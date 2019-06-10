package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionFirstGimmick} entity.
 */
public class MGachaRenditionFirstGimmickDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer birdNum;

    @NotNull
    private Integer isTickerTape;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBirdNum() {
        return birdNum;
    }

    public void setBirdNum(Integer birdNum) {
        this.birdNum = birdNum;
    }

    public Integer getIsTickerTape() {
        return isTickerTape;
    }

    public void setIsTickerTape(Integer isTickerTape) {
        this.isTickerTape = isTickerTape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionFirstGimmickDTO mGachaRenditionFirstGimmickDTO = (MGachaRenditionFirstGimmickDTO) o;
        if (mGachaRenditionFirstGimmickDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionFirstGimmickDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionFirstGimmickDTO{" +
            "id=" + getId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", birdNum=" + getBirdNum() +
            ", isTickerTape=" + getIsTickerTape() +
            "}";
    }
}
