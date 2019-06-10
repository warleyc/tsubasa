package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MGachaRendition} entity.
 */
public class MGachaRenditionDTO implements Serializable {

    private Long id;

    
    @Lob
    private String mainPrefabName;

    
    @Lob
    private String resultExpectedUpPrefabName;

    @Lob
    private String resultQuestionPrefabName;

    
    @Lob
    private String soundSwitchEventName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainPrefabName() {
        return mainPrefabName;
    }

    public void setMainPrefabName(String mainPrefabName) {
        this.mainPrefabName = mainPrefabName;
    }

    public String getResultExpectedUpPrefabName() {
        return resultExpectedUpPrefabName;
    }

    public void setResultExpectedUpPrefabName(String resultExpectedUpPrefabName) {
        this.resultExpectedUpPrefabName = resultExpectedUpPrefabName;
    }

    public String getResultQuestionPrefabName() {
        return resultQuestionPrefabName;
    }

    public void setResultQuestionPrefabName(String resultQuestionPrefabName) {
        this.resultQuestionPrefabName = resultQuestionPrefabName;
    }

    public String getSoundSwitchEventName() {
        return soundSwitchEventName;
    }

    public void setSoundSwitchEventName(String soundSwitchEventName) {
        this.soundSwitchEventName = soundSwitchEventName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MGachaRenditionDTO mGachaRenditionDTO = (MGachaRenditionDTO) o;
        if (mGachaRenditionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mGachaRenditionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MGachaRenditionDTO{" +
            "id=" + getId() +
            ", mainPrefabName='" + getMainPrefabName() + "'" +
            ", resultExpectedUpPrefabName='" + getResultExpectedUpPrefabName() + "'" +
            ", resultQuestionPrefabName='" + getResultQuestionPrefabName() + "'" +
            ", soundSwitchEventName='" + getSoundSwitchEventName() + "'" +
            "}";
    }
}
