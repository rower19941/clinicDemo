package com.company.clinic.core;

import com.company.clinic.config.VisitConfig;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("clinic_VisitEntityListener")
public class VisitEntityListener implements BeforeInsertEntityListener<Visit> {
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Inject
    private Configuration configuration;

    @Override
    public void onBeforeInsert(Visit entity, EntityManager entityManager) {
        VisitConfig config = configuration.getConfig(VisitConfig.class);
        entity.setNumber(String.valueOf(uniqueNumbersAPI.getNextNumber(config.getVisitUn())));
    }

}
