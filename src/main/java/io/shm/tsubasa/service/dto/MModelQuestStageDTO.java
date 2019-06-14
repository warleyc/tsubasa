package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MModelQuestStage} entity.
 */
public class MModelQuestStageDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer stageId;

    
    @Lob
    private String image;

    
    @Lob
    private String modelName;

    
    @Lob
    private String bgmOffencing;

    
    @Lob
    private String bgmDefencing;

    
    @Lob
    private String bgmHurrying;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBgmOffencing() {
        return bgmOffencing;
    }

    public void setBgmOffencing(String bgmOffencing) {
        this.bgmOffencing = bgmOffencing;
    }

    public String getBgmDefencing() {
        return bgmDefencing;
    }

    public void setBgmDefencing(String bgmDefencing) {
        this.bgmDefencing = bgmDefencing;
    }

    public String getBgmHurrying() {
        return bgmHurrying;
    }

    public void setBgmHurrying(String bgmHurrying) {
        this.bgmHurrying = bgmHurrying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MModelQuestStageDTO mModelQuestStageDTO = (MModelQuestStageDTO) o;
        if (mModelQuestStageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mModelQuestStageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MModelQuestStageDTO{" +
            "id=" + getId() +
            ", stageId=" + getStageId() +
            ", image='" + getImage() + "'" +
            ", modelName='" + getModelName() + "'" +
            ", bgmOffencing='" + getBgmOffencing() + "'" +
            ", bgmDefencing='" + getBgmDefencing() + "'" +
            ", bgmHurrying='" + getBgmHurrying() + "'" +
            "}";
    }
}
