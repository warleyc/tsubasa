package io.shm.tsubasa.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MChallengeQuestStage.
 */
@Entity
@Table(name = "m_challenge_quest_stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MChallengeQuestStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "world_id", nullable = false)
    private Integer worldId;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    
    @Lob
    @Column(name = "character_thumbnail_path", nullable = false)
    private String characterThumbnailPath;

    @NotNull
    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    @NotNull
    @Column(name = "stage_unlock_pattern", nullable = false)
    private Integer stageUnlockPattern;

    @NotNull
    @Column(name = "story_only", nullable = false)
    private Integer storyOnly;

    @NotNull
    @Column(name = "first_half_npc_deck_id", nullable = false)
    private Integer firstHalfNpcDeckId;

    @NotNull
    @Column(name = "first_half_environment_id", nullable = false)
    private Integer firstHalfEnvironmentId;

    @NotNull
    @Column(name = "second_half_npc_deck_id", nullable = false)
    private Integer secondHalfNpcDeckId;

    @NotNull
    @Column(name = "second_half_environment_id", nullable = false)
    private Integer secondHalfEnvironmentId;

    @NotNull
    @Column(name = "extra_first_half_npc_deck_id", nullable = false)
    private Integer extraFirstHalfNpcDeckId;

    @NotNull
    @Column(name = "consume_ap", nullable = false)
    private Integer consumeAp;

    @NotNull
    @Column(name = "kick_off_type", nullable = false)
    private Integer kickOffType;

    @NotNull
    @Column(name = "match_minute", nullable = false)
    private Integer matchMinute;

    @NotNull
    @Column(name = "enable_extra", nullable = false)
    private Integer enableExtra;

    @NotNull
    @Column(name = "enable_pk", nullable = false)
    private Integer enablePk;

    @NotNull
    @Column(name = "ai_level", nullable = false)
    private Integer aiLevel;

    @NotNull
    @Column(name = "start_at_second_half", nullable = false)
    private Integer startAtSecondHalf;

    @NotNull
    @Column(name = "start_score", nullable = false)
    private Integer startScore;

    @NotNull
    @Column(name = "start_score_opponent", nullable = false)
    private Integer startScoreOpponent;

    @Column(name = "condition_id")
    private Integer conditionId;

    @Column(name = "option_id")
    private Integer optionId;

    @Column(name = "deck_condition_id")
    private Integer deckConditionId;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @NotNull
    @Column(name = "skip_check_point", nullable = false)
    private Integer skipCheckPoint;

    @NotNull
    @Column(name = "road_number", nullable = false)
    private Integer roadNumber;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mChallengeQuestStages")
    private MChallengeQuestWorld id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorldId() {
        return worldId;
    }

    public MChallengeQuestStage worldId(Integer worldId) {
        this.worldId = worldId;
        return this;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getNumber() {
        return number;
    }

    public MChallengeQuestStage number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public MChallengeQuestStage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MChallengeQuestStage imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCharacterThumbnailPath() {
        return characterThumbnailPath;
    }

    public MChallengeQuestStage characterThumbnailPath(String characterThumbnailPath) {
        this.characterThumbnailPath = characterThumbnailPath;
        return this;
    }

    public void setCharacterThumbnailPath(String characterThumbnailPath) {
        this.characterThumbnailPath = characterThumbnailPath;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public MChallengeQuestStage difficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public MChallengeQuestStage stageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
        return this;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public Integer getStoryOnly() {
        return storyOnly;
    }

    public MChallengeQuestStage storyOnly(Integer storyOnly) {
        this.storyOnly = storyOnly;
        return this;
    }

    public void setStoryOnly(Integer storyOnly) {
        this.storyOnly = storyOnly;
    }

    public Integer getFirstHalfNpcDeckId() {
        return firstHalfNpcDeckId;
    }

    public MChallengeQuestStage firstHalfNpcDeckId(Integer firstHalfNpcDeckId) {
        this.firstHalfNpcDeckId = firstHalfNpcDeckId;
        return this;
    }

    public void setFirstHalfNpcDeckId(Integer firstHalfNpcDeckId) {
        this.firstHalfNpcDeckId = firstHalfNpcDeckId;
    }

    public Integer getFirstHalfEnvironmentId() {
        return firstHalfEnvironmentId;
    }

    public MChallengeQuestStage firstHalfEnvironmentId(Integer firstHalfEnvironmentId) {
        this.firstHalfEnvironmentId = firstHalfEnvironmentId;
        return this;
    }

    public void setFirstHalfEnvironmentId(Integer firstHalfEnvironmentId) {
        this.firstHalfEnvironmentId = firstHalfEnvironmentId;
    }

    public Integer getSecondHalfNpcDeckId() {
        return secondHalfNpcDeckId;
    }

    public MChallengeQuestStage secondHalfNpcDeckId(Integer secondHalfNpcDeckId) {
        this.secondHalfNpcDeckId = secondHalfNpcDeckId;
        return this;
    }

    public void setSecondHalfNpcDeckId(Integer secondHalfNpcDeckId) {
        this.secondHalfNpcDeckId = secondHalfNpcDeckId;
    }

    public Integer getSecondHalfEnvironmentId() {
        return secondHalfEnvironmentId;
    }

    public MChallengeQuestStage secondHalfEnvironmentId(Integer secondHalfEnvironmentId) {
        this.secondHalfEnvironmentId = secondHalfEnvironmentId;
        return this;
    }

    public void setSecondHalfEnvironmentId(Integer secondHalfEnvironmentId) {
        this.secondHalfEnvironmentId = secondHalfEnvironmentId;
    }

    public Integer getExtraFirstHalfNpcDeckId() {
        return extraFirstHalfNpcDeckId;
    }

    public MChallengeQuestStage extraFirstHalfNpcDeckId(Integer extraFirstHalfNpcDeckId) {
        this.extraFirstHalfNpcDeckId = extraFirstHalfNpcDeckId;
        return this;
    }

    public void setExtraFirstHalfNpcDeckId(Integer extraFirstHalfNpcDeckId) {
        this.extraFirstHalfNpcDeckId = extraFirstHalfNpcDeckId;
    }

    public Integer getConsumeAp() {
        return consumeAp;
    }

    public MChallengeQuestStage consumeAp(Integer consumeAp) {
        this.consumeAp = consumeAp;
        return this;
    }

    public void setConsumeAp(Integer consumeAp) {
        this.consumeAp = consumeAp;
    }

    public Integer getKickOffType() {
        return kickOffType;
    }

    public MChallengeQuestStage kickOffType(Integer kickOffType) {
        this.kickOffType = kickOffType;
        return this;
    }

    public void setKickOffType(Integer kickOffType) {
        this.kickOffType = kickOffType;
    }

    public Integer getMatchMinute() {
        return matchMinute;
    }

    public MChallengeQuestStage matchMinute(Integer matchMinute) {
        this.matchMinute = matchMinute;
        return this;
    }

    public void setMatchMinute(Integer matchMinute) {
        this.matchMinute = matchMinute;
    }

    public Integer getEnableExtra() {
        return enableExtra;
    }

    public MChallengeQuestStage enableExtra(Integer enableExtra) {
        this.enableExtra = enableExtra;
        return this;
    }

    public void setEnableExtra(Integer enableExtra) {
        this.enableExtra = enableExtra;
    }

    public Integer getEnablePk() {
        return enablePk;
    }

    public MChallengeQuestStage enablePk(Integer enablePk) {
        this.enablePk = enablePk;
        return this;
    }

    public void setEnablePk(Integer enablePk) {
        this.enablePk = enablePk;
    }

    public Integer getAiLevel() {
        return aiLevel;
    }

    public MChallengeQuestStage aiLevel(Integer aiLevel) {
        this.aiLevel = aiLevel;
        return this;
    }

    public void setAiLevel(Integer aiLevel) {
        this.aiLevel = aiLevel;
    }

    public Integer getStartAtSecondHalf() {
        return startAtSecondHalf;
    }

    public MChallengeQuestStage startAtSecondHalf(Integer startAtSecondHalf) {
        this.startAtSecondHalf = startAtSecondHalf;
        return this;
    }

    public void setStartAtSecondHalf(Integer startAtSecondHalf) {
        this.startAtSecondHalf = startAtSecondHalf;
    }

    public Integer getStartScore() {
        return startScore;
    }

    public MChallengeQuestStage startScore(Integer startScore) {
        this.startScore = startScore;
        return this;
    }

    public void setStartScore(Integer startScore) {
        this.startScore = startScore;
    }

    public Integer getStartScoreOpponent() {
        return startScoreOpponent;
    }

    public MChallengeQuestStage startScoreOpponent(Integer startScoreOpponent) {
        this.startScoreOpponent = startScoreOpponent;
        return this;
    }

    public void setStartScoreOpponent(Integer startScoreOpponent) {
        this.startScoreOpponent = startScoreOpponent;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public MChallengeQuestStage conditionId(Integer conditionId) {
        this.conditionId = conditionId;
        return this;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public MChallengeQuestStage optionId(Integer optionId) {
        this.optionId = optionId;
        return this;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getDeckConditionId() {
        return deckConditionId;
    }

    public MChallengeQuestStage deckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
        return this;
    }

    public void setDeckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public String getShortName() {
        return shortName;
    }

    public MChallengeQuestStage shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getSkipCheckPoint() {
        return skipCheckPoint;
    }

    public MChallengeQuestStage skipCheckPoint(Integer skipCheckPoint) {
        this.skipCheckPoint = skipCheckPoint;
        return this;
    }

    public void setSkipCheckPoint(Integer skipCheckPoint) {
        this.skipCheckPoint = skipCheckPoint;
    }

    public Integer getRoadNumber() {
        return roadNumber;
    }

    public MChallengeQuestStage roadNumber(Integer roadNumber) {
        this.roadNumber = roadNumber;
        return this;
    }

    public void setRoadNumber(Integer roadNumber) {
        this.roadNumber = roadNumber;
    }

    public MChallengeQuestWorld getId() {
        return id;
    }

    public MChallengeQuestStage id(MChallengeQuestWorld mChallengeQuestWorld) {
        this.id = mChallengeQuestWorld;
        return this;
    }

    public void setId(MChallengeQuestWorld mChallengeQuestWorld) {
        this.id = mChallengeQuestWorld;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MChallengeQuestStage)) {
            return false;
        }
        return id != null && id.equals(((MChallengeQuestStage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MChallengeQuestStage{" +
            "id=" + getId() +
            ", worldId=" + getWorldId() +
            ", number=" + getNumber() +
            ", name='" + getName() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", characterThumbnailPath='" + getCharacterThumbnailPath() + "'" +
            ", difficulty=" + getDifficulty() +
            ", stageUnlockPattern=" + getStageUnlockPattern() +
            ", storyOnly=" + getStoryOnly() +
            ", firstHalfNpcDeckId=" + getFirstHalfNpcDeckId() +
            ", firstHalfEnvironmentId=" + getFirstHalfEnvironmentId() +
            ", secondHalfNpcDeckId=" + getSecondHalfNpcDeckId() +
            ", secondHalfEnvironmentId=" + getSecondHalfEnvironmentId() +
            ", extraFirstHalfNpcDeckId=" + getExtraFirstHalfNpcDeckId() +
            ", consumeAp=" + getConsumeAp() +
            ", kickOffType=" + getKickOffType() +
            ", matchMinute=" + getMatchMinute() +
            ", enableExtra=" + getEnableExtra() +
            ", enablePk=" + getEnablePk() +
            ", aiLevel=" + getAiLevel() +
            ", startAtSecondHalf=" + getStartAtSecondHalf() +
            ", startScore=" + getStartScore() +
            ", startScoreOpponent=" + getStartScoreOpponent() +
            ", conditionId=" + getConditionId() +
            ", optionId=" + getOptionId() +
            ", deckConditionId=" + getDeckConditionId() +
            ", shortName='" + getShortName() + "'" +
            ", skipCheckPoint=" + getSkipCheckPoint() +
            ", roadNumber=" + getRoadNumber() +
            "}";
    }
}
