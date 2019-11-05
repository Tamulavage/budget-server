package com.group3.budgetApp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "future_accounting")
@IdClass(LineItemId.class)
public class FutureBudgetLineItem {

    @Id
    @Column(name = "org_ID")
    private Integer orgId;

    @Id
    @Column(name = "month")
    private Integer month;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "freq_per_month")
    private Integer frequencyPerMonth;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFrequencyPerMonth() {
        return frequencyPerMonth;
    }

    public void setFrequencyPerMonth(Integer frequencyPerMonth) {
        this.frequencyPerMonth = frequencyPerMonth;
    }
}
