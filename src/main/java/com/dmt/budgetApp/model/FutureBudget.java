package com.dmt.budgetApp.model;

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
    private BigDecimal currentAmount ;

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

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    @Override
    public String toString() {
        return "FutureBudget [aprilAmount=" + aprilAmount + ", augustAmount=" + augustAmount + ", currentAmount="
                + currentAmount + ", decemberAmount=" + decemberAmount + ", direction=" + direction
                + ", februaryAmount=" + februaryAmount + ", frequencyPerMonth=" + frequencyPerMonth + ", januaryAmount="
                + januaryAmount + ", julyAmount=" + julyAmount + ", juneAmount=" + juneAmount + ", marchAmount="
                + marchAmount + ", mayAmount=" + mayAmount + ", novemberAmount=" + novemberAmount + ", octoberAmount="
                + octoberAmount + ", orgId=" + orgId + ", orgName=" + orgName + ", septemberAmount=" + septemberAmount
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aprilAmount == null) ? 0 : aprilAmount.hashCode());
        result = prime * result + ((augustAmount == null) ? 0 : augustAmount.hashCode());
        result = prime * result + ((currentAmount == null) ? 0 : currentAmount.hashCode());
        result = prime * result + ((decemberAmount == null) ? 0 : decemberAmount.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((februaryAmount == null) ? 0 : februaryAmount.hashCode());
        result = prime * result + ((frequencyPerMonth == null) ? 0 : frequencyPerMonth.hashCode());
        result = prime * result + ((januaryAmount == null) ? 0 : januaryAmount.hashCode());
        result = prime * result + ((julyAmount == null) ? 0 : julyAmount.hashCode());
        result = prime * result + ((juneAmount == null) ? 0 : juneAmount.hashCode());
        result = prime * result + ((marchAmount == null) ? 0 : marchAmount.hashCode());
        result = prime * result + ((mayAmount == null) ? 0 : mayAmount.hashCode());
        result = prime * result + ((novemberAmount == null) ? 0 : novemberAmount.hashCode());
        result = prime * result + ((octoberAmount == null) ? 0 : octoberAmount.hashCode());
        result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
        result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
        result = prime * result + ((septemberAmount == null) ? 0 : septemberAmount.hashCode());
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
        FutureBudget other = (FutureBudget) obj;
        if (aprilAmount == null) {
            if (other.aprilAmount != null)
                return false;
        } else if (!aprilAmount.equals(other.aprilAmount))
            return false;
        if (augustAmount == null) {
            if (other.augustAmount != null)
                return false;
        } else if (!augustAmount.equals(other.augustAmount))
            return false;
        if (currentAmount == null) {
            if (other.currentAmount != null)
                return false;
        } else if (!currentAmount.equals(other.currentAmount))
            return false;
        if (decemberAmount == null) {
            if (other.decemberAmount != null)
                return false;
        } else if (!decemberAmount.equals(other.decemberAmount))
            return false;
        if (direction == null) {
            if (other.direction != null)
                return false;
        } else if (!direction.equals(other.direction))
            return false;
        if (februaryAmount == null) {
            if (other.februaryAmount != null)
                return false;
        } else if (!februaryAmount.equals(other.februaryAmount))
            return false;
        if (frequencyPerMonth == null) {
            if (other.frequencyPerMonth != null)
                return false;
        } else if (!frequencyPerMonth.equals(other.frequencyPerMonth))
            return false;
        if (januaryAmount == null) {
            if (other.januaryAmount != null)
                return false;
        } else if (!januaryAmount.equals(other.januaryAmount))
            return false;
        if (julyAmount == null) {
            if (other.julyAmount != null)
                return false;
        } else if (!julyAmount.equals(other.julyAmount))
            return false;
        if (juneAmount == null) {
            if (other.juneAmount != null)
                return false;
        } else if (!juneAmount.equals(other.juneAmount))
            return false;
        if (marchAmount == null) {
            if (other.marchAmount != null)
                return false;
        } else if (!marchAmount.equals(other.marchAmount))
            return false;
        if (mayAmount == null) {
            if (other.mayAmount != null)
                return false;
        } else if (!mayAmount.equals(other.mayAmount))
            return false;
        if (novemberAmount == null) {
            if (other.novemberAmount != null)
                return false;
        } else if (!novemberAmount.equals(other.novemberAmount))
            return false;
        if (octoberAmount == null) {
            if (other.octoberAmount != null)
                return false;
        } else if (!octoberAmount.equals(other.octoberAmount))
            return false;
        if (orgId == null) {
            if (other.orgId != null)
                return false;
        } else if (!orgId.equals(other.orgId))
            return false;
        if (orgName == null) {
            if (other.orgName != null)
                return false;
        } else if (!orgName.equals(other.orgName))
            return false;
        if (septemberAmount == null) {
            if (other.septemberAmount != null)
                return false;
        } else if (!septemberAmount.equals(other.septemberAmount))
            return false;
        return true;
    }

}