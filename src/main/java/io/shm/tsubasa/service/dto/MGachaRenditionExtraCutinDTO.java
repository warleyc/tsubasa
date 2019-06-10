package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRenditionExtraCutin} entity.
 */
public class MGachaRenditionExtraCutinDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer renditionId;

    
    @Lob
    private String mainPrefabName;

    
    @Lob
    private String voiceStartCutIn;

    
    @Lob
    private String serif;


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

    public String getMainPrefabName() {
        return mainPrefabName;
    }

    public void setMainPrefabName(String mainPrefabName) {
        this.mainPrefabName = mainPrefabName;
    }

    public String getVoiceStartCutIn() {
        return voiceStartCutIn;
    }

    public void setVoiceStartCutIn(String voiceStartCutIn) {
        this.voiceStartCutIn = voiceStartCutIn;
    }

    public String getSerif() {
        return serif;
    }

    public void setSerif(String serif) {
        this.serif = serif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionExtraCutinDTO mGachaRenditionExtraCutinDTO = (MGachaRenditionExtraCutinDTO) o;
        if (mGachaRenditionExtraCutinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionExtraCutinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionExtraCutinDTO{" +
            "id=" + getId() +
            ", renditionId=" + getRenditionId() +
            ", mainPrefabName='" + getMainPrefabName() + "'" +
            ", voiceStartCutIn='" + getVoiceStartCutIn() + "'" +
            ", serif='" + getSerif() + "'" +
            "}";
    }
}
