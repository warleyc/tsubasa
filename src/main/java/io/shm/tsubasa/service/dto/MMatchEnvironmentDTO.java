package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MMatchEnvironment} entity.
 */
public class MMatchEnvironmentDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer category;

    
    @Lob
    private String skyTex;

    
    @Lob
    private String ball;

    
    @Lob
    private String stadium;

    @Lob
    private String rainyAssetName;

    @NotNull
    private Integer isPlayRainySound;

    
    @Lob
    private String offenseStartBgm;

    
    @Lob
    private String offenseStopBgm;

    
    @Lob
    private String defenseStartBgm;

    
    @Lob
    private String defenseStopBgm;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSkyTex() {
        return skyTex;
    }

    public void setSkyTex(String skyTex) {
        this.skyTex = skyTex;
    }

    public String getBall() {
        return ball;
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getRainyAssetName() {
        return rainyAssetName;
    }

    public void setRainyAssetName(String rainyAssetName) {
        this.rainyAssetName = rainyAssetName;
    }

    public Integer getIsPlayRainySound() {
        return isPlayRainySound;
    }

    public void setIsPlayRainySound(Integer isPlayRainySound) {
        this.isPlayRainySound = isPlayRainySound;
    }

    public String getOffenseStartBgm() {
        return offenseStartBgm;
    }

    public void setOffenseStartBgm(String offenseStartBgm) {
        this.offenseStartBgm = offenseStartBgm;
    }

    public String getOffenseStopBgm() {
        return offenseStopBgm;
    }

    public void setOffenseStopBgm(String offenseStopBgm) {
        this.offenseStopBgm = offenseStopBgm;
    }

    public String getDefenseStartBgm() {
        return defenseStartBgm;
    }

    public void setDefenseStartBgm(String defenseStartBgm) {
        this.defenseStartBgm = defenseStartBgm;
    }

    public String getDefenseStopBgm() {
        return defenseStopBgm;
    }

    public void setDefenseStopBgm(String defenseStopBgm) {
        this.defenseStopBgm = defenseStopBgm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MMatchEnvironmentDTO mMatchEnvironmentDTO = (MMatchEnvironmentDTO) o;
        if (mMatchEnvironmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mMatchEnvironmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MMatchEnvironmentDTO{" +
            "id=" + getId() +
            ", category=" + getCategory() +
            ", skyTex='" + getSkyTex() + "'" +
            ", ball='" + getBall() + "'" +
            ", stadium='" + getStadium() + "'" +
            ", rainyAssetName='" + getRainyAssetName() + "'" +
            ", isPlayRainySound=" + getIsPlayRainySound() +
            ", offenseStartBgm='" + getOffenseStartBgm() + "'" +
            ", offenseStopBgm='" + getOffenseStopBgm() + "'" +
            ", defenseStartBgm='" + getDefenseStartBgm() + "'" +
            ", defenseStopBgm='" + getDefenseStopBgm() + "'" +
            "}";
    }
}
