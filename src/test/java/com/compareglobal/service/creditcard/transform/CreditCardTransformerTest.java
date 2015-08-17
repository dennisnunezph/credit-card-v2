package com.compareglobal.service.creditcard.transform;

import com.compareglobal.service.common.domain.Filter;
import com.compareglobal.service.creditcard.domain.Compare;
import com.compareglobal.service.creditcard.domain.CreditCard;
import com.compareglobal.service.creditcard.domain.CreditCardPublic;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dennis on 7/30/15.
 */
public class CreditCardTransformerTest {

    private CreditCardTransformer cardTransformer;

    @Before
    public void init() {
        cardTransformer = new CreditCardTransformer();
    }

    @Test
    public void airmilesFilter() throws Exception {

        Compare compare = new Compare();
        compare.setLocale("en-SG");
        compare.setFilter(Compare.Filter.AIRMILES);

        List<CreditCard> cardList = new ArrayList<>();
        CreditCard airmilesCard = mock(CreditCard.class);
        Set<Filter> stubFilter = new HashSet<>();

        Filter airmilesFilter = new Filter();
        airmilesFilter.setTypeValue(Compare.Filter.AIRMILES.getValue());
        airmilesFilter.setValue("1");
        stubFilter.add(airmilesFilter);

        Filter cashBackFilter = new Filter();
        cashBackFilter.setTypeValue(Compare.Filter.CASHBACK.getValue());
        cashBackFilter.setValue("0");
        stubFilter.add(cashBackFilter);

        when(airmilesCard.getFilter()).thenReturn(stubFilter);

        cardList.add(airmilesCard);

        List<CreditCardPublic> result = cardTransformer.filteredList(cardList, compare);
        assertEquals(1, result.size());
    }
}
