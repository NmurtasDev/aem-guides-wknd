package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="/apps/wknd/components/page",
        methods= "GET",
        extensions="html",
        selectors="hello")
public class GetSelectorServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String responseString = "<html><head><title>Benvenuto</title></head><body><h1>Hello World!</h1></body></html>";

        /**
         * Writing the entire JSON string on the browser
         */
        response.getWriter().println(responseString);
    }
}
