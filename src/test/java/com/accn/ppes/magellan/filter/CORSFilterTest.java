package com.accn.ppes.magellan.filter;


import com.accn.ppes.magellan.packageFilter.CORSFilter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class CORSFilterTest {


    @Test
    public void testCorsFilter() throws Exception {

        MockHttpServletRequest request=new MockHttpServletRequest();
        MockFilterChain filterChain=new MockFilterChain();
        MockFilterConfig filterConfig=new MockFilterConfig();
        CORSFilter filter=new CORSFilter();
        MockHttpServletResponse response=new MockHttpServletResponse();
        filter.doFilter(request,response,filterChain);
        Assert.assertEquals("*",response.getHeader("Access-Control-Allow-Origin"));
        Assert.assertEquals("POST, GET, PUT, OPTIONS, DELETE",response.getHeader("Access-Control-Allow-Methods"));
        filter.destroy();
    }

}
