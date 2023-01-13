package com.adobe.aem.guides.wknd.core.servlets;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Anirudh Sharma
 * <p>
 * This class shows the usage of SlingPostServlet
 */
@Component(service = Servlet.class, property = {Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/submitdata"})
public class SlingServlet extends SlingAllMethodsServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -159625176093879129L;

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(SlingServlet.class);

    /**
     * Overridden doPost() method which is invoked when an HTTP post request is made
     */
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        try {

            /**
             * Getting the instance of resource resolver from the request
             */
            ResourceResolver resourceResolver = request.getResourceResolver();

            /**
             * Getting the resource object via path
             */
            Resource resource = resourceResolver.getResource("/content/wknd");
            log.info("Resource is at path {}", resource.getPath());

            StringBuilder buf = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
            }
            String outputBuffer = buf.toString();
            log.info("Risultato lettura body {}", outputBuffer);
            JsonObject jsonObject = new JsonParser().parse(outputBuffer).getAsJsonObject();

            Set<String> key = jsonObject.keySet();
            for (Iterator<String> it = key.iterator(); it.hasNext(); ) {
                String name = it.next();
                Resource demoNode = resource.getChild(name);

                if (demoNode == null) {
                    /**
                     * Adapt the resource to javax.jcr.Node type
                     */
                    Node node = resource.adaptTo(Node.class);


                    /**
                     * Create a new node with name and primary type and add it below the path specified by the resource
                     */
                    Node newNode = node.addNode(name, "nt:unstructured");

                    JsonObject object = jsonObject.getAsJsonObject(name);
                    Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();
                    for (Iterator<Map.Entry<String, JsonElement>> entryIterator = entrySet.iterator();entryIterator.hasNext();){
                        Map.Entry<String, JsonElement> entry = entryIterator.next();
                        JsonElement element = entry.getValue();
                        newNode.setProperty(entry.getKey(), element.getAsString());
                    }
                    /**
                     * Setting a name property for this node
                     */


                    /**
                     * Commit the changes to JCR
                     */
                    resourceResolver.commit();

                    response.getWriter().println("Nodo creato con successo");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "node already exist");
                }
            }

        } catch (RepositoryException | IOException e) {

            log.error(e.getMessage(), e);

            e.printStackTrace();

        }
    }


}