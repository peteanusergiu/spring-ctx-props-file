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

	/*using spring techniques/objects to obtain access to project and system specific variables*/
	
	/*using appProperties for getting access to app.properties defined variables*/
    @Value("#{appProperties['prop.example']}")
    private String exampleProperty;

    /*using systemProperties for getting access to SYSTEM ENV defined variables*/
    @Value("#{systemProperties['gradleProp']?: 'gradlePropDefault'}")
    private String systemProperty;

    /*using a bean & dependency injection for accessing a variable defined in app-context.xml file*/
    @Autowired
    private ValueHolder valueHolder;
    
/*---------------------------------------------------------------------------------------------------------
    Examples proving how to use Spring techniques for sending parameters/variables to test JVM 
-----------------------------------------------------------------------------------------------------------*/
    @Test
    public void testValueHolder() throws Exception {
        assertEquals("Hello world!", valueHolder.getValue());
    }
    
    @Test
    public void testExampleProp() {
        assertEquals("Hello world!", exampleProperty);
    }

/*---------------------------------------------------------------------------------------------------------
    Examples proving how to transmit parameters from Gradle's BUILD JVM to TEST JVM
    (we are talking about 2 separate processes each one with it's own environment therefore in order to "share" 
    any parameters gradle has to send them, one by one, or, not recommended, all of them) 
-----------------------------------------------------------------------------------------------------------*/
    @Test
    public void testAllSystemProperties(){
        assertNotNull(System.getProperties());
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

}
