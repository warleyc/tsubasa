package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCut} entity.
 */
public class MCutDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    @NotNull
    private Integer fpA;

    @NotNull
    private Integer fpB;

    @NotNull
    private Integer fpC;

    @NotNull
    private Integer fpD;

    @NotNull
    private Integer fpE;

    @NotNull
    private Integer fpF;

    @NotNull
    private Integer gkA;

    @NotNull
    private Integer gkB;

    @NotNull
    private Integer showEnvironmentalEffect;

    @NotNull
    private Integer cutType;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer isCombiCut;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFpA() {
        return fpA;
    }

    public void setFpA(Integer fpA) {
        this.fpA = fpA;
    }

    public Integer getFpB() {
        return fpB;
    }

    public void setFpB(Integer fpB) {
        this.fpB = fpB;
    }

    public Integer getFpC() {
        return fpC;
    }

    public void setFpC(Integer fpC) {
        this.fpC = fpC;
    }

    public Integer getFpD() {
        return fpD;
    }

    public void setFpD(Integer fpD) {
        this.fpD = fpD;
    }

    public Integer getFpE() {
        return fpE;
    }

    public void setFpE(Integer fpE) {
        this.fpE = fpE;
    }

    public Integer getFpF() {
        return fpF;
    }

    public void setFpF(Integer fpF) {
        this.fpF = fpF;
    }

    public Integer getGkA() {
        return gkA;
    }

    public void setGkA(Integer gkA) {
        this.gkA = gkA;
    }

    public Integer getGkB() {
        return gkB;
    }

    public void setGkB(Integer gkB) {
        this.gkB = gkB;
    }

    public Integer getShowEnvironmentalEffect() {
        return showEnvironmentalEffect;
    }

    public void setShowEnvironmentalEffect(Integer showEnvironmentalEffect) {
        this.showEnvironmentalEffect = showEnvironmentalEffect;
    }

    public Integer getCutType() {
        return cutType;
    }

    public void setCutType(Integer cutType) {
        this.cutType = cutType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getIsCombiCut() {
        return isCombiCut;
    }

    public void setIsCombiCut(Integer isCombiCut) {
        this.isCombiCut = isCombiCut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MCutDTO mCutDTO = (MCutDTO) o;
        if (mCutDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCutDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCutDTO{" +
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
