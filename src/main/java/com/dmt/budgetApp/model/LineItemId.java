package com.dmt.budgetApp.model;

import java.io.Serializable;

import javax.persistence.Column;

public class LineItemId implements Serializable{

    private static final long serialVersionUID = 7794274429265752634L;

    @Column(name = "org_ID")
    private Integer orgId;

    @Column(name = "month")
    private Integer month;

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

    public LineItemId(Integer orgId, Integer month) {
        this.orgId = orgId;
        this.month = month;
    }

    public LineItemId() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        LineItemId other = (LineItemId) obj;
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
}