/**
 * 
 */
package tests.nww;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.nww.core.utils.DefaultLocaleHelper;
import org.nww.core.utils.LocaleHelper;

/**
 *
 * @author mga
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@SpringApplicationConfiguration(classes = NetzwerkWohnenApp.class)
//@WebAppConfiguration
public class SampleTest {
	
	private LocaleHelper lh = new DefaultLocaleHelper();
	
	@Test
	public void sample() throws Exception {
		assertThat(lh.getLocaleID(Locale.GERMANY), equalTo("de_DE"));
	}
}
