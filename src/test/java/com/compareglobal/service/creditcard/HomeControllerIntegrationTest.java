package com.compareglobal.service.creditcard;

import com.compareglobal.service.creditcard.domain.Compare;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static com.compareglobal.service.creditcard.CreditCardAssertions.assertThatHasCreditCards;
import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class HomeControllerIntegrationTest extends TestBase {

    @Test
    public void shouldReturnPersonalLoanListFromHK() {
        final Compare compare = new Compare();
        compare.setLocale("en-HK");
        compare.setFilter(Compare.Filter.AIRMILES);

        shouldReturnCreditCardListFrom(compare);
    }


    private ResponseEntity<Object> shouldReturnCreditCardListFrom(final Compare params) {
        final ResponseEntity<Object> response = postCreditCard(params);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThatHasCreditCards(response);
        return response;
    }
}