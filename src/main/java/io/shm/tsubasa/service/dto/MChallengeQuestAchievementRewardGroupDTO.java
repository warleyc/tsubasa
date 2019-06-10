package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup} entity.
 */
public class MChallengeQuestAchievementRewardGroupDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

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

        MChallengeQuestAchievementRewardGroupDTO mChallengeQuestAchievementRewardGroupDTO = (MChallengeQuestAchievementRewardGroupDTO) o;
        if (mChallengeQuestAchievementRewardGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mChallengeQuestAchievementRewardGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MChallengeQuestAchievementRewardGroupDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
