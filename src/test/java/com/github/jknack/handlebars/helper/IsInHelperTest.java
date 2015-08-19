package com.github.jknack.handlebars.helper;

import com.compareglobal.service.creditcard.domain.CreditCard;
import com.compareglobal.service.creditcard.domain.CreditCardPublic;
import com.compareglobal.service.creditcard.domain.promotions.Promotion;
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
public class IsInHelperTest {
    private Handlebars handlebars;

    @Before
    public void init() {
        handlebars = new Handlebars()
                .registerHelper(IsInHelper.NAME, IsInHelper.INSTANCE);
    }

    @Test
    public void contains() throws Exception {
        String containsScript = "{{#each creditCard.promotions}}\n" +
                "    {{#isIn typeValue filter=\"welcomeGift, promoExpiry, proHasOnlineCondition\"}}\n" +
                "        {\"type\": \"{{this.typeValue}}\",\n" +
                "        \"value\": \"{{this.title}}\",\n" +
                "        \"description\": \"{{this.description}}\",\n" +
                "        },\n" +
                "    {{/isIn}}\n" +
                "{{~/each}}";
        Template template = handlebars.compileInline(containsScript);

        CreditCard creditCard = mock(CreditCard.class);
        Set<Promotion> promotions = new HashSet<>();
        Promotion promotion1 = mock(Promotion.class);
        when(promotion1.getTypeValue()).thenReturn ("welcomeGift");
        when(promotion1.getDescription()).thenReturn("welcomeGift 1");
        promotions.add(promotion1);


        Promotion promotion2 = mock(Promotion.class);
        when(promotion2.getTypeValue()).thenReturn("onlyGift");
        when(promotion2.getDescription()).thenReturn("onlyGift 2");
        promotions.add(promotion2);

        when(creditCard.getPromotions()).thenReturn(promotions);

        CreditCardPublic creditCardPublic = new CreditCardPublic(creditCard);
        String result = template.apply(creditCardPublic).replaceAll("\n", "")
                .replaceAll(" },]", "}]")
                .replaceAll("&quot;", "\"\"");
        assertNotNull(result);
        assertTrue(result.contains("welcomeGift"));

    }
}

