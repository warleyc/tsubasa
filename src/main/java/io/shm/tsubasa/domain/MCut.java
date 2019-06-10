package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MCut.
 */
@Entity
@Table(name = "m_cut")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "fp_a", nullable = false)
    private Integer fpA;

    @NotNull
    @Column(name = "fp_b", nullable = false)
    private Integer fpB;

    @NotNull
    @Column(name = "fp_c", nullable = false)
    private Integer fpC;

    @NotNull
    @Column(name = "fp_d", nullable = false)
    private Integer fpD;

    @NotNull
    @Column(name = "fp_e", nullable = false)
    private Integer fpE;

    @NotNull
    @Column(name = "fp_f", nullable = false)
    private Integer fpF;

    @NotNull
    @Column(name = "gk_a", nullable = false)
    private Integer gkA;

    @NotNull
    @Column(name = "gk_b", nullable = false)
    private Integer gkB;

    @NotNull
    @Column(name = "show_environmental_effect", nullable = false)
    private Integer showEnvironmentalEffect;

    @NotNull
    @Column(name = "cut_type", nullable = false)
    private Integer cutType;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "is_combi_cut", nullable = false)
    private Integer isCombiCut;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MCut name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFpA() {
        return fpA;
    }

    public MCut fpA(Integer fpA) {
        this.fpA = fpA;
        return this;
    }

    public void setFpA(Integer fpA) {
        this.fpA = fpA;
    }

    public Integer getFpB() {
        return fpB;
    }

    public MCut fpB(Integer fpB) {
        this.fpB = fpB;
        return this;
    }

    public void setFpB(Integer fpB) {
        this.fpB = fpB;
    }

    public Integer getFpC() {
        return fpC;
    }

    public MCut fpC(Integer fpC) {
        this.fpC = fpC;
        return this;
    }

    public void setFpC(Integer fpC) {
        this.fpC = fpC;
    }

    public Integer getFpD() {
        return fpD;
    }

    public MCut fpD(Integer fpD) {
        this.fpD = fpD;
        return this;
    }

    public void setFpD(Integer fpD) {
        this.fpD = fpD;
    }

    public Integer getFpE() {
        return fpE;
    }

    public MCut fpE(Integer fpE) {
        this.fpE = fpE;
        return this;
    }

    public void setFpE(Integer fpE) {
        this.fpE = fpE;
    }

    public Integer getFpF() {
        return fpF;
    }

    public MCut fpF(Integer fpF) {
        this.fpF = fpF;
        return this;
    }

    public void setFpF(Integer fpF) {
        this.fpF = fpF;
    }

    public Integer getGkA() {
        return gkA;
    }

    public MCut gkA(Integer gkA) {
        this.gkA = gkA;
        return this;
    }

    public void setGkA(Integer gkA) {
        this.gkA = gkA;
    }

    public Integer getGkB() {
        return gkB;
    }

    public MCut gkB(Integer gkB) {
        this.gkB = gkB;
        return this;
    }

    public void setGkB(Integer gkB) {
        this.gkB = gkB;
    }

    public Integer getShowEnvironmentalEffect() {
        return showEnvironmentalEffect;
    }

    public MCut showEnvironmentalEffect(Integer showEnvironmentalEffect) {
        this.showEnvironmentalEffect = showEnvironmentalEffect;
        return this;
    }

    public void setShowEnvironmentalEffect(Integer showEnvironmentalEffect) {
        this.showEnvironmentalEffect = showEnvironmentalEffect;
    }

    public Integer getCutType() {
        return cutType;
    }

    public MCut cutType(Integer cutType) {
        this.cutType = cutType;
        return this;
    }

    public void setCutType(Integer cutType) {
        this.cutType = cutType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MCut groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getIsCombiCut() {
        return isCombiCut;
    }

    public MCut isCombiCut(Integer isCombiCut) {
        this.isCombiCut = isCombiCut;
        return this;
    }

    public void setIsCombiCut(Integer isCombiCut) {
        this.isCombiCut = isCombiCut;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCut)) {
            return false;
        }
        return id != null && id.equals(((MCut) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCut{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fpA=" + getFpA() +
            ", fpB=" + getFpB() +
            ", fpC=" + getFpC() +
            ", fpD=" + getFpD() +
            ", fpE=" + getFpE() +
            ", fpF=" + getFpF() +
            ", gkA=" + getGkA() +
            ", gkB=" + getGkB() +
            ", showEnvironmentalEffect=" + getShowEnvironmentalEffect() +
            ", cutType=" + getCutType() +
            ", groupId=" + getGroupId() +
            ", isCombiCut=" + getIsCombiCut() +
            "}";
    }
}
