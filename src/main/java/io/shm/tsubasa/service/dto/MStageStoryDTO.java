package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MStageStory} entity.
 */
public class MStageStoryDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer stageId;

    @NotNull
    private Integer eventType;

    @Lob
    private String mainStoryAsset;

    @Lob
    private String kickoffStoryAsset;

    @Lob
    private String halftimeStoryAsset;

    @Lob
    private String resultStoryAsset;


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

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getMainStoryAsset() {
        return mainStoryAsset;
    }

    public void setMainStoryAsset(String mainStoryAsset) {
        this.mainStoryAsset = mainStoryAsset;
    }

    public String getKickoffStoryAsset() {
        return kickoffStoryAsset;
    }

    public void setKickoffStoryAsset(String kickoffStoryAsset) {
        this.kickoffStoryAsset = kickoffStoryAsset;
    }

    public String getHalftimeStoryAsset() {
        return halftimeStoryAsset;
    }

    public void setHalftimeStoryAsset(String halftimeStoryAsset) {
        this.halftimeStoryAsset = halftimeStoryAsset;
    }

    public String getResultStoryAsset() {
        return resultStoryAsset;
    }

    public void setResultStoryAsset(String resultStoryAsset) {
        this.resultStoryAsset = resultStoryAsset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MStageStoryDTO mStageStoryDTO = (MStageStoryDTO) o;
        if (mStageStoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mStageStoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MStageStoryDTO{" +
            "id=" + getId() +
            ", stageId=" + getStageId() +
            ", eventType=" + getEventType() +
            ", mainStoryAsset='" + getMainStoryAsset() + "'" +
            ", kickoffStoryAsset='" + getKickoffStoryAsset() + "'" +
            ", halftimeStoryAsset='" + getHalftimeStoryAsset() + "'" +
            ", resultStoryAsset='" + getResultStoryAsset() + "'" +
            "}";
    }
}
