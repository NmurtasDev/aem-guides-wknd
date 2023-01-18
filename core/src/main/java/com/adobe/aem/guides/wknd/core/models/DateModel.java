/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.utils.DateUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = Resource.class)
public class DateModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;

    String minDate;

    String maxDate;

    @PostConstruct
    protected void init() {
        ValueMap properties = currentResource.getValueMap();
        Date date = properties.get("datepicker",Date.class);
        calculateDate(date);

    }

    private void calculateDate(Date date) {
        if(date == null){
            date = new Date();
        }
        Date futureDay = DateUtils.getDateOnTheFuture(date,15);
        SimpleDateFormat simpleDateFormat = DateUtils.getSimpleDateFormatForDatePicker();
        minDate = simpleDateFormat.format(date);
        maxDate = simpleDateFormat.format(futureDay);
    }

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }
}