package com.compareglobal.service.creditcard.transform;

import com.compareglobal.service.common.domain.Filter;
import com.compareglobal.service.common.utils.ListHelper;
import com.compareglobal.service.creditcard.domain.Compare;
import com.compareglobal.service.creditcard.domain.CreditCard;
import com.compareglobal.service.creditcard.domain.CreditCardPublic;
import com.compareglobal.service.creditcard.domain.benefits.Benefit;
import java.util.ArrayList;
import java.util.List;
import static org.boon.sort.Sorting.sort;

/**
 * Created by dennis on 7/28/15.
 */
public class CreditCardTransformer {

    private static final String TYPE_VALUE = "typeValue";
    private static final String TYPE_KEY = "typeKey";
    private static final String EN_SG = "en-SG";
    private static final String FILTER_TRUE = "true";
    private static final String FILTER_ACTIVE = "1";

    public  List<CreditCardPublic> filteredList(List<CreditCard> creditCards, Compare compare) {
        List<CreditCardPublic> filteredList = new ArrayList<>();
        String filterValue = getFilterValue(compare);

        for (CreditCard creditCard : creditCards) {
            Filter currentFilter = ListHelper.findItemByTypeT(creditCard.getFilter(), TYPE_VALUE, filterValue);
            if (currentFilter != null
                    && (FILTER_TRUE.equalsIgnoreCase(currentFilter.getValue())
                        ||  FILTER_ACTIVE.equalsIgnoreCase(currentFilter.getValue())
                       )
                    ) {
                filteredList.add(sortedValue(creditCard));
            }
        }
        return filteredList;
    }

    public CreditCardPublic sortedValue(CreditCard creditCard) {
        CreditCardPublic cardPublic = new CreditCardPublic(creditCard);

        List<Benefit> sortedBenefits = (List<Benefit>) sort(Benefit.class, creditCard.getBenefits(), TYPE_KEY);
        cardPublic.setSortedBenefits(sortedBenefits);

        return cardPublic;
    }

    private String getFilterValue(Compare compare) {
        String filterValue = compare.getFilter().getValue();
        if (EN_SG.equalsIgnoreCase(compare.getLocale())
                && compare.getFilter().equals(Compare.Filter.PREMIUM)) {
            filterValue = Compare.Filter.PREMIUMSG.getValue();
        }
        return filterValue;
    }

}
