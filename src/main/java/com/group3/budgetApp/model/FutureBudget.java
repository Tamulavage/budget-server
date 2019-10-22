package com.group3.budgetApp.model;

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

    private Double januaryAmount = 0.0d;
    private Double februaryAmount = 0.0d;
    private Double marchAmount = 0.0d;
    private Double aprilAmount = 0.0d;
    private Double mayAmount = 0.0d;
    private Double juneAmount = 0.0d;
    private Double julyAmount = 0.0d;
    private Double augustAmount = 0.0d;
    private Double septemberAmount = 0.0d;
    private Double octoberAmount = 0.0d;
    private Double novemberAmount = 0.0d;
    private Double decemberAmount = 0.0d;            
    

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

    public Double getJanuaryAmount() {
    return januaryAmount;
    }

    public void setJanuaryAmount(Double januaryAmount) {
    this.januaryAmount = januaryAmount;
    }

    public Double getFebruaryAmount() {
    return februaryAmount;
    }

    public void setFebruaryAmount(Double februaryAmount) {
    this.februaryAmount = februaryAmount;
    }

    public Double getMarchAmount() {
    return marchAmount;
    }

    public void setMarchAmount(Double marchAmount) {
    this.marchAmount = marchAmount;
    }

    public Double getAprilAmount() {
        return aprilAmount;
    }

    public void setAprilAmount(Double aprilAmount) {
        this.aprilAmount = aprilAmount;
    }

    public Double getMayAmount() {
        return mayAmount;
    }

    public void setMayAmount(Double mayAmount) {
        this.mayAmount = mayAmount;
    }

    public Double getJuneAmount() {
        return juneAmount;
    }

    public void setJuneAmount(Double juneAmount) {
        this.juneAmount = juneAmount;
    }

    public Double getJulyAmount() {
        return julyAmount;
    }

    public void setJulyAmount(Double julyAmount) {
        this.julyAmount = julyAmount;
    }

    public Double getAugustAmount() {
        return augustAmount;
    }

    public void setAugustAmount(Double augustAmount) {
        this.augustAmount = augustAmount;
    }

    public Double getSeptemberAmount() {
        return septemberAmount;
    }

    public void setSeptemberAmount(Double septemberAmount) {
        this.septemberAmount = septemberAmount;
    }

    public Double getOctoberAmount() {
        return octoberAmount;
    }

    public void setOctoberAmount(Double octoberAmount) {
        this.octoberAmount = octoberAmount;
    }

    public Double getNovemberAmount() {
        return novemberAmount;
    }

    public void setNovemberAmount(Double novemberAmount) {
        this.novemberAmount = novemberAmount;
    }

    public Double getDecemberAmount() {
        return decemberAmount;
    }

    public void setDecemberAmount(Double decemberAmount) {
        this.decemberAmount = decemberAmount;
    }

}