package com.company.clinic.core;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(VisitBean.NAME)
public class VisitBean {
    public static final String NAME = "clinic_VisitBean";

    @Inject
    private Persistence persistence;

    public List<Visit> getVisits(){
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager entityManager = persistence.getEntityManager();
            TypedQuery<Visit> query = entityManager.createQuery("select distinct c from clinic_Consumable c " +
                    "where @between(c.createTs, now-7, now+1 , day)", Visit.class);
            query.setViewName(View.LOCAL);
            List<Visit> resultList = query.getResultList();
            tx.commit();
            return resultList;
        } finally {
            tx.end();
        }
    }

    public Visit getVisit(Long id) {
        Transaction tx = persistence.createTransaction();
        try{
            EntityManager entityManager = persistence.getEntityManager();
            TypedQuery<Visit> query = entityManager.createQuery(
                    "select u from CLINIC_VISIT u where u.id = :id", Visit.class);
            query.setParameter("id", id);
            Visit visit = query.getSingleResult();
            tx.commit();
            return visit;
        }finally {
            tx.end();
        }

    }
}