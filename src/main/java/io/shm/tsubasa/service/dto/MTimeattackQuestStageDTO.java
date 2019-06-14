package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTimeattackQuestStage} entity.
 */
public class MTimeattackQuestStageDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer worldId;

    @NotNull
    private Integer number;

    @NotNull
    private Integer startAt;

    
    @Lob
    private String name;

    
    @Lob
    private String kickoffDescription;

    
    @Lob
    private String imagePath;

    
    @Lob
    private String characterThumbnailPath;

    @NotNull
    private Integer difficulty;

    @NotNull
    private Integer stageUnlockPattern;

    @NotNull
    private Integer storyOnly;

    @NotNull
    private Integer firstHalfNpcDeckId;

    @NotNull
    private Integer firstHalfEnvironmentId;

    @NotNull
    private Integer secondHalfNpcDeckId;

    @NotNull
    private Integer secondHalfEnvironmentId;

    @NotNull
    private Integer extraFirstHalfNpcDeckId;

    @NotNull
    private Integer consumeAp;

    private Integer ticketId;

    @NotNull
    private Integer ticketAmount;

    @NotNull
    private Integer kickOffType;

    @NotNull
    private Integer matchMinute;

    @NotNull
    private Integer enableExtra;

    @NotNull
    private Integer enablePk;

    @NotNull
    private Integer aiLevel;

    @NotNull
    private Integer startAtSecondHalf;

    @NotNull
    private Integer startScore;

    @NotNull
    private Integer startScoreOpponent;

    private Integer conditionId;

    private Integer optionId;

    private Integer deckConditionId;

    
    @Lob
    private String shortName;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorldId() {
        return worldId;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKickoffDescription() {
        return kickoffDescription;
    }

    public void setKickoffDescription(String kickoffDescription) {
        this.kickoffDescription = kickoffDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCharacterThumbnailPath() {
        return characterThumbnailPath;
    }

    public void setCharacterThumbnailPath(String characterThumbnailPath) {
        this.characterThumbnailPath = characterThumbnailPath;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public void setStageUnlockPattern(Integer stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public Integer getStoryOnly() {
        return storyOnly;
    }

    public void setStoryOnly(Integer storyOnly) {
        this.storyOnly = storyOnly;
    }

    public Integer getFirstHalfNpcDeckId() {
        return firstHalfNpcDeckId;
    }

    public void setFirstHalfNpcDeckId(Integer firstHalfNpcDeckId) {
        this.firstHalfNpcDeckId = firstHalfNpcDeckId;
    }

    public Integer getFirstHalfEnvironmentId() {
        return firstHalfEnvironmentId;
    }

    public void setFirstHalfEnvironmentId(Integer firstHalfEnvironmentId) {
        this.firstHalfEnvironmentId = firstHalfEnvironmentId;
    }

    public Integer getSecondHalfNpcDeckId() {
        return secondHalfNpcDeckId;
    }

    public void setSecondHalfNpcDeckId(Integer secondHalfNpcDeckId) {
        this.secondHalfNpcDeckId = secondHalfNpcDeckId;
    }

    public Integer getSecondHalfEnvironmentId() {
        return secondHalfEnvironmentId;
    }

    public void setSecondHalfEnvironmentId(Integer secondHalfEnvironmentId) {
        this.secondHalfEnvironmentId = secondHalfEnvironmentId;
    }

    public Integer getExtraFirstHalfNpcDeckId() {
        return extraFirstHalfNpcDeckId;
    }

    public void setExtraFirstHalfNpcDeckId(Integer extraFirstHalfNpcDeckId) {
        this.extraFirstHalfNpcDeckId = extraFirstHalfNpcDeckId;
    }

    public Integer getConsumeAp() {
        return consumeAp;
    }

    public void setConsumeAp(Integer consumeAp) {
        this.consumeAp = consumeAp;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public Integer getKickOffType() {
        return kickOffType;
    }

    public void setKickOffType(Integer kickOffType) {
        this.kickOffType = kickOffType;
    }

    public Integer getMatchMinute() {
        return matchMinute;
    }

    public void setMatchMinute(Integer matchMinute) {
        this.matchMinute = matchMinute;
    }

    public Integer getEnableExtra() {
        return enableExtra;
    }

    public void setEnableExtra(Integer enableExtra) {
        this.enableExtra = enableExtra;
    }

    public Integer getEnablePk() {
        return enablePk;
    }

    public void setEnablePk(Integer enablePk) {
        this.enablePk = enablePk;
    }

    public Integer getAiLevel() {
        return aiLevel;
    }

    public void setAiLevel(Integer aiLevel) {
        this.aiLevel = aiLevel;
    }

    public Integer getStartAtSecondHalf() {
        return startAtSecondHalf;
    }

    public void setStartAtSecondHalf(Integer startAtSecondHalf) {
        this.startAtSecondHalf = startAtSecondHalf;
    }

    public Integer getStartScore() {
        return startScore;
    }

    public void setStartScore(Integer startScore) {
        this.startScore = startScore;
    }

    public Integer getStartScoreOpponent() {
        return startScoreOpponent;
    }

    public void setStartScoreOpponent(Integer startScoreOpponent) {
        this.startScoreOpponent = startScoreOpponent;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getDeckConditionId() {
        return deckConditionId;
    }

    public void setDeckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mTimeattackQuestWorldId) {
        this.idId = mTimeattackQuestWorldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTimeattackQuestStageDTO mTimeattackQuestStageDTO = (MTimeattackQuestStageDTO) o;
        if (mTimeattackQuestStageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTimeattackQuestStageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTimeattackQuestStageDTO{" +
            "id=" + getId() +
            ", worldId=" + getWorldId() +
            ", number=" + getNumber() +
            ", startAt=" + getStartAt() +
            ", name='" + getName() + "'" +
            ", kickoffDescription='" + getKickoffDescription() + "'" +
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
            ", ticketId=" + getTicketId() +
            ", ticketAmount=" + getTicketAmount() +
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
            ", id=" + getIdId() +
            "}";
    }
}
