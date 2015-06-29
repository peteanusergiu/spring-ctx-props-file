import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath*:/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SystemVariableCommandLineTest {

	@Value("#{systemProperties['http.proxyHost']?: 'gradlePropDefault'}")
    private String httpHost;
	@Value("#{systemProperties['http.proxyPort']?: 'gradlePropDefault'}")
	private String httpPort;
	@Value("#{systemProperties['https.proxyHost']?: 'gradlePropDefault'}")
	private String httpsHost;
	@Value("#{systemProperties['https.proxyPort']?: 'gradlePropDefault'}")
	private String httpsPort;
	
	@Test
    public void testHttpProxyHost(){
    	assertEquals("proxy.host.http", httpHost);
    }
	@Test
	public void testHttpsProxyHost(){
		assertEquals("proxy.host.https", httpsHost);
	}
	@Test
	public void testHttpsProxyPort(){
		assertEquals("3128", httpsPort);
	}
	public void testHttpProxyPort(){
		assertEquals("8080", httpPort);
	}
}
