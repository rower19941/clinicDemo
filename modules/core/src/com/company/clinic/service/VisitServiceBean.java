package com.company.clinic.service;

import com.company.clinic.core.VisitBean;
import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Visit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Service(VisitService.NAME)
public class VisitServiceBean implements VisitService {

    @Inject
    private VisitBean visitBean;

    @Override
    public List<Visit> getVisits() {
        return visitBean.getVisits();
    }

    @Override
    public Visit getVisit(Long id) {
        return visitBean.getVisit(id);
    }

    @Override
    public double cost(Visit visit) {
        Integer hoursSpend = visit.getHoursSpend();
        BigDecimal amount = visit.getAmount();
        double consumableCost = 0.0;
        for(Consumable consumable: visit.getConsumables()){
            consumableCost= consumableCost+consumable.getPrice().doubleValue();
        }

        return (amount.doubleValue() * hoursSpend)+consumableCost;
    }
}