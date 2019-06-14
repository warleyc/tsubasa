package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MModelQuestStage.
 */
@Entity
@Table(name = "m_model_quest_stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MModelQuestStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    
    @Lob
    @Column(name = "image", nullable = false)
    private String image;

    
    @Lob
    @Column(name = "model_name", nullable = false)
    private String modelName;

    
    @Lob
    @Column(name = "bgm_offencing", nullable = false)
    private String bgmOffencing;

    
    @Lob
    @Column(name = "bgm_defencing", nullable = false)
    private String bgmDefencing;

    
    @Lob
    @Column(name = "bgm_hurrying", nullable = false)
    private String bgmHurrying;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStageId() {
        return stageId;
    }

    public MModelQuestStage stageId(Integer stageId) {
        this.stageId = stageId;
        return this;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getImage() {
        return image;
    }

    public MModelQuestStage image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModelName() {
        return modelName;
    }

    public MModelQuestStage modelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBgmOffencing() {
        return bgmOffencing;
    }

    public MModelQuestStage bgmOffencing(String bgmOffencing) {
        this.bgmOffencing = bgmOffencing;
        return this;
    }

    public void setBgmOffencing(String bgmOffencing) {
        this.bgmOffencing = bgmOffencing;
    }

    public String getBgmDefencing() {
        return bgmDefencing;
    }

    public MModelQuestStage bgmDefencing(String bgmDefencing) {
        this.bgmDefencing = bgmDefencing;
        return this;
    }

    public void setBgmDefencing(String bgmDefencing) {
        this.bgmDefencing = bgmDefencing;
    }

    public String getBgmHurrying() {
        return bgmHurrying;
    }

    public MModelQuestStage bgmHurrying(String bgmHurrying) {
        this.bgmHurrying = bgmHurrying;
        return this;
    }

    public void setBgmHurrying(String bgmHurrying) {
        this.bgmHurrying = bgmHurrying;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MModelQuestStage)) {
            return false;
        }
        return id != null && id.equals(((MModelQuestStage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MModelQuestStage{" +
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
