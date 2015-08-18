package com.compareglobal.service.creditcard;

import org.junit.Assert;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Luis Miguel Osorio.
 */
public class CreditCardAssertions {
    public static void assertThatHasCreditCards(final ResponseEntity<Object> response) {
        assertThatHasCreditCards("", response);
    }

    public static void assertThatHasCreditCards(final String message, final ResponseEntity<Object> response) {
        assertThatHasList("the service should returns a list of personal loans " + message, response);
    }

    public static void assertThatHasProviders(final ResponseEntity<Object> response) {
        assertThatHasList("the service should returns a list of providers", response);
    }

    public static void assertThatHasList(final String message, final ResponseEntity<Object> response) {
        Assert.assertNotNull(message, response.getBody());
        Assert.assertFalse(message, response.getBody() == null);
    }

    public static void assertThatHasFilter(final Map<String, Object> personalLoan, final String dataBaseFilterName) {
        final List<Map<String, String>> filters = (List<Map<String, String>>) personalLoan.get("filters");
        Assert.assertNotNull("the personal loan should has a list of filters", filters);

        boolean hasDataBaseFilter = false;
        for (Map<String, String> creditCardFilter : filters) {
            if (creditCardFilter.get("type").equals(dataBaseFilterName) && creditCardFilter.get("value").equals("true")) {
                hasDataBaseFilter = true;
            }
        }

        Assert.assertTrue("the personal loan should has `" + dataBaseFilterName + "` filter", hasDataBaseFilter);
    }


}
