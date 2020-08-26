package com.company.clinic.listeners;

import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EmailException;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.EmailInfoBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("clinic_VisitChangedListener")
public class VisitChangedListener {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(VisitChangedListener.class);
    @Inject
    private DataManager dataManager;
    @Inject
    private EmailService emailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Visit, UUID> event) {
        AttributeChanges changes = event.getChanges();
        if(changes.isChanged("date")){
            Visit one = dataManager.load(event.getEntityId()).view("visit-load").one();
            sendDateChange(one);
        }
    }

    private void sendDateChange(Visit one) {
        String email = one.getPet().getOwner().getEmail();
        if(StringUtils.isNotEmpty(email)){
            EmailInfo emailInfo = EmailInfoBuilder
                    .create()
                    .setAddresses(email)
                    .setCaption("Visit date changed")
                    .setBody("")
                    .build();
            log.info(emailInfo.toString());
           /* try {
                emailService.sendEmail(emailInfo);
            } catch (EmailException e) {
                e.printStackTrace();
            }*/
        }
    }
}