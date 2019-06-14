package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestCoop} entity.
 */
public class MQuestCoopDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer effectRarity;

    @NotNull
    private Integer effectLevelFrom;

    @NotNull
    private Integer effectLevelTo;

    @NotNull
    private Integer choose1Weight;

    @NotNull
    private Integer choose2Weight;


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

    public Integer getEffectRarity() {
        return effectRarity;
    }

    public void setEffectRarity(Integer effectRarity) {
        this.effectRarity = effectRarity;
    }

    public Integer getEffectLevelFrom() {
        return effectLevelFrom;
    }

    public void setEffectLevelFrom(Integer effectLevelFrom) {
        this.effectLevelFrom = effectLevelFrom;
    }

    public Integer getEffectLevelTo() {
        return effectLevelTo;
    }

    public void setEffectLevelTo(Integer effectLevelTo) {
        this.effectLevelTo = effectLevelTo;
    }

    public Integer getChoose1Weight() {
        return choose1Weight;
    }

    public void setChoose1Weight(Integer choose1Weight) {
        this.choose1Weight = choose1Weight;
    }

    public Integer getChoose2Weight() {
        return choose2Weight;
    }

    public void setChoose2Weight(Integer choose2Weight) {
        this.choose2Weight = choose2Weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestCoopDTO mQuestCoopDTO = (MQuestCoopDTO) o;
        if (mQuestCoopDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestCoopDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestCoopDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", effectRarity=" + getEffectRarity() +
            ", effectLevelFrom=" + getEffectLevelFrom() +
            ", effectLevelTo=" + getEffectLevelTo() +
            ", choose1Weight=" + getChoose1Weight() +
            ", choose2Weight=" + getChoose2Weight() +
            "}";
    }
}
