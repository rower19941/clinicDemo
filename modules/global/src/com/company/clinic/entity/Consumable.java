package com.company.clinic.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Table(name = "CLINIC_CONSUMABLE")
@Entity(name = "clinic_Consumable")
@NamePattern("%s|title")
public class Consumable extends StandardEntity {
    private static final long serialVersionUID = -6179742398363824522L;

    @NotNull
    @Lob
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    @DecimalMin("1")
    @Positive
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}