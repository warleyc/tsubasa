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
 * Criteria class for the {@link io.shm.tsubasa.domain.MContentableCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MContentableCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-contentable-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MContentableCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter playableCardId;

    private IntegerFilter level;

    private IntegerFilter actionMainLevel;

    private IntegerFilter actionSub1Level;

    private IntegerFilter actionSub2Level;

    private IntegerFilter actionSub3Level;

    private IntegerFilter actionSub4Level;

    private IntegerFilter plusRate;

    public MContentableCardCriteria(){
    }

    public MContentableCardCriteria(MContentableCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.playableCardId = other.playableCardId == null ? null : other.playableCardId.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.actionMainLevel = other.actionMainLevel == null ? null : other.actionMainLevel.copy();
        this.actionSub1Level = other.actionSub1Level == null ? null : other.actionSub1Level.copy();
        this.actionSub2Level = other.actionSub2Level == null ? null : other.actionSub2Level.copy();
        this.actionSub3Level = other.actionSub3Level == null ? null : other.actionSub3Level.copy();
        this.actionSub4Level = other.actionSub4Level == null ? null : other.actionSub4Level.copy();
        this.plusRate = other.plusRate == null ? null : other.plusRate.copy();
    }

    @Override
    public MContentableCardCriteria copy() {
        return new MContentableCardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getPlayableCardId() {
        return playableCardId;
    }

    public void setPlayableCardId(IntegerFilter playableCardId) {
        this.playableCardId = playableCardId;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public IntegerFilter getActionMainLevel() {
        return actionMainLevel;
    }

    public void setActionMainLevel(IntegerFilter actionMainLevel) {
        this.actionMainLevel = actionMainLevel;
    }

    public IntegerFilter getActionSub1Level() {
        return actionSub1Level;
    }

    public void setActionSub1Level(IntegerFilter actionSub1Level) {
        this.actionSub1Level = actionSub1Level;
    }

    public IntegerFilter getActionSub2Level() {
        return actionSub2Level;
    }

    public void setActionSub2Level(IntegerFilter actionSub2Level) {
        this.actionSub2Level = actionSub2Level;
    }

    public IntegerFilter getActionSub3Level() {
        return actionSub3Level;
    }

    public void setActionSub3Level(IntegerFilter actionSub3Level) {
        this.actionSub3Level = actionSub3Level;
    }

    public IntegerFilter getActionSub4Level() {
        return actionSub4Level;
    }

    public void setActionSub4Level(IntegerFilter actionSub4Level) {
        this.actionSub4Level = actionSub4Level;
    }

    public IntegerFilter getPlusRate() {
        return plusRate;
    }

    public void setPlusRate(IntegerFilter plusRate) {
        this.plusRate = plusRate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MContentableCardCriteria that = (MContentableCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(playableCardId, that.playableCardId) &&
            Objects.equals(level, that.level) &&
            Objects.equals(actionMainLevel, that.actionMainLevel) &&
            Objects.equals(actionSub1Level, that.actionSub1Level) &&
            Objects.equals(actionSub2Level, that.actionSub2Level) &&
            Objects.equals(actionSub3Level, that.actionSub3Level) &&
            Objects.equals(actionSub4Level, that.actionSub4Level) &&
            Objects.equals(plusRate, that.plusRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        playableCardId,
        level,
        actionMainLevel,
        actionSub1Level,
        actionSub2Level,
        actionSub3Level,
        actionSub4Level,
        plusRate
        );
    }

    @Override
    public String toString() {
        return "MContentableCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (playableCardId != null ? "playableCardId=" + playableCardId + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (actionMainLevel != null ? "actionMainLevel=" + actionMainLevel + ", " : "") +
                (actionSub1Level != null ? "actionSub1Level=" + actionSub1Level + ", " : "") +
                (actionSub2Level != null ? "actionSub2Level=" + actionSub2Level + ", " : "") +
                (actionSub3Level != null ? "actionSub3Level=" + actionSub3Level + ", " : "") +
                (actionSub4Level != null ? "actionSub4Level=" + actionSub4Level + ", " : "") +
                (plusRate != null ? "plusRate=" + plusRate + ", " : "") +
            "}";
    }

}
