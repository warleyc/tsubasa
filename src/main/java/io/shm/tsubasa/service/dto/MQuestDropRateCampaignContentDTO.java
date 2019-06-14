package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestDropRateCampaignContent} entity.
 */
public class MQuestDropRateCampaignContentDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer groupId;

    @NotNull
    private Integer contentType;

    private Integer contentId;

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

        MQuestDropRateCampaignContentDTO mQuestDropRateCampaignContentDTO = (MQuestDropRateCampaignContentDTO) o;
        if (mQuestDropRateCampaignContentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestDropRateCampaignContentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestDropRateCampaignContentDTO{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", rate=" + getRate() +
            "}";
    }
}
