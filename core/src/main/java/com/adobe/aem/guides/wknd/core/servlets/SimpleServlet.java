package com.adobe.aem.guides.wknd.core.servlets;

import javax.servlet.Servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anirudh Sharma
 *
 * This servlet uses the HTTP GET method to read a data from the RESTful webservice
 */
@Component(service = Servlet.class, property = {
        Constants.SERVICE_DESCRIPTION + "=JSON Servlet to read the data from the content",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/readData" })
public class SimpleServlet extends SlingSafeMethodsServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = 4438376868274173005L;

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(SimpleServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        try {

            log.info("Reading the data from the webservice");

            ResourceResolver resourceResolver = request.getResourceResolver();

            Resource wknd = resourceResolver.getResource("/content/wknd");

            JsonObject returnObject = new JsonObject();

            JsonArray wkndChild = new JsonArray();

            for(Resource child : wknd.getChildren()){
                JsonObject childJson = new JsonObject();
                childJson.addProperty("name",child.getName());
                childJson.addProperty("path",child.getPath());
                wkndChild.add(childJson);
            }
            returnObject.add("wknd child",wkndChild);

            /**
             * Getting the JSON string from the webservice
             */
            String responseString = returnObject.toString();

            /**
             * Writing the entire JSON string on the browser
             */
            response.getWriter().println(responseString);

        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }
    }

}
