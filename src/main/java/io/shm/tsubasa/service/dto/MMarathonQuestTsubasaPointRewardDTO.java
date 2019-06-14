package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward} entity.
 */
public class MMarathonQuestTsubasaPointRewardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer stageId;

    @NotNull
    private Integer tsubasaPoint;

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

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getTsubasaPoint() {
        return tsubasaPoint;
    }

    public void setTsubasaPoint(Integer tsubasaPoint) {
        this.tsubasaPoint = tsubasaPoint;
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

        MMarathonQuestTsubasaPointRewardDTO mMarathonQuestTsubasaPointRewardDTO = (MMarathonQuestTsubasaPointRewardDTO) o;
        if (mMarathonQuestTsubasaPointRewardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMarathonQuestTsubasaPointRewardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMarathonQuestTsubasaPointRewardDTO{" +
            "id=" + getId() +
            ", stageId=" + getStageId() +
            ", tsubasaPoint=" + getTsubasaPoint() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
