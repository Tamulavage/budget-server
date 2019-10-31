package com.group3.budgetApp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "future_accounting")
public class FutureBudget {

    @Id
    @Column(name = "org_ID")
    private Integer orgId;
    @Column(name = "org_name")
    private String orgName;

    @Column(name = "freq_per_month")
    private Integer frequencyPerMonth;
    private String direction;

    private BigDecimal januaryAmount; 
    private BigDecimal februaryAmount;
    private BigDecimal marchAmount ;
    private BigDecimal aprilAmount ;
    private BigDecimal mayAmount ;
    private BigDecimal juneAmount ;
    private BigDecimal julyAmount ;
    private BigDecimal augustAmount ;
    private BigDecimal septemberAmount ;
    private BigDecimal octoberAmount ;
    private BigDecimal novemberAmount ;
    private BigDecimal decemberAmount ;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getFrequencyPerMonth() {
        return frequencyPerMonth;
    }

    public void setFrequencyPerMonth(Integer frequencyPerMonth) {
        this.frequencyPerMonth = frequencyPerMonth;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigDecimal getJanuaryAmount() {
        return januaryAmount != null ? januaryAmount : new BigDecimal(0);
    }

    public void setJanuaryAmount(BigDecimal januaryAmount) {
        this.januaryAmount = januaryAmount;
    }

    public BigDecimal getFebruaryAmount() {
        return februaryAmount != null ? februaryAmount : new BigDecimal(0);
    }

    public void setFebruaryAmount(BigDecimal februaryAmount) {
        this.februaryAmount = februaryAmount;
    }

    public BigDecimal getMarchAmount() {
        return marchAmount != null ? marchAmount : new BigDecimal(0);
    }

    public void setMarchAmount(BigDecimal marchAmount) {
        this.marchAmount = marchAmount;
    }

    public BigDecimal getAprilAmount() {
        return aprilAmount != null ? aprilAmount : new BigDecimal(0);
    }

    public void setAprilAmount(BigDecimal aprilAmount) {
        this.aprilAmount = aprilAmount;
    }

    public BigDecimal getMayAmount() {
        return mayAmount != null ? mayAmount : new BigDecimal(0);
    }

    public void setMayAmount(BigDecimal mayAmount) {
        this.mayAmount = mayAmount;
    }

    public BigDecimal getJuneAmount() {
        return juneAmount != null ? juneAmount : new BigDecimal(0);
    }

    public void setJuneAmount(BigDecimal juneAmount) {
        this.juneAmount = juneAmount;
    }

    public BigDecimal getJulyAmount() {
        return julyAmount != null ? julyAmount : new BigDecimal(0);
    }

    public void setJulyAmount(BigDecimal julyAmount) {
        this.julyAmount = julyAmount;
    }

    public BigDecimal getAugustAmount() {
        return augustAmount != null ? augustAmount : new BigDecimal(0);
    }

    public void setAugustAmount(BigDecimal augustAmount) {
        this.augustAmount = augustAmount;
    }

    public BigDecimal getSeptemberAmount() {
        return septemberAmount != null ? septemberAmount : new BigDecimal(0);
    }

    public void setSeptemberAmount(BigDecimal septemberAmount) {
        this.septemberAmount = septemberAmount;
    }

    public BigDecimal getOctoberAmount() {
        return octoberAmount != null ? octoberAmount : new BigDecimal(0);
    }

    public void setOctoberAmount(BigDecimal octoberAmount) {
        this.octoberAmount = octoberAmount;
    }

    public BigDecimal getNovemberAmount() {
        return novemberAmount != null ? novemberAmount : new BigDecimal(0);
    }

    public void setNovemberAmount(BigDecimal novemberAmount) {
        this.novemberAmount = novemberAmount;
    }

    public BigDecimal getDecemberAmount() {
        return decemberAmount != null ? decemberAmount : new BigDecimal(0);
    }

    public void setDecemberAmount(BigDecimal decemberAmount) {
        this.decemberAmount = decemberAmount;
    }

}