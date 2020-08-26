package com.company.clinic.core;

import com.company.clinic.entity.Consumable;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.View;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(ConsumablesBean.NAME)
public class ConsumablesBean {
    public static final String NAME = "clinic_ConsumablesBean";

    @Inject
    private Persistence persistence;


    public List<Consumable> getConsumables() {
//@Trans...
       /* EntityManager em = persistence.getEntityManager();
        TypedQuery<Consumable> query = em.createQuery("select distinct c from clinic_Consumable c " +
                " where @between(c.createTs, now-7, now+1, day) ", Consumable.class);
        query.setViewName(View.LOCAL);
        List<Consumable> consumableList = query.getResultList();

        return consumableList;*/

       /* return persistence.createTransaction().execute(em1 -> {
TypedQuery<Consumable> query = em1.createQuery("select distinct c from clinic_Consumable c " +
" where @between(c.createTs, now-7, now+1, day) ", Consumable.class);
query.setViewName(View.LOCAL);
return query.getResultList();
});*/

        Transaction tx = persistence.createTransaction();
        try {
            EntityManager entityManager = persistence.getEntityManager();
            TypedQuery<Consumable> query = entityManager.createQuery("select distinct c from clinic_Consumable c " +
                    "where @between(c.createTs, now-7, now+1 , day)", Consumable.class);
            query.setViewName(View.LOCAL);
            List<Consumable> resultList = query.getResultList();
            tx.commit();
            return resultList;
        } finally {
            tx.end();
        }
    }
}