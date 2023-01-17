package com.adobe.aem.guides.wknd.core.schedulers.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@ObjectClassDefinition(name="A scheduled task for DAM",
        description = "Simple demo for cron-job like task with properties for DAM")
public @interface DamSchedulerConf {

    @AttributeDefinition(name = "Cron-job expression")
    String scheduler_expression() default "*/30 * * * * ?";

    @AttributeDefinition(name = "Concurrent task",
            description = "Whether or not to schedule this task concurrently")
    boolean scheduler_concurrent() default false;

    @AttributeDefinition(name = "A parameter",
            description = "Can be configured in /system/console/configMgr")
    String myParameter() default "";
}