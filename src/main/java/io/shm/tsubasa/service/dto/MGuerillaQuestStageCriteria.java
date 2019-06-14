package io.shm.tsubasa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.shm.tsubasa.domain.MGuerillaQuestStage} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MGuerillaQuestStageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-guerilla-quest-stages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MGuerillaQuestStageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter worldId;

    private IntegerFilter number;

    private IntegerFilter difficulty;

    private IntegerFilter stageUnlockPattern;

    private IntegerFilter storyOnly;

    private IntegerFilter firstHalfNpcDeckId;

    private IntegerFilter firstHalfEnvironmentId;

    private IntegerFilter secondHalfNpcDeckId;

    private IntegerFilter secondHalfEnvironmentId;

    private IntegerFilter extraFirstHalfNpcDeckId;

    private IntegerFilter extraSecondHalfNpcDeckId;

    private IntegerFilter consumeAp;

    private IntegerFilter kickOffType;

    private IntegerFilter matchMinute;

    private IntegerFilter enableExtra;

    private IntegerFilter enablePk;

    private IntegerFilter aiLevel;

    private IntegerFilter startAtSecondHalf;

    private IntegerFilter startScore;

    private IntegerFilter startScoreOpponent;

    private IntegerFilter conditionId;

    private IntegerFilter optionId;

    private IntegerFilter deckConditionId;

    private LongFilter idId;

    public MGuerillaQuestStageCriteria(){
    }

    public MGuerillaQuestStageCriteria(MGuerillaQuestStageCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.worldId = other.worldId == null ? null : other.worldId.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.difficulty = other.difficulty == null ? null : other.difficulty.copy();
        this.stageUnlockPattern = other.stageUnlockPattern == null ? null : other.stageUnlockPattern.copy();
        this.storyOnly = other.storyOnly == null ? null : other.storyOnly.copy();
        this.firstHalfNpcDeckId = other.firstHalfNpcDeckId == null ? null : other.firstHalfNpcDeckId.copy();
        this.firstHalfEnvironmentId = other.firstHalfEnvironmentId == null ? null : other.firstHalfEnvironmentId.copy();
        this.secondHalfNpcDeckId = other.secondHalfNpcDeckId == null ? null : other.secondHalfNpcDeckId.copy();
        this.secondHalfEnvironmentId = other.secondHalfEnvironmentId == null ? null : other.secondHalfEnvironmentId.copy();
        this.extraFirstHalfNpcDeckId = other.extraFirstHalfNpcDeckId == null ? null : other.extraFirstHalfNpcDeckId.copy();
        this.extraSecondHalfNpcDeckId = other.extraSecondHalfNpcDeckId == null ? null : other.extraSecondHalfNpcDeckId.copy();
        this.consumeAp = other.consumeAp == null ? null : other.consumeAp.copy();
        this.kickOffType = other.kickOffType == null ? null : other.kickOffType.copy();
        this.matchMinute = other.matchMinute == null ? null : other.matchMinute.copy();
        this.enableExtra = other.enableExtra == null ? null : other.enableExtra.copy();
        this.enablePk = other.enablePk == null ? null : other.enablePk.copy();
        this.aiLevel = other.aiLevel == null ? null : other.aiLevel.copy();
        this.startAtSecondHalf = other.startAtSecondHalf == null ? null : other.startAtSecondHalf.copy();
        this.startScore = other.startScore == null ? null : other.startScore.copy();
        this.startScoreOpponent = other.startScoreOpponent == null ? null : other.startScoreOpponent.copy();
        this.conditionId = other.conditionId == null ? null : other.conditionId.copy();
        this.optionId = other.optionId == null ? null : other.optionId.copy();
        this.deckConditionId = other.deckConditionId == null ? null : other.deckConditionId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MGuerillaQuestStageCriteria copy() {
        return new MGuerillaQuestStageCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getWorldId() {
        return worldId;
    }

    public void setWorldId(IntegerFilter worldId) {
        this.worldId = worldId;
    }

    public IntegerFilter getNumber() {
        return number;
    }

    public void setNumber(IntegerFilter number) {
        this.number = number;
    }

    public IntegerFilter getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(IntegerFilter difficulty) {
        this.difficulty = difficulty;
    }

    public IntegerFilter getStageUnlockPattern() {
        return stageUnlockPattern;
    }

    public void setStageUnlockPattern(IntegerFilter stageUnlockPattern) {
        this.stageUnlockPattern = stageUnlockPattern;
    }

    public IntegerFilter getStoryOnly() {
        return storyOnly;
    }

    public void setStoryOnly(IntegerFilter storyOnly) {
        this.storyOnly = storyOnly;
    }

    public IntegerFilter getFirstHalfNpcDeckId() {
        return firstHalfNpcDeckId;
    }

    public void setFirstHalfNpcDeckId(IntegerFilter firstHalfNpcDeckId) {
        this.firstHalfNpcDeckId = firstHalfNpcDeckId;
    }

    public IntegerFilter getFirstHalfEnvironmentId() {
        return firstHalfEnvironmentId;
    }

    public void setFirstHalfEnvironmentId(IntegerFilter firstHalfEnvironmentId) {
        this.firstHalfEnvironmentId = firstHalfEnvironmentId;
    }

    public IntegerFilter getSecondHalfNpcDeckId() {
        return secondHalfNpcDeckId;
    }

    public void setSecondHalfNpcDeckId(IntegerFilter secondHalfNpcDeckId) {
        this.secondHalfNpcDeckId = secondHalfNpcDeckId;
    }

    public IntegerFilter getSecondHalfEnvironmentId() {
        return secondHalfEnvironmentId;
    }

    public void setSecondHalfEnvironmentId(IntegerFilter secondHalfEnvironmentId) {
        this.secondHalfEnvironmentId = secondHalfEnvironmentId;
    }

    public IntegerFilter getExtraFirstHalfNpcDeckId() {
        return extraFirstHalfNpcDeckId;
    }

    public void setExtraFirstHalfNpcDeckId(IntegerFilter extraFirstHalfNpcDeckId) {
        this.extraFirstHalfNpcDeckId = extraFirstHalfNpcDeckId;
    }

    public IntegerFilter getExtraSecondHalfNpcDeckId() {
        return extraSecondHalfNpcDeckId;
    }

    public void setExtraSecondHalfNpcDeckId(IntegerFilter extraSecondHalfNpcDeckId) {
        this.extraSecondHalfNpcDeckId = extraSecondHalfNpcDeckId;
    }

    public IntegerFilter getConsumeAp() {
        return consumeAp;
    }

    public void setConsumeAp(IntegerFilter consumeAp) {
        this.consumeAp = consumeAp;
    }

    public IntegerFilter getKickOffType() {
        return kickOffType;
    }

    public void setKickOffType(IntegerFilter kickOffType) {
        this.kickOffType = kickOffType;
    }

    public IntegerFilter getMatchMinute() {
        return matchMinute;
    }

    public void setMatchMinute(IntegerFilter matchMinute) {
        this.matchMinute = matchMinute;
    }

    public IntegerFilter getEnableExtra() {
        return enableExtra;
    }

    public void setEnableExtra(IntegerFilter enableExtra) {
        this.enableExtra = enableExtra;
    }

    public IntegerFilter getEnablePk() {
        return enablePk;
    }

    public void setEnablePk(IntegerFilter enablePk) {
        this.enablePk = enablePk;
    }

    public IntegerFilter getAiLevel() {
        return aiLevel;
    }

    public void setAiLevel(IntegerFilter aiLevel) {
        this.aiLevel = aiLevel;
    }

    public IntegerFilter getStartAtSecondHalf() {
        return startAtSecondHalf;
    }

    public void setStartAtSecondHalf(IntegerFilter startAtSecondHalf) {
        this.startAtSecondHalf = startAtSecondHalf;
    }

    public IntegerFilter getStartScore() {
        return startScore;
    }

    public void setStartScore(IntegerFilter startScore) {
        this.startScore = startScore;
    }

    public IntegerFilter getStartScoreOpponent() {
        return startScoreOpponent;
    }

    public void setStartScoreOpponent(IntegerFilter startScoreOpponent) {
        this.startScoreOpponent = startScoreOpponent;
    }

    public IntegerFilter getConditionId() {
        return conditionId;
    }

    public void setConditionId(IntegerFilter conditionId) {
        this.conditionId = conditionId;
    }

    public IntegerFilter getOptionId() {
        return optionId;
    }

    public void setOptionId(IntegerFilter optionId) {
        this.optionId = optionId;
    }

    public IntegerFilter getDeckConditionId() {
        return deckConditionId;
    }

    public void setDeckConditionId(IntegerFilter deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MGuerillaQuestStageCriteria that = (MGuerillaQuestStageCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(worldId, that.worldId) &&
            Objects.equals(number, that.number) &&
            Objects.equals(difficulty, that.difficulty) &&
            Objects.equals(stageUnlockPattern, that.stageUnlockPattern) &&
            Objects.equals(storyOnly, that.storyOnly) &&
            Objects.equals(firstHalfNpcDeckId, that.firstHalfNpcDeckId) &&
            Objects.equals(firstHalfEnvironmentId, that.firstHalfEnvironmentId) &&
            Objects.equals(secondHalfNpcDeckId, that.secondHalfNpcDeckId) &&
            Objects.equals(secondHalfEnvironmentId, that.secondHalfEnvironmentId) &&
            Objects.equals(extraFirstHalfNpcDeckId, that.extraFirstHalfNpcDeckId) &&
            Objects.equals(extraSecondHalfNpcDeckId, that.extraSecondHalfNpcDeckId) &&
            Objects.equals(consumeAp, that.consumeAp) &&
            Objects.equals(kickOffType, that.kickOffType) &&
            Objects.equals(matchMinute, that.matchMinute) &&
            Objects.equals(enableExtra, that.enableExtra) &&
            Objects.equals(enablePk, that.enablePk) &&
            Objects.equals(aiLevel, that.aiLevel) &&
            Objects.equals(startAtSecondHalf, that.startAtSecondHalf) &&
            Objects.equals(startScore, that.startScore) &&
            Objects.equals(startScoreOpponent, that.startScoreOpponent) &&
            Objects.equals(conditionId, that.conditionId) &&
            Objects.equals(optionId, that.optionId) &&
            Objects.equals(deckConditionId, that.deckConditionId) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        worldId,
        number,
        difficulty,
        stageUnlockPattern,
        storyOnly,
        firstHalfNpcDeckId,
        firstHalfEnvironmentId,
        secondHalfNpcDeckId,
        secondHalfEnvironmentId,
        extraFirstHalfNpcDeckId,
        extraSecondHalfNpcDeckId,
        consumeAp,
        kickOffType,
        matchMinute,
        enableExtra,
        enablePk,
        aiLevel,
        startAtSecondHalf,
        startScore,
        startScoreOpponent,
        conditionId,
        optionId,
        deckConditionId,
        idId
        );
    }

    @Override
    public String toString() {
        return "MGuerillaQuestStageCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (worldId != null ? "worldId=" + worldId + ", " : "") +
                (number != null ? "number=" + number + ", " : "") +
                (difficulty != null ? "difficulty=" + difficulty + ", " : "") +
                (stageUnlockPattern != null ? "stageUnlockPattern=" + stageUnlockPattern + ", " : "") +
                (storyOnly != null ? "storyOnly=" + storyOnly + ", " : "") +
                (firstHalfNpcDeckId != null ? "firstHalfNpcDeckId=" + firstHalfNpcDeckId + ", " : "") +
                (firstHalfEnvironmentId != null ? "firstHalfEnvironmentId=" + firstHalfEnvironmentId + ", " : "") +
                (secondHalfNpcDeckId != null ? "secondHalfNpcDeckId=" + secondHalfNpcDeckId + ", " : "") +
                (secondHalfEnvironmentId != null ? "secondHalfEnvironmentId=" + secondHalfEnvironmentId + ", " : "") +
                (extraFirstHalfNpcDeckId != null ? "extraFirstHalfNpcDeckId=" + extraFirstHalfNpcDeckId + ", " : "") +
                (extraSecondHalfNpcDeckId != null ? "extraSecondHalfNpcDeckId=" + extraSecondHalfNpcDeckId + ", " : "") +
                (consumeAp != null ? "consumeAp=" + consumeAp + ", " : "") +
                (kickOffType != null ? "kickOffType=" + kickOffType + ", " : "") +
                (matchMinute != null ? "matchMinute=" + matchMinute + ", " : "") +
                (enableExtra != null ? "enableExtra=" + enableExtra + ", " : "") +
                (enablePk != null ? "enablePk=" + enablePk + ", " : "") +
                (aiLevel != null ? "aiLevel=" + aiLevel + ", " : "") +
                (startAtSecondHalf != null ? "startAtSecondHalf=" + startAtSecondHalf + ", " : "") +
                (startScore != null ? "startScore=" + startScore + ", " : "") +
                (startScoreOpponent != null ? "startScoreOpponent=" + startScoreOpponent + ", " : "") +
                (conditionId != null ? "conditionId=" + conditionId + ", " : "") +
                (optionId != null ? "optionId=" + optionId + ", " : "") +
                (deckConditionId != null ? "deckConditionId=" + deckConditionId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
