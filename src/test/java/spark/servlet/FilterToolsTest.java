package spark.servlet;

import javax.servlet.http.HttpServletRequest;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FilterToolsTest {

    @Test
    public void getRelativePath_null() {
        assertNull(FilterTools.getRelativePath(null, null));
        assertNull(FilterTools.getRelativePath(mock(HttpServletRequest.class), null));
        assertNull(FilterTools.getRelativePath(null, "xyz"));
    }
    
    @Test
    public void getRelativePath() {
        String expectedPath = "/some/path.html";
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/filterThisOut" + expectedPath);
        when(request.getContextPath()).thenReturn("");
        
        assertEquals("Should filter out 'filterThisOut'", expectedPath, FilterTools.getRelativePath(request, "filterThisOut"));
    }
    
    @Test
    public void getRelativePath_nonMatchingFilter() {
        String expectedPath = "/some/path.html";
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(expectedPath);
        when(request.getContextPath()).thenReturn("");
        
        assertEquals("Shouldn't filter", expectedPath, FilterTools.getRelativePath(request, "blah"));
    }
    
    @Test
    public void getRelativePath_handleFilterTrailingSlash() {
        String path = "/some/path";
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn(path);
        when(request.getContextPath()).thenReturn("");
        
        assertEquals("Path should be '/'", "/", FilterTools.getRelativePath(request, "some/path/"));
    }
    
    @Test
    public void getRelativePath_sameLocation() {
        String expectedPath = "/";
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/dir");
        when(request.getContextPath()).thenReturn("/dir");
        
        assertEquals("Path should be '/'", expectedPath, FilterTools.getRelativePath(request, ""));
    }
}
