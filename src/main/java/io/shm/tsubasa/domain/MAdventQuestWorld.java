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
 * A MAdventQuestWorld.
 */
@Entity
@Table(name = "m_advent_quest_world")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAdventQuestWorld implements Serializable {

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

    @NotNull
    @Column(name = "symbol_type", nullable = false)
    private Integer symbolType;

    
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

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MAdventQuestStage> mAdventQuestStages = new HashSet<>();

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

    public MAdventQuestWorld setId(Integer setId) {
        this.setId = setId;
        return this;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getNumber() {
        return number;
    }

    public MAdventQuestWorld number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public MAdventQuestWorld name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSymbolType() {
        return symbolType;
    }

    public MAdventQuestWorld symbolType(Integer symbolType) {
        this.symbolType = symbolType;
        return this;
    }

    public void setSymbolType(Integer symbolType) {
        this.symbolType = symbolType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MAdventQuestWorld imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public MAdventQuestWorld description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public MAdventQuestWorld stageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
        return this;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public String getArousalBanner() {
        return arousalBanner;
    }

    public MAdventQuestWorld arousalBanner(String arousalBanner) {
        this.arousalBanner = arousalBanner;
        return this;
    }

    public void setArousalBanner(String arousalBanner) {
        this.arousalBanner = arousalBanner;
    }

    public Integer getSpecialRewardContentType() {
        return specialRewardContentType;
    }

    public MAdventQuestWorld specialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
        return this;
    }

    public void setSpecialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
    }

    public Integer getSpecialRewardContentId() {
        return specialRewardContentId;
    }

    public MAdventQuestWorld specialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
        return this;
    }

    public void setSpecialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
    }

    public Integer getIsEnableCoop() {
        return isEnableCoop;
    }

    public MAdventQuestWorld isEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
        return this;
    }

    public void setIsEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
    }

    public Set<MAdventQuestStage> getMAdventQuestStages() {
        return mAdventQuestStages;
    }

    public MAdventQuestWorld mAdventQuestStages(Set<MAdventQuestStage> mAdventQuestStages) {
        this.mAdventQuestStages = mAdventQuestStages;
        return this;
    }

    public MAdventQuestWorld addMAdventQuestStage(MAdventQuestStage mAdventQuestStage) {
        this.mAdventQuestStages.add(mAdventQuestStage);
        mAdventQuestStage.setId(this);
        return this;
    }

    public MAdventQuestWorld removeMAdventQuestStage(MAdventQuestStage mAdventQuestStage) {
        this.mAdventQuestStages.remove(mAdventQuestStage);
        mAdventQuestStage.setId(null);
        return this;
    }

    public void setMAdventQuestStages(Set<MAdventQuestStage> mAdventQuestStages) {
        this.mAdventQuestStages = mAdventQuestStages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAdventQuestWorld)) {
            return false;
        }
        return id != null && id.equals(((MAdventQuestWorld) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAdventQuestWorld{" +
            "id=" + getId() +
            ", setId=" + getSetId() +
            ", number=" + getNumber() +
            ", name='" + getName() + "'" +
            ", symbolType=" + getSymbolType() +
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
