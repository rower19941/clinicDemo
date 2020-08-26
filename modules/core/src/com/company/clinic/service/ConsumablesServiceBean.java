package com.company.clinic.service;

import com.company.clinic.core.ConsumablesBean;
import com.company.clinic.entity.Consumable;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(ConsumablesService.NAME)
public class ConsumablesServiceBean implements ConsumablesService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ConsumablesServiceBean.class);

    @Inject
    private ConsumablesBean consumablesBean;

    @Override
    public List<Consumable> getConsumables() {
        log.info("run" + this.toString());
        log.debug("run" + this.toString());
        return consumablesBean.getConsumables();

    }
}