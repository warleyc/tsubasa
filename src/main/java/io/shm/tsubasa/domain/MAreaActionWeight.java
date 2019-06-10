package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MAreaActionWeight.
 */
@Entity
@Table(name = "m_area_action_weight")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAreaActionWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "area_type", nullable = false)
    private Integer areaType;

    @NotNull
    @Column(name = "dribble_rate", nullable = false)
    private Integer dribbleRate;

    @NotNull
    @Column(name = "passing_rate", nullable = false)
    private Integer passingRate;

    @NotNull
    @Column(name = "onetwo_rate", nullable = false)
    private Integer onetwoRate;

    @NotNull
    @Column(name = "shoot_rate", nullable = false)
    private Integer shootRate;

    @NotNull
    @Column(name = "volley_shoot_rate", nullable = false)
    private Integer volleyShootRate;

    @NotNull
    @Column(name = "heading_shoot_rate", nullable = false)
    private Integer headingShootRate;

    @NotNull
    @Column(name = "tackle_rate", nullable = false)
    private Integer tackleRate;

    @NotNull
    @Column(name = "block_rate", nullable = false)
    private Integer blockRate;

    @NotNull
    @Column(name = "pass_cut_rate", nullable = false)
    private Integer passCutRate;

    @NotNull
    @Column(name = "clear_rate", nullable = false)
    private Integer clearRate;

    @NotNull
    @Column(name = "compete_rate", nullable = false)
    private Integer competeRate;

    @NotNull
    @Column(name = "trap_rate", nullable = false)
    private Integer trapRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public MAreaActionWeight areaType(Integer areaType) {
        this.areaType = areaType;
        return this;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getDribbleRate() {
        return dribbleRate;
    }

    public MAreaActionWeight dribbleRate(Integer dribbleRate) {
        this.dribbleRate = dribbleRate;
        return this;
    }

    public void setDribbleRate(Integer dribbleRate) {
        this.dribbleRate = dribbleRate;
    }

    public Integer getPassingRate() {
        return passingRate;
    }

    public MAreaActionWeight passingRate(Integer passingRate) {
        this.passingRate = passingRate;
        return this;
    }

    public void setPassingRate(Integer passingRate) {
        this.passingRate = passingRate;
    }

    public Integer getOnetwoRate() {
        return onetwoRate;
    }

    public MAreaActionWeight onetwoRate(Integer onetwoRate) {
        this.onetwoRate = onetwoRate;
        return this;
    }

    public void setOnetwoRate(Integer onetwoRate) {
        this.onetwoRate = onetwoRate;
    }

    public Integer getShootRate() {
        return shootRate;
    }

    public MAreaActionWeight shootRate(Integer shootRate) {
        this.shootRate = shootRate;
        return this;
    }

    public void setShootRate(Integer shootRate) {
        this.shootRate = shootRate;
    }

    public Integer getVolleyShootRate() {
        return volleyShootRate;
    }

    public MAreaActionWeight volleyShootRate(Integer volleyShootRate) {
        this.volleyShootRate = volleyShootRate;
        return this;
    }

    public void setVolleyShootRate(Integer volleyShootRate) {
        this.volleyShootRate = volleyShootRate;
    }

    public Integer getHeadingShootRate() {
        return headingShootRate;
    }

    public MAreaActionWeight headingShootRate(Integer headingShootRate) {
        this.headingShootRate = headingShootRate;
        return this;
    }

    public void setHeadingShootRate(Integer headingShootRate) {
        this.headingShootRate = headingShootRate;
    }

    public Integer getTackleRate() {
        return tackleRate;
    }

    public MAreaActionWeight tackleRate(Integer tackleRate) {
        this.tackleRate = tackleRate;
        return this;
    }

    public void setTackleRate(Integer tackleRate) {
        this.tackleRate = tackleRate;
    }

    public Integer getBlockRate() {
        return blockRate;
    }

    public MAreaActionWeight blockRate(Integer blockRate) {
        this.blockRate = blockRate;
        return this;
    }

    public void setBlockRate(Integer blockRate) {
        this.blockRate = blockRate;
    }

    public Integer getPassCutRate() {
        return passCutRate;
    }

    public MAreaActionWeight passCutRate(Integer passCutRate) {
        this.passCutRate = passCutRate;
        return this;
    }

    public void setPassCutRate(Integer passCutRate) {
        this.passCutRate = passCutRate;
    }

    public Integer getClearRate() {
        return clearRate;
    }

    public MAreaActionWeight clearRate(Integer clearRate) {
        this.clearRate = clearRate;
        return this;
    }

    public void setClearRate(Integer clearRate) {
        this.clearRate = clearRate;
    }

    public Integer getCompeteRate() {
        return competeRate;
    }

    public MAreaActionWeight competeRate(Integer competeRate) {
        this.competeRate = competeRate;
        return this;
    }

    public void setCompeteRate(Integer competeRate) {
        this.competeRate = competeRate;
    }

    public Integer getTrapRate() {
        return trapRate;
    }

    public MAreaActionWeight trapRate(Integer trapRate) {
        this.trapRate = trapRate;
        return this;
    }

    public void setTrapRate(Integer trapRate) {
        this.trapRate = trapRate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAreaActionWeight)) {
            return false;
        }
        return id != null && id.equals(((MAreaActionWeight) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAreaActionWeight{" +
            "id=" + getId() +
            ", areaType=" + getAreaType() +
            ", dribbleRate=" + getDribbleRate() +
            ", passingRate=" + getPassingRate() +
            ", onetwoRate=" + getOnetwoRate() +
            ", shootRate=" + getShootRate() +
            ", volleyShootRate=" + getVolleyShootRate() +
            ", headingShootRate=" + getHeadingShootRate() +
            ", tackleRate=" + getTackleRate() +
            ", blockRate=" + getBlockRate() +
            ", passCutRate=" + getPassCutRate() +
            ", clearRate=" + getClearRate() +
            ", competeRate=" + getCompeteRate() +
            ", trapRate=" + getTrapRate() +
            "}";
    }
}
