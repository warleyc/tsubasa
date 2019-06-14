package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MAchievement} entity.
 */
public class MAchievementDTO implements Serializable {

    private Long id;

    
    @Lob
    private String achievementId;

    
    @Lob
    private String name;

    @NotNull
    private Integer type;

    @NotNull
    private Integer missionId;


    private Long mmissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public Long getMmissionId() {
        return mmissionId;
    }

    public void setMmissionId(Long mMissionId) {
        this.mmissionId = mMissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MAchievementDTO mAchievementDTO = (MAchievementDTO) o;
        if (mAchievementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mAchievementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MAchievementDTO{" +
            "id=" + getId() +
            ", achievementId='" + getAchievementId() + "'" +
            ", name='" + getName() + "'" +
            ", type=" + getType() +
            ", missionId=" + getMissionId() +
            ", mmission=" + getMmissionId() +
            "}";
    }
}
