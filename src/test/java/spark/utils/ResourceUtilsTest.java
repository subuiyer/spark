package spark.utils;

import java.io.FileNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;
        
public class ResourceUtilsTest {

    @Test
    public void isUrl_null() {
        assertFalse(ResourceUtils.isUrl(null));
    }
    
    @Test
    public void isUrl_classpath() {
        assertTrue(ResourceUtils.isUrl("classpath:blah"));
    }
    
    @Test
    public void isUrl_malformed() {
        assertFalse(ResourceUtils.isUrl("blah"));
    }
    
    @Test
    public void isUrl() {
        assertTrue(ResourceUtils.isUrl("http://abc.com"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getURL_null() throws FileNotFoundException {
        ResourceUtils.getURL(null);
    }
    
    @Test(expected = FileNotFoundException.class)
    public void getURL_classpath_noResource() throws FileNotFoundException {
        assertNull(ResourceUtils.getURL("classpath:someResource"));
    }
    
    @Test
    public void getURL_classpath_resourceExists() throws FileNotFoundException {
        assertNotNull(ResourceUtils.getURL("classpath:util/aResource.txt"));
    }
    
    @Test
    public void getURL_file() throws FileNotFoundException {
        assertNotNull(ResourceUtils.getURL("pom.xml"));
    }
    
}
