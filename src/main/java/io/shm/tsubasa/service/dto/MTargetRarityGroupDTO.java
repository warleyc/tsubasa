package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTargetRarityGroup} entity.
 */
public class MTargetRarityGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer cardRarity;


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

    public Integer getCardRarity() {
        return cardRarity;
    }

    public void setCardRarity(Integer cardRarity) {
        this.cardRarity = cardRarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTargetRarityGroupDTO mTargetRarityGroupDTO = (MTargetRarityGroupDTO) o;
        if (mTargetRarityGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTargetRarityGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTargetRarityGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", cardRarity=" + getCardRarity() +
            "}";
    }
}
