package com.compareglobal.service.creditcard;

import com.compareglobal.service.creditcard.domain.Compare;
import com.compareglobal.service.creditcard.service.CompareService;
import com.github.jknack.handlebars.Handlebars;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;


/**
 * Created by dennis on 8/18/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private CompareService compareService;

    @Mock
    private Handlebars handlebars;

    @Test
    public void template() throws Exception {
        Compare compare = new Compare();
        compare.setLocale("en-HK");
        compare.setFilter(Compare.Filter.AIRMILES);
        Object result = homeController.template(compare);
        assertNotNull(result);
    }
}
