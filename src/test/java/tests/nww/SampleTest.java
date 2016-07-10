/**
 * 
 */
package tests.nww;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nww.NetzwerkWohnenApp;
import org.nww.core.utils.DefaultLocaleHelper;
import org.nww.core.utils.LocaleHelper;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author mga
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NetzwerkWohnenApp.class)
@WebAppConfiguration
public class SampleTest {
	
	private LocaleHelper lh = new DefaultLocaleHelper();
	
	@Test
	public void sample() throws Exception {
		assertThat(lh.getLocaleID(Locale.GERMANY), equalTo("de_DE"));
	}
}
