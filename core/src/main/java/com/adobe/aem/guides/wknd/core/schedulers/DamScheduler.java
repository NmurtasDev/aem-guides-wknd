package com.adobe.aem.guides.wknd.core.schedulers;

import com.adobe.aem.guides.wknd.core.schedulers.config.DamSchedulerConf;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd= DamSchedulerConf.class)
@Component(service=Runnable.class)
public class DamScheduler  implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String myParameter;

    @Override
    public void run() {
        logger.debug("SimpleScheduledTask is now running, myParameter='{}'", myParameter);
    }

    @Activate
    protected void activate(final DamSchedulerConf config) {
        myParameter = config.myParameter();
    }
}