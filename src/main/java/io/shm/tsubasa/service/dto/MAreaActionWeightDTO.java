package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MAreaActionWeight} entity.
 */
public class MAreaActionWeightDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer areaType;

    @NotNull
    private Integer dribbleRate;

    @NotNull
    private Integer passingRate;

    @NotNull
    private Integer onetwoRate;

    @NotNull
    private Integer shootRate;

    @NotNull
    private Integer volleyShootRate;

    @NotNull
    private Integer headingShootRate;

    @NotNull
    private Integer tackleRate;

    @NotNull
    private Integer blockRate;

    @NotNull
    private Integer passCutRate;

    @NotNull
    private Integer clearRate;

    @NotNull
    private Integer competeRate;

    @NotNull
    private Integer trapRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getDribbleRate() {
        return dribbleRate;
    }

    public void setDribbleRate(Integer dribbleRate) {
        this.dribbleRate = dribbleRate;
    }

    public Integer getPassingRate() {
        return passingRate;
    }

    public void setPassingRate(Integer passingRate) {
        this.passingRate = passingRate;
    }

    public Integer getOnetwoRate() {
        return onetwoRate;
    }

    public void setOnetwoRate(Integer onetwoRate) {
        this.onetwoRate = onetwoRate;
    }

    public Integer getShootRate() {
        return shootRate;
    }

    public void setShootRate(Integer shootRate) {
        this.shootRate = shootRate;
    }

    public Integer getVolleyShootRate() {
        return volleyShootRate;
    }

    public void setVolleyShootRate(Integer volleyShootRate) {
        this.volleyShootRate = volleyShootRate;
    }

    public Integer getHeadingShootRate() {
        return headingShootRate;
    }

    public void setHeadingShootRate(Integer headingShootRate) {
        this.headingShootRate = headingShootRate;
    }

    public Integer getTackleRate() {
        return tackleRate;
    }

    public void setTackleRate(Integer tackleRate) {
        this.tackleRate = tackleRate;
    }

    public Integer getBlockRate() {
        return blockRate;
    }

    public void setBlockRate(Integer blockRate) {
        this.blockRate = blockRate;
    }

    public Integer getPassCutRate() {
        return passCutRate;
    }

    public void setPassCutRate(Integer passCutRate) {
        this.passCutRate = passCutRate;
    }

    public Integer getClearRate() {
        return clearRate;
    }

    public void setClearRate(Integer clearRate) {
        this.clearRate = clearRate;
    }

    public Integer getCompeteRate() {
        return competeRate;
    }

    public void setCompeteRate(Integer competeRate) {
        this.competeRate = competeRate;
    }

    public Integer getTrapRate() {
        return trapRate;
    }

    public void setTrapRate(Integer trapRate) {
        this.trapRate = trapRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MAreaActionWeightDTO mAreaActionWeightDTO = (MAreaActionWeightDTO) o;
        if (mAreaActionWeightDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mAreaActionWeightDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MAreaActionWeightDTO{" +
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
