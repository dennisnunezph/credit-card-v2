package com.github.jknack.handlebars.helper;


import com.compareglobal.service.creditcard.domain.CreditCard;
import com.compareglobal.service.creditcard.domain.CreditCardPublic;
import com.compareglobal.service.creditcard.domain.rewards.Reward;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dennis on 8/19/15.
 */
public class ContainsHelperTest {
    private Handlebars handlebars;

    @Before
    public void init() {
        handlebars = new Handlebars()
                .registerHelper(ContainsHelper.NAME, ContainsHelper.INSTANCE);
    }

    @Test
    public void contains() throws Exception {
        String containsScript = "{{#each creditCard.rewards}}\n" +
                "    {{#contains typeValue keyword=\"cashback\"}}\n" +
                "    \"{{this.typeValue}}\" :\n" +
                "        {\"type\": \"{{this.typeValue}}\",\n" +
                "        \"value\": \"{{this.value}}\",\n" +
                "        \"description\": \"{{this.description}}\"\n" +
                "        },\n" +
                "    {{/contains}}\n" +
                "{{~/each}}";
        Template template = handlebars.compileInline(containsScript);

        CreditCard creditCard = mock(CreditCard.class);
        Set<Reward> rewards = new HashSet<>();
        Reward reward1 = new Reward();
        reward1.setTypeValue("cashback");
        reward1.setDescription("cashback 1");
        rewards.add(reward1);

        Reward reward2 = new Reward();
        reward2.setTypeValue("airmiles");
        reward2.setDescription("airmiles 1");
        rewards.add(reward2);


        when(creditCard.getRewards()).thenReturn(rewards);

        CreditCardPublic creditCardPublic = new CreditCardPublic(creditCard);
        String result = template.apply(creditCardPublic).replaceAll("\n", "")
                .replaceAll(" },]", "}]")
                .replaceAll("&quot;", "\"\"");
        assertNotNull(result);
        assertTrue(result.contains("cashback"));

    }
}
