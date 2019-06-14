package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTsubasaPoint} entity.
 */
public class MTsubasaPointDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer matchType;

    @NotNull
    private Integer pointType;

    @NotNull
    private Integer calcType;

    @NotNull
    private Integer aValue;

    @NotNull
    private Integer bValue;

    @NotNull
    private Integer orderNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public Integer getCalcType() {
        return calcType;
    }

    public void setCalcType(Integer calcType) {
        this.calcType = calcType;
    }

    public Integer getaValue() {
        return aValue;
    }

    public void setaValue(Integer aValue) {
        this.aValue = aValue;
    }

    public Integer getbValue() {
        return bValue;
    }

    public void setbValue(Integer bValue) {
        this.bValue = bValue;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTsubasaPointDTO mTsubasaPointDTO = (MTsubasaPointDTO) o;
        if (mTsubasaPointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTsubasaPointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTsubasaPointDTO{" +
            "id=" + getId() +
            ", matchType=" + getMatchType() +
            ", pointType=" + getPointType() +
            ", calcType=" + getCalcType() +
            ", aValue=" + getaValue() +
            ", bValue=" + getbValue() +
            ", orderNum=" + getOrderNum() +
            "}";
    }
}
