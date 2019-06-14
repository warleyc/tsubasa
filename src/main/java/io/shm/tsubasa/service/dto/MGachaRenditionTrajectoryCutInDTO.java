package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn} entity.
 */
public class MGachaRenditionTrajectoryCutInDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer renditionId;

    @NotNull
    private Integer trajectoryType;

    
    @Lob
    private String spriteName;

    @NotNull
    private Integer weight;

    
    @Lob
    private String voice;

    @NotNull
    private Integer exceptKickerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRenditionId() {
        return renditionId;
    }

    public void setRenditionId(Integer renditionId) {
        this.renditionId = renditionId;
    }

    public Integer getTrajectoryType() {
        return trajectoryType;
    }

    public void setTrajectoryType(Integer trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Integer getExceptKickerId() {
        return exceptKickerId;
    }

    public void setExceptKickerId(Integer exceptKickerId) {
        this.exceptKickerId = exceptKickerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionTrajectoryCutInDTO mGachaRenditionTrajectoryCutInDTO = (MGachaRenditionTrajectoryCutInDTO) o;
        if (mGachaRenditionTrajectoryCutInDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionTrajectoryCutInDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionTrajectoryCutInDTO{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", trajectoryType=" + getTrajectoryType() +
            ", spriteName='" + getSpriteName() + "'" +
            ", weight=" + getWeight() +
            ", voice='" + getVoice() + "'" +
            ", exceptKickerId=" + getExceptKickerId() +
            "}";
    }
}
