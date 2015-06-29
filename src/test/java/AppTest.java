import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath*:/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    @Value("#{appProperties['prop.example']}")
    private String exampleProperty;

    @Value("#{systemProperties['gradleProp']?: 'gradlePropDefault'}")
    private String systemProperty;

    @Autowired
    private ValueHolder valueHolder;

    @Test
    public void testAllSystemProperties(){
        assertNotNull(System.getProperties());
//        System.out.println(System.getProperties());
    }
    
    @Test
    public void testSystemVarSystemProperties(){
    	assertEquals("SYSTEM_VAR_systemProperties_VALUE", System.getProperty("SYSTEM_VAR_systemProperties"));
    }

    @Test
    public void testSystemVarSystemPropertiesMapValue(){
    	assertEquals("SYSTEM_VAR_systemProperties_map_VALUE", System.getProperty("SYSTEM_VAR_systemProperties_map_value"));
    }

    @Test
    public void testSystemVarSystemPropertiesMapSysProp(){
    	assertEquals("SYSTEM_VAR_systemProperties_map_VALUE", System.getProperty("SYSTEM_VAR_systemProperties_map_sysProp"));
    }
    
    @Test
    public void testSystemVarSystemProperty(){
    	assertEquals("SYSTEM_VAR_systemProperty_VALUE", System.getProperty("SYSTEM_VAR_systemProperty"));
    }

    @Test
    public void testSystemVarSystemPropertyFromGradleProp(){
    	assertEquals("gradlePropValue", System.getProperty("gradleProp"));
    }

    @Test
    public void testSystemVarFromSystemProperty(){
    	assertEquals("gradlePropValue", systemProperty);
    }

    @Test
    public void testValueHolder() throws Exception {
        assertEquals("Hello world!", valueHolder.getValue());
    }

    @Test
    public void testExampleProp() {
        assertEquals("Hello world!", exampleProperty);
    }
}
