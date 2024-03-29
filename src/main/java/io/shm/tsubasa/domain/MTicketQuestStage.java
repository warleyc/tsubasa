package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTicketQuestStage.
 */
@Entity
@Table(name = "m_ticket_quest_stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTicketQuestStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "world_id", nullable = false)
    private Integer worldId;

    @NotNull
    @Column(name = "jhi_number", nullable = false)
    private Integer number;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "ticket_id", nullable = false)
    private Integer ticketId;

    @NotNull
    @Column(name = "ticket_amount", nullable = false)
    private Integer ticketAmount;

    
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
    @Column(name = "extra_second_half_npc_deck_id", nullable = false)
    private Integer extraSecondHalfNpcDeckId;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTicketQuestStages")
    private MTicketQuestWorld mticketquestworld;

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

    public MTicketQuestStage worldId(Integer worldId) {
        this.worldId = worldId;
        return this;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getNumber() {
        return number;
    }

    public MTicketQuestStage number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public MTicketQuestStage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public MTicketQuestStage ticketId(Integer ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public MTicketQuestStage ticketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
        return this;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public MTicketQuestStage imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCharacterThumbnailPath() {
        return characterThumbnailPath;
    }

    public MTicketQuestStage characterThumbnailPath(String characterThumbnailPath) {
        this.characterThumbnailPath = characterThumbnailPath;
        return this;
    }

    public void setCharacterThumbnailPath(String characterThumbnailPath) {
        this.characterThumbnailPath = characterThumbnailPath;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public MTicketQuestStage difficulty(Integer difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public MTicketQuestStage stageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
        return this;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public Integer getStoryOnly() {
        return storyOnly;
    }

    public MTicketQuestStage storyOnly(Integer storyOnly) {
        this.storyOnly = storyOnly;
        return this;
    }

    public void setStoryOnly(Integer storyOnly) {
        this.storyOnly = storyOnly;
    }

    public Integer getFirstHalfNpcDeckId() {
        return firstHalfNpcDeckId;
    }

    public MTicketQuestStage firstHalfNpcDeckId(Integer firstHalfNpcDeckId) {
        this.firstHalfNpcDeckId = firstHalfNpcDeckId;
        return this;
    }

    public void setFirstHalfNpcDeckId(Integer firstHalfNpcDeckId) {
        this.firstHalfNpcDeckId = firstHalfNpcDeckId;
    }

    public Integer getFirstHalfEnvironmentId() {
        return firstHalfEnvironmentId;
    }

    public MTicketQuestStage firstHalfEnvironmentId(Integer firstHalfEnvironmentId) {
        this.firstHalfEnvironmentId = firstHalfEnvironmentId;
        return this;
    }

    public void setFirstHalfEnvironmentId(Integer firstHalfEnvironmentId) {
        this.firstHalfEnvironmentId = firstHalfEnvironmentId;
    }

    public Integer getSecondHalfNpcDeckId() {
        return secondHalfNpcDeckId;
    }

    public MTicketQuestStage secondHalfNpcDeckId(Integer secondHalfNpcDeckId) {
        this.secondHalfNpcDeckId = secondHalfNpcDeckId;
        return this;
    }

    public void setSecondHalfNpcDeckId(Integer secondHalfNpcDeckId) {
        this.secondHalfNpcDeckId = secondHalfNpcDeckId;
    }

    public Integer getSecondHalfEnvironmentId() {
        return secondHalfEnvironmentId;
    }

    public MTicketQuestStage secondHalfEnvironmentId(Integer secondHalfEnvironmentId) {
        this.secondHalfEnvironmentId = secondHalfEnvironmentId;
        return this;
    }

    public void setSecondHalfEnvironmentId(Integer secondHalfEnvironmentId) {
        this.secondHalfEnvironmentId = secondHalfEnvironmentId;
    }

    public Integer getExtraFirstHalfNpcDeckId() {
        return extraFirstHalfNpcDeckId;
    }

    public MTicketQuestStage extraFirstHalfNpcDeckId(Integer extraFirstHalfNpcDeckId) {
        this.extraFirstHalfNpcDeckId = extraFirstHalfNpcDeckId;
        return this;
    }

    public void setExtraFirstHalfNpcDeckId(Integer extraFirstHalfNpcDeckId) {
        this.extraFirstHalfNpcDeckId = extraFirstHalfNpcDeckId;
    }

    public Integer getExtraSecondHalfNpcDeckId() {
        return extraSecondHalfNpcDeckId;
    }

    public MTicketQuestStage extraSecondHalfNpcDeckId(Integer extraSecondHalfNpcDeckId) {
        this.extraSecondHalfNpcDeckId = extraSecondHalfNpcDeckId;
        return this;
    }

    public void setExtraSecondHalfNpcDeckId(Integer extraSecondHalfNpcDeckId) {
        this.extraSecondHalfNpcDeckId = extraSecondHalfNpcDeckId;
    }

    public Integer getConsumeAp() {
        return consumeAp;
    }

    public MTicketQuestStage consumeAp(Integer consumeAp) {
        this.consumeAp = consumeAp;
        return this;
    }

    public void setConsumeAp(Integer consumeAp) {
        this.consumeAp = consumeAp;
    }

    public Integer getKickOffType() {
        return kickOffType;
    }

    public MTicketQuestStage kickOffType(Integer kickOffType) {
        this.kickOffType = kickOffType;
        return this;
    }

    public void setKickOffType(Integer kickOffType) {
        this.kickOffType = kickOffType;
    }

    public Integer getMatchMinute() {
        return matchMinute;
    }

    public MTicketQuestStage matchMinute(Integer matchMinute) {
        this.matchMinute = matchMinute;
        return this;
    }

    public void setMatchMinute(Integer matchMinute) {
        this.matchMinute = matchMinute;
    }

    public Integer getEnableExtra() {
        return enableExtra;
    }

    public MTicketQuestStage enableExtra(Integer enableExtra) {
        this.enableExtra = enableExtra;
        return this;
    }

    public void setEnableExtra(Integer enableExtra) {
        this.enableExtra = enableExtra;
    }

    public Integer getEnablePk() {
        return enablePk;
    }

    public MTicketQuestStage enablePk(Integer enablePk) {
        this.enablePk = enablePk;
        return this;
    }

    public void setEnablePk(Integer enablePk) {
        this.enablePk = enablePk;
    }

    public Integer getAiLevel() {
        return aiLevel;
    }

    public MTicketQuestStage aiLevel(Integer aiLevel) {
        this.aiLevel = aiLevel;
        return this;
    }

    public void setAiLevel(Integer aiLevel) {
        this.aiLevel = aiLevel;
    }

    public Integer getStartAtSecondHalf() {
        return startAtSecondHalf;
    }

    public MTicketQuestStage startAtSecondHalf(Integer startAtSecondHalf) {
        this.startAtSecondHalf = startAtSecondHalf;
        return this;
    }

    public void setStartAtSecondHalf(Integer startAtSecondHalf) {
        this.startAtSecondHalf = startAtSecondHalf;
    }

    public Integer getStartScore() {
        return startScore;
    }

    public MTicketQuestStage startScore(Integer startScore) {
        this.startScore = startScore;
        return this;
    }

    public void setStartScore(Integer startScore) {
        this.startScore = startScore;
    }

    public Integer getStartScoreOpponent() {
        return startScoreOpponent;
    }

    public MTicketQuestStage startScoreOpponent(Integer startScoreOpponent) {
        this.startScoreOpponent = startScoreOpponent;
        return this;
    }

    public void setStartScoreOpponent(Integer startScoreOpponent) {
        this.startScoreOpponent = startScoreOpponent;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public MTicketQuestStage conditionId(Integer conditionId) {
        this.conditionId = conditionId;
        return this;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public MTicketQuestStage optionId(Integer optionId) {
        this.optionId = optionId;
        return this;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getDeckConditionId() {
        return deckConditionId;
    }

    public MTicketQuestStage deckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
        return this;
    }

    public void setDeckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public MTicketQuestWorld getMticketquestworld() {
        return mticketquestworld;
    }

    public MTicketQuestStage mticketquestworld(MTicketQuestWorld mTicketQuestWorld) {
        this.mticketquestworld = mTicketQuestWorld;
        return this;
    }

    public void setMticketquestworld(MTicketQuestWorld mTicketQuestWorld) {
        this.mticketquestworld = mTicketQuestWorld;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTicketQuestStage)) {
            return false;
        }
        return id != null && id.equals(((MTicketQuestStage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTicketQuestStage{" +
            "id=" + getId() +
            ", worldId=" + getWorldId() +
            ", number=" + getNumber() +
            ", name='" + getName() + "'" +
            ", ticketId=" + getTicketId() +
            ", ticketAmount=" + getTicketAmount() +
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
            ", extraSecondHalfNpcDeckId=" + getExtraSecondHalfNpcDeckId() +
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
            "}";
    }
}
