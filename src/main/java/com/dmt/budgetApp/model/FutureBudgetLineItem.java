package com.dmt.budgetApp.model;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((frequencyPerMonth == null) ? 0 : frequencyPerMonth.hashCode());
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FutureBudgetLineItem other = (FutureBudgetLineItem) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (frequencyPerMonth == null) {
            if (other.frequencyPerMonth != null)
                return false;
        } else if (!frequencyPerMonth.equals(other.frequencyPerMonth))
            return false;
        if (month == null) {
            if (other.month != null)
                return false;
        } else if (!month.equals(other.month))
            return false;
        if (orgId == null) {
            if (other.orgId != null)
                return false;
        } else if (!orgId.equals(other.orgId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FutureBudgetLineItem [amount=" + amount + ", frequencyPerMonth=" + frequencyPerMonth + ", month="
                + month + ", orgId=" + orgId + "]";
    }

    public FutureBudgetLineItem(Integer orgId, Integer month, BigDecimal amount, Integer frequencyPerMonth) {
        this.orgId = orgId;
        this.month = month;
        this.amount = amount;
        this.frequencyPerMonth = frequencyPerMonth;
    }

    public FutureBudgetLineItem() {};
}
