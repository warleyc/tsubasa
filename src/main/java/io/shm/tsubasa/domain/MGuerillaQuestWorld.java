package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MGuerillaQuestWorld.
 */
@Entity
@Table(name = "m_guerilla_quest_world")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuerillaQuestWorld implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "set_id", nullable = false)
    private Integer setId;

    @NotNull
    @Column(name = "jhi_number", nullable = false)
    private Integer number;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "stage_unlock_pattern", nullable = false)
    private Integer stageUnlockPattern;

    @Lob
    @Column(name = "arousal_banner")
    private String arousalBanner;

    @Column(name = "special_reward_content_type")
    private Integer specialRewardContentType;

    @Column(name = "special_reward_content_id")
    private Integer specialRewardContentId;

    @NotNull
    @Column(name = "is_enable_coop", nullable = false)
    private Integer isEnableCoop;

    @OneToMany(mappedBy = "mguerillaquestworld")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MGuerillaQuestStage> mGuerillaQuestStages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSetId() {
        return setId;
    }

    public MGuerillaQuestWorld setId(Integer setId) {
        this.setId = setId;
        return this;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getNumber() {
        return number;
    }

    public MGuerillaQuestWorld number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public MGuerillaQuestWorld name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MGuerillaQuestWorld imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public MGuerillaQuestWorld description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public MGuerillaQuestWorld stageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
        return this;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public String getArousalBanner() {
        return arousalBanner;
    }

    public MGuerillaQuestWorld arousalBanner(String arousalBanner) {
        this.arousalBanner = arousalBanner;
        return this;
    }

    public void setArousalBanner(String arousalBanner) {
        this.arousalBanner = arousalBanner;
    }

    public Integer getSpecialRewardContentType() {
        return specialRewardContentType;
    }

    public MGuerillaQuestWorld specialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
        return this;
    }

    public void setSpecialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
    }

    public Integer getSpecialRewardContentId() {
        return specialRewardContentId;
    }

    public MGuerillaQuestWorld specialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
        return this;
    }

    public void setSpecialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
    }

    public Integer getIsEnableCoop() {
        return isEnableCoop;
    }

    public MGuerillaQuestWorld isEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
        return this;
    }

    public void setIsEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
    }

    public Set<MGuerillaQuestStage> getMGuerillaQuestStages() {
        return mGuerillaQuestStages;
    }

    public MGuerillaQuestWorld mGuerillaQuestStages(Set<MGuerillaQuestStage> mGuerillaQuestStages) {
        this.mGuerillaQuestStages = mGuerillaQuestStages;
        return this;
    }

    public MGuerillaQuestWorld addMGuerillaQuestStage(MGuerillaQuestStage mGuerillaQuestStage) {
        this.mGuerillaQuestStages.add(mGuerillaQuestStage);
        mGuerillaQuestStage.setMguerillaquestworld(this);
        return this;
    }

    public MGuerillaQuestWorld removeMGuerillaQuestStage(MGuerillaQuestStage mGuerillaQuestStage) {
        this.mGuerillaQuestStages.remove(mGuerillaQuestStage);
        mGuerillaQuestStage.setMguerillaquestworld(null);
        return this;
    }

    public void setMGuerillaQuestStages(Set<MGuerillaQuestStage> mGuerillaQuestStages) {
        this.mGuerillaQuestStages = mGuerillaQuestStages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuerillaQuestWorld)) {
            return false;
        }
        return id != null && id.equals(((MGuerillaQuestWorld) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuerillaQuestWorld{" +
            "id=" + getId() +
            ", setId=" + getSetId() +
            ", number=" + getNumber() +
            ", name='" + getName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", description='" + getDescription() + "'" +
            ", stageUnlockPattern=" + getStageUnlockPattern() +
            ", arousalBanner='" + getArousalBanner() + "'" +
            ", specialRewardContentType=" + getSpecialRewardContentType() +
            ", specialRewardContentId=" + getSpecialRewardContentId() +
            ", isEnableCoop=" + getIsEnableCoop() +
            "}";
    }
}
