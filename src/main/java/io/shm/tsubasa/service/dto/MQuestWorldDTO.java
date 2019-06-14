package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MQuestWorld} entity.
 */
public class MQuestWorldDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer number;

    
    @Lob
    private String name;

    @NotNull
    private Integer startAt;

    
    @Lob
    private String imagePath;

    
    @Lob
    private String backgroundImagePath;

    
    @Lob
    private String description;

    @NotNull
    private Integer stageUnlockPattern;

    private Integer specialRewardContentType;

    private Integer specialRewardContentId;

    @NotNull
    private Integer isEnableCoop;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public Integer getSpecialRewardContentType() {
        return specialRewardContentType;
    }

    public void setSpecialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
    }

    public Integer getSpecialRewardContentId() {
        return specialRewardContentId;
    }

    public void setSpecialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
    }

    public Integer getIsEnableCoop() {
        return isEnableCoop;
    }

    public void setIsEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MQuestWorldDTO mQuestWorldDTO = (MQuestWorldDTO) o;
        if (mQuestWorldDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mQuestWorldDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MQuestWorldDTO{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", name='" + getName() + "'" +
            ", startAt=" + getStartAt() +
            ", imagePath='" + getImagePath() + "'" +
            ", backgroundImagePath='" + getBackgroundImagePath() + "'" +
            ", description='" + getDescription() + "'" +
            ", stageUnlockPattern=" + getStageUnlockPattern() +
            ", specialRewardContentType=" + getSpecialRewardContentType() +
            ", specialRewardContentId=" + getSpecialRewardContentId() +
            ", isEnableCoop=" + getIsEnableCoop() +
            "}";
    }
}
