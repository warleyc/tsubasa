package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionBall} entity.
 */
public class MGachaRenditionBallDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer renditionId;

    @NotNull
    private Integer isSsr;

    @NotNull
    private Integer weight;

    
    @Lob
    private String ballTextureName;

    
    @Lob
    private String trajectoryNormalTextureName;

    
    @Lob
    private String trajectoryGoldTextureName;

    
    @Lob
    private String trajectoryRainbowTextureName;


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

    public Integer getIsSsr() {
        return isSsr;
    }

    public void setIsSsr(Integer isSsr) {
        this.isSsr = isSsr;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBallTextureName() {
        return ballTextureName;
    }

    public void setBallTextureName(String ballTextureName) {
        this.ballTextureName = ballTextureName;
    }

    public String getTrajectoryNormalTextureName() {
        return trajectoryNormalTextureName;
    }

    public void setTrajectoryNormalTextureName(String trajectoryNormalTextureName) {
        this.trajectoryNormalTextureName = trajectoryNormalTextureName;
    }

    public String getTrajectoryGoldTextureName() {
        return trajectoryGoldTextureName;
    }

    public void setTrajectoryGoldTextureName(String trajectoryGoldTextureName) {
        this.trajectoryGoldTextureName = trajectoryGoldTextureName;
    }

    public String getTrajectoryRainbowTextureName() {
        return trajectoryRainbowTextureName;
    }

    public void setTrajectoryRainbowTextureName(String trajectoryRainbowTextureName) {
        this.trajectoryRainbowTextureName = trajectoryRainbowTextureName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionBallDTO mGachaRenditionBallDTO = (MGachaRenditionBallDTO) o;
        if (mGachaRenditionBallDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionBallDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionBallDTO{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", isSsr=" + getIsSsr() +
            ", weight=" + getWeight() +
            ", ballTextureName='" + getBallTextureName() + "'" +
            ", trajectoryNormalTextureName='" + getTrajectoryNormalTextureName() + "'" +
            ", trajectoryGoldTextureName='" + getTrajectoryGoldTextureName() + "'" +
            ", trajectoryRainbowTextureName='" + getTrajectoryRainbowTextureName() + "'" +
            "}";
    }
}
