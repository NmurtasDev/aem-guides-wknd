package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.utils.DateUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(service = Servlet.class,
        property = {
                "name=" + "WkndServlet",
                Constants.SERVICE_DESCRIPTION + "= Form submit servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.resourceTypes=" + "sling/servlet/default",
                "sling.servlet.selectors=" + "wkndform",
                "sling.servlet.extensions=" + "html"
        })
public class FormSubmitPost extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(FormSubmitPost.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        String redirectAfterAll = request.getParameter("failRedirect");
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String formDateString = request.getParameter("date");
            String resourcePath = request.getParameter("resourcePath");
            log.info("name : {}; lastname : {}", name, surname);
            /*check della date dentro il componente se presente*/
            ResourceResolver resourceResolver = request.getResourceResolver();
            Resource currentResource = resourceResolver.getResource(resourcePath);
            ValueMap valueMap = currentResource.getValueMap();
            Date date = valueMap.get("date",Date.class);
            if(date == null){
                date = new Date();
            }
            Date onTheFuture = DateUtils.getDateOnTheFuture(date,15);
            SimpleDateFormat sdf = DateUtils.getSimpleDateFormatForDatePicker();
            Date formDate = sdf.parse(formDateString);
            if(formDate == null || (formDate.before(date) || formDate.after(onTheFuture))){
                log.error("data sbagliata");
            }
            String successRedirect = request.getParameter("succesRedirect");
            redirectAfterAll = successRedirect;
        }catch (Exception e){
            //qualcosa
            log.error("Exception");
        }

        response.sendRedirect(redirectAfterAll.concat(".html"));
    }
}
