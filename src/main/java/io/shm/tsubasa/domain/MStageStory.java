package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MStageStory.
 */
@Entity
@Table(name = "m_stage_story")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MStageStory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    @NotNull
    @Column(name = "event_type", nullable = false)
    private Integer eventType;

    @Lob
    @Column(name = "main_story_asset")
    private String mainStoryAsset;

    @Lob
    @Column(name = "kickoff_story_asset")
    private String kickoffStoryAsset;

    @Lob
    @Column(name = "halftime_story_asset")
    private String halftimeStoryAsset;

    @Lob
    @Column(name = "result_story_asset")
    private String resultStoryAsset;

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

    public MStageStory stageId(Integer stageId) {
        this.stageId = stageId;
        return this;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getEventType() {
        return eventType;
    }

    public MStageStory eventType(Integer eventType) {
        this.eventType = eventType;
        return this;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getMainStoryAsset() {
        return mainStoryAsset;
    }

    public MStageStory mainStoryAsset(String mainStoryAsset) {
        this.mainStoryAsset = mainStoryAsset;
        return this;
    }

    public void setMainStoryAsset(String mainStoryAsset) {
        this.mainStoryAsset = mainStoryAsset;
    }

    public String getKickoffStoryAsset() {
        return kickoffStoryAsset;
    }

    public MStageStory kickoffStoryAsset(String kickoffStoryAsset) {
        this.kickoffStoryAsset = kickoffStoryAsset;
        return this;
    }

    public void setKickoffStoryAsset(String kickoffStoryAsset) {
        this.kickoffStoryAsset = kickoffStoryAsset;
    }

    public String getHalftimeStoryAsset() {
        return halftimeStoryAsset;
    }

    public MStageStory halftimeStoryAsset(String halftimeStoryAsset) {
        this.halftimeStoryAsset = halftimeStoryAsset;
        return this;
    }

    public void setHalftimeStoryAsset(String halftimeStoryAsset) {
        this.halftimeStoryAsset = halftimeStoryAsset;
    }

    public String getResultStoryAsset() {
        return resultStoryAsset;
    }

    public MStageStory resultStoryAsset(String resultStoryAsset) {
        this.resultStoryAsset = resultStoryAsset;
        return this;
    }

    public void setResultStoryAsset(String resultStoryAsset) {
        this.resultStoryAsset = resultStoryAsset;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MStageStory)) {
            return false;
        }
        return id != null && id.equals(((MStageStory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MStageStory{" +
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
