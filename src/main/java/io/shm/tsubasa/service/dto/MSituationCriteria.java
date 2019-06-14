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
 * Criteria class for the {@link io.shm.tsubasa.domain.MSituation} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MSituationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-situations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MSituationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter kickoff;

    private IntegerFilter penaltyKick;

    private IntegerFilter matchInterval;

    private IntegerFilter outOfPlay;

    private IntegerFilter foul;

    private IntegerFilter goal;

    private IntegerFilter score;

    private IntegerFilter time;

    public MSituationCriteria(){
    }

    public MSituationCriteria(MSituationCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.kickoff = other.kickoff == null ? null : other.kickoff.copy();
        this.penaltyKick = other.penaltyKick == null ? null : other.penaltyKick.copy();
        this.matchInterval = other.matchInterval == null ? null : other.matchInterval.copy();
        this.outOfPlay = other.outOfPlay == null ? null : other.outOfPlay.copy();
        this.foul = other.foul == null ? null : other.foul.copy();
        this.goal = other.goal == null ? null : other.goal.copy();
        this.score = other.score == null ? null : other.score.copy();
        this.time = other.time == null ? null : other.time.copy();
    }

    @Override
    public MSituationCriteria copy() {
        return new MSituationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getKickoff() {
        return kickoff;
    }

    public void setKickoff(IntegerFilter kickoff) {
        this.kickoff = kickoff;
    }

    public IntegerFilter getPenaltyKick() {
        return penaltyKick;
    }

    public void setPenaltyKick(IntegerFilter penaltyKick) {
        this.penaltyKick = penaltyKick;
    }

    public IntegerFilter getMatchInterval() {
        return matchInterval;
    }

    public void setMatchInterval(IntegerFilter matchInterval) {
        this.matchInterval = matchInterval;
    }

    public IntegerFilter getOutOfPlay() {
        return outOfPlay;
    }

    public void setOutOfPlay(IntegerFilter outOfPlay) {
        this.outOfPlay = outOfPlay;
    }

    public IntegerFilter getFoul() {
        return foul;
    }

    public void setFoul(IntegerFilter foul) {
        this.foul = foul;
    }

    public IntegerFilter getGoal() {
        return goal;
    }

    public void setGoal(IntegerFilter goal) {
        this.goal = goal;
    }

    public IntegerFilter getScore() {
        return score;
    }

    public void setScore(IntegerFilter score) {
        this.score = score;
    }

    public IntegerFilter getTime() {
        return time;
    }

    public void setTime(IntegerFilter time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MSituationCriteria that = (MSituationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(kickoff, that.kickoff) &&
            Objects.equals(penaltyKick, that.penaltyKick) &&
            Objects.equals(matchInterval, that.matchInterval) &&
            Objects.equals(outOfPlay, that.outOfPlay) &&
            Objects.equals(foul, that.foul) &&
            Objects.equals(goal, that.goal) &&
            Objects.equals(score, that.score) &&
            Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        kickoff,
        penaltyKick,
        matchInterval,
        outOfPlay,
        foul,
        goal,
        score,
        time
        );
    }

    @Override
    public String toString() {
        return "MSituationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (kickoff != null ? "kickoff=" + kickoff + ", " : "") +
                (penaltyKick != null ? "penaltyKick=" + penaltyKick + ", " : "") +
                (matchInterval != null ? "matchInterval=" + matchInterval + ", " : "") +
                (outOfPlay != null ? "outOfPlay=" + outOfPlay + ", " : "") +
                (foul != null ? "foul=" + foul + ", " : "") +
                (goal != null ? "goal=" + goal + ", " : "") +
                (score != null ? "score=" + score + ", " : "") +
                (time != null ? "time=" + time + ", " : "") +
            "}";
    }

}
