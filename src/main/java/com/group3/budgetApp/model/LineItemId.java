package com.group3.budgetApp.model;

import java.io.Serializable;

import javax.persistence.Column;

public class LineItemId implements Serializable{

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
}