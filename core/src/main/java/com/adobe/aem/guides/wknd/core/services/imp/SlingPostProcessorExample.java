package com.adobe.aem.guides.wknd.core.services.imp;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.servlets.post.Modification;
import org.apache.sling.servlets.post.SlingPostProcessor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SlingPostProcessorExample implements SlingPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlingPostProcessorExample.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void process(SlingHttpServletRequest request, List<Modification> mods) throws Exception {

        /**
         * This map will be used to get session via getServiceResourceResolver() method
         */
        Map<String, Object> params = new HashMap<>();

        /**
         * Adding the subservice name in the param map
         */
        params.put(ResourceResolverFactory.SUBSERVICE, "wknd-service-admin");

        /**
         * Getting resource resolver from the service factory
         */
        try (ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(params)) {
            //current state of the resource before changes are persisted
            Resource resourceBeforeModification1 = resolver.getResource(request.getResource().getPath());
            //our resource will be in the below state after changes are persisted
            Resource resourceAfterModification = request.getResource();

        } catch (Exception e) {
            LOGGER.error("login error", e);
        }

    }

}
