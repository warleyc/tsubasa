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
 * Criteria class for the {@link io.shm.tsubasa.domain.MQuestClearRewardWeight} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MQuestClearRewardWeightResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-quest-clear-reward-weights?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MQuestClearRewardWeightCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter reward1;

    private IntegerFilter reward2;

    private IntegerFilter reward3;

    private IntegerFilter reward4;

    private IntegerFilter reward5;

    public MQuestClearRewardWeightCriteria(){
    }

    public MQuestClearRewardWeightCriteria(MQuestClearRewardWeightCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.reward1 = other.reward1 == null ? null : other.reward1.copy();
        this.reward2 = other.reward2 == null ? null : other.reward2.copy();
        this.reward3 = other.reward3 == null ? null : other.reward3.copy();
        this.reward4 = other.reward4 == null ? null : other.reward4.copy();
        this.reward5 = other.reward5 == null ? null : other.reward5.copy();
    }

    @Override
    public MQuestClearRewardWeightCriteria copy() {
        return new MQuestClearRewardWeightCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getReward1() {
        return reward1;
    }

    public void setReward1(IntegerFilter reward1) {
        this.reward1 = reward1;
    }

    public IntegerFilter getReward2() {
        return reward2;
    }

    public void setReward2(IntegerFilter reward2) {
        this.reward2 = reward2;
    }

    public IntegerFilter getReward3() {
        return reward3;
    }

    public void setReward3(IntegerFilter reward3) {
        this.reward3 = reward3;
    }

    public IntegerFilter getReward4() {
        return reward4;
    }

    public void setReward4(IntegerFilter reward4) {
        this.reward4 = reward4;
    }

    public IntegerFilter getReward5() {
        return reward5;
    }

    public void setReward5(IntegerFilter reward5) {
        this.reward5 = reward5;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MQuestClearRewardWeightCriteria that = (MQuestClearRewardWeightCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(reward1, that.reward1) &&
            Objects.equals(reward2, that.reward2) &&
            Objects.equals(reward3, that.reward3) &&
            Objects.equals(reward4, that.reward4) &&
            Objects.equals(reward5, that.reward5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        reward1,
        reward2,
        reward3,
        reward4,
        reward5
        );
    }

    @Override
    public String toString() {
        return "MQuestClearRewardWeightCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (reward1 != null ? "reward1=" + reward1 + ", " : "") +
                (reward2 != null ? "reward2=" + reward2 + ", " : "") +
                (reward3 != null ? "reward3=" + reward3 + ", " : "") +
                (reward4 != null ? "reward4=" + reward4 + ", " : "") +
                (reward5 != null ? "reward5=" + reward5 + ", " : "") +
            "}";
    }

}
