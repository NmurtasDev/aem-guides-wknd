package com.adobe.aem.guides.wknd.core.listeners;

/*
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/

/**
 * @author Anirudh Sharma
 *
 * Event Handler that listens to the Sling events
 *
 * COMMENTATA PERCHE CREA TROPPI LOG
 *
 */
//@Component(immediate = true, service = EventHandler.class, property = {
//        Constants.SERVICE_DESCRIPTION + "= This event handler listens the events on page activation",
//        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
//        EventConstants.EVENT_FILTER + "(&" + "(path=/content/dam/wknd/*/jcr:content)" })
//public class CustomEventHandlerDAM implements EventHandler {
//
//    /**
//     * Logger
//     */
//    private static final Logger log = LoggerFactory.getLogger(CustomEventHandlerDAM.class);
//
//    @Override
//    public void handleEvent(Event event) {
//
//        log.info("Event is: {}", event.getTopic());
//    }
//
//}