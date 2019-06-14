package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MLuckRateGroup} entity.
 */
public class MLuckRateGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer rate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MLuckRateGroupDTO mLuckRateGroupDTO = (MLuckRateGroupDTO) o;
        if (mLuckRateGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mLuckRateGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MLuckRateGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", rarity=" + getRarity() +
            ", rate=" + getRate() +
            "}";
    }
}
