package com.compareglobal.service.creditcard;

import com.compareglobal.service.common.domain.Provider;
import com.compareglobal.service.creditcard.domain.Compare;
import com.compareglobal.service.creditcard.domain.CreditCard;
import com.compareglobal.service.creditcard.domain.CreditCardPublic;
import com.compareglobal.service.creditcard.domain.RetrieveList;
import com.compareglobal.service.creditcard.service.CompareService;
import com.compareglobal.service.creditcard.transform.CreditCardTransformer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String DEFAULT_CONTENT_TYPE_CHARSET = "Content-Type=application/json;charset=UTF-8";

    private static final String BRAND_CARD_PROVIDER_VISA = "cardProviderVisa";

    private final JsonSchemaGenerator generator = new JsonSchemaGenerator(MAPPER);

    private final CompareService compareService;

    @Autowired
    private final Handlebars handlebars;

    @Inject
    public HomeController(CompareService compareService,
                          Handlebars handlebars) {
        this.compareService = compareService;
        this.handlebars = handlebars;
    }

    @RequestMapping(value = "/compare", method = RequestMethod.GET, headers = DEFAULT_CONTENT_TYPE_CHARSET)
    @ResponseBody
    public JsonSchema home() throws JsonMappingException {
        return generator.generateSchema(Compare.class);
    }

    @RequestMapping(value = "/template", method = RequestMethod.POST, headers = DEFAULT_CONTENT_TYPE_CHARSET)
    @ResponseBody
    public Object template(@RequestBody final Compare compare) throws IOException {
        List<CreditCard> creditCards = compareService.compare(compare.getLocale());
        CreditCardTransformer cardTransformer = new CreditCardTransformer();
        List<CreditCardPublic> creditCardFiltered = cardTransformer.filteredList(creditCards, compare);

        Template template = handlebars.compile("creditCardTemplate" + compare.getCountrySuffix());

        List<Object> resultTemplate = new ArrayList<>();
        for (CreditCardPublic creditCard : creditCardFiltered) {
            String templateResult = template.apply(creditCard).replaceAll("\n", "")
                    .replaceAll(" },]", "}]")
                    .replaceAll("&quot;", "\"\"");
            resultTemplate.add(JSONValue.parse(templateResult));
        }
        return resultTemplate;
    }

    @RequestMapping(value = "/providers", method = RequestMethod.POST, headers = DEFAULT_CONTENT_TYPE_CHARSET)
    @ResponseBody
    public List<Provider> providers(@RequestBody final RetrieveList retrieve) {
        return compareService.getProviders(retrieve.getLocale());
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, headers = DEFAULT_CONTENT_TYPE_CHARSET)
    @ResponseBody
    public CreditCard search(@RequestBody final RetrieveList retrieve) {
        return compareService.search(retrieve.getId());
    }

    @RequestMapping(value = "/brands", method = RequestMethod.GET, headers = DEFAULT_CONTENT_TYPE_CHARSET)
    @ResponseBody
    public List<String> brands(@RequestParam(value = "brandType", required = false, defaultValue = BRAND_CARD_PROVIDER_VISA) String brandType) {
        return compareService.getBrands(brandType);
    }

}
