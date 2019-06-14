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
 * A MLuckWeeklyQuestWorld.
 */
@Entity
@Table(name = "m_luck_weekly_quest_world")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLuckWeeklyQuestWorld implements Serializable {

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

    @NotNull
    @Column(name = "clear_limit", nullable = false)
    private Integer clearLimit;

    @OneToMany(mappedBy = "mluckweeklyquestworld")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MLuckWeeklyQuestStage> mLuckWeeklyQuestStages = new HashSet<>();

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

    public MLuckWeeklyQuestWorld setId(Integer setId) {
        this.setId = setId;
        return this;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public Integer getNumber() {
        return number;
    }

    public MLuckWeeklyQuestWorld number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public MLuckWeeklyQuestWorld name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MLuckWeeklyQuestWorld imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public MLuckWeeklyQuestWorld description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public MLuckWeeklyQuestWorld stageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
        return this;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public String getArousalBanner() {
        return arousalBanner;
    }

    public MLuckWeeklyQuestWorld arousalBanner(String arousalBanner) {
        this.arousalBanner = arousalBanner;
        return this;
    }

    public void setArousalBanner(String arousalBanner) {
        this.arousalBanner = arousalBanner;
    }

    public Integer getSpecialRewardContentType() {
        return specialRewardContentType;
    }

    public MLuckWeeklyQuestWorld specialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
        return this;
    }

    public void setSpecialRewardContentType(Integer specialRewardContentType) {
        this.specialRewardContentType = specialRewardContentType;
    }

    public Integer getSpecialRewardContentId() {
        return specialRewardContentId;
    }

    public MLuckWeeklyQuestWorld specialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
        return this;
    }

    public void setSpecialRewardContentId(Integer specialRewardContentId) {
        this.specialRewardContentId = specialRewardContentId;
    }

    public Integer getIsEnableCoop() {
        return isEnableCoop;
    }

    public MLuckWeeklyQuestWorld isEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
        return this;
    }

    public void setIsEnableCoop(Integer isEnableCoop) {
        this.isEnableCoop = isEnableCoop;
    }

    public Integer getClearLimit() {
        return clearLimit;
    }

    public MLuckWeeklyQuestWorld clearLimit(Integer clearLimit) {
        this.clearLimit = clearLimit;
        return this;
    }

    public void setClearLimit(Integer clearLimit) {
        this.clearLimit = clearLimit;
    }

    public Set<MLuckWeeklyQuestStage> getMLuckWeeklyQuestStages() {
        return mLuckWeeklyQuestStages;
    }

    public MLuckWeeklyQuestWorld mLuckWeeklyQuestStages(Set<MLuckWeeklyQuestStage> mLuckWeeklyQuestStages) {
        this.mLuckWeeklyQuestStages = mLuckWeeklyQuestStages;
        return this;
    }

    public MLuckWeeklyQuestWorld addMLuckWeeklyQuestStage(MLuckWeeklyQuestStage mLuckWeeklyQuestStage) {
        this.mLuckWeeklyQuestStages.add(mLuckWeeklyQuestStage);
        mLuckWeeklyQuestStage.setMluckweeklyquestworld(this);
        return this;
    }

    public MLuckWeeklyQuestWorld removeMLuckWeeklyQuestStage(MLuckWeeklyQuestStage mLuckWeeklyQuestStage) {
        this.mLuckWeeklyQuestStages.remove(mLuckWeeklyQuestStage);
        mLuckWeeklyQuestStage.setMluckweeklyquestworld(null);
        return this;
    }

    public void setMLuckWeeklyQuestStages(Set<MLuckWeeklyQuestStage> mLuckWeeklyQuestStages) {
        this.mLuckWeeklyQuestStages = mLuckWeeklyQuestStages;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLuckWeeklyQuestWorld)) {
            return false;
        }
        return id != null && id.equals(((MLuckWeeklyQuestWorld) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLuckWeeklyQuestWorld{" +
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
            ", clearLimit=" + getClearLimit() +
            "}";
    }
}
