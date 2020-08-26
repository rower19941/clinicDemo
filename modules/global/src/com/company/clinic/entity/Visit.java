package com.company.clinic.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.TimeSource;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PublishEntityChangedEvents
@Table(name = "CLINIC_VISIT")
@Entity(name = "clinic_Visit")
@NamePattern("%s|descriptions")
@Listeners("clinic_VisitEntityListener")
public class Visit extends StandardEntity {
    private static final long serialVersionUID = -8254124810105648524L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PET_ID")
    private Pet pet;

    @Column(name = "NUMBER_")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VETERINARIAN_ID")
    private Veterinarian veterinarian;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATE_", nullable = false)
    private Date date;

    @Lob
    @Column(name = "DESCRIPTIONS")
    private String descriptions;

    @Column(name = "HOURS_SPEND")
    @PositiveOrZero
    private Integer hoursSpend;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @JoinTable(name = "CLINIC_VISIT_CONSUMABLE_LINK",
            joinColumns = @JoinColumn(name = "VISIT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONSUMABLE_ID"))
    @ManyToMany
    private List<Consumable> consumables;

    @Column(name = "VISIT_TYPE")
    private String visitType;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VisitType getVisitType() {
        return visitType == null ? null : VisitType.fromId(visitType);
    }

    public void setVisitType(VisitType visitType) {
        this.visitType = visitType == null ? null : visitType.getId();
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(List<Consumable> consumables) {
        this.consumables = consumables;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getHoursSpend() {
        return hoursSpend;
    }

    public void setHoursSpend(Integer hoursSpend) {
        this.hoursSpend = hoursSpend;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }



    @PostConstruct
    public void postConstruct() {
        setAmount(BigDecimal.ZERO);
        setDate(AppBeans.get(TimeSource.class).currentTimestamp());
    }
}