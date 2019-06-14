package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestGoalReward} entity.
 */
public class MQuestGoalRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer weight;

    
    @Lob
    private String assetName;

    @NotNull
    private Integer contentType;

    private Integer contentId;

    @NotNull
    private Integer contentAmount;


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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestGoalRewardDTO mQuestGoalRewardDTO = (MQuestGoalRewardDTO) o;
        if (mQuestGoalRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestGoalRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestGoalRewardDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", weight=" + getWeight() +
            ", assetName='" + getAssetName() + "'" +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
