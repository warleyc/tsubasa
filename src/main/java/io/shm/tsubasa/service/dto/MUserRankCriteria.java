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
 * Criteria class for the {@link io.shm.tsubasa.domain.MUserRank} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MUserRankResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-user-ranks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MUserRankCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rank;

    private IntegerFilter exp;

    private IntegerFilter questAp;

    private IntegerFilter maxFriendCount;

    public MUserRankCriteria(){
    }

    public MUserRankCriteria(MUserRankCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rank = other.rank == null ? null : other.rank.copy();
        this.exp = other.exp == null ? null : other.exp.copy();
        this.questAp = other.questAp == null ? null : other.questAp.copy();
        this.maxFriendCount = other.maxFriendCount == null ? null : other.maxFriendCount.copy();
    }

    @Override
    public MUserRankCriteria copy() {
        return new MUserRankCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRank() {
        return rank;
    }

    public void setRank(IntegerFilter rank) {
        this.rank = rank;
    }

    public IntegerFilter getExp() {
        return exp;
    }

    public void setExp(IntegerFilter exp) {
        this.exp = exp;
    }

    public IntegerFilter getQuestAp() {
        return questAp;
    }

    public void setQuestAp(IntegerFilter questAp) {
        this.questAp = questAp;
    }

    public IntegerFilter getMaxFriendCount() {
        return maxFriendCount;
    }

    public void setMaxFriendCount(IntegerFilter maxFriendCount) {
        this.maxFriendCount = maxFriendCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MUserRankCriteria that = (MUserRankCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rank, that.rank) &&
            Objects.equals(exp, that.exp) &&
            Objects.equals(questAp, that.questAp) &&
            Objects.equals(maxFriendCount, that.maxFriendCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rank,
        exp,
        questAp,
        maxFriendCount
        );
    }

    @Override
    public String toString() {
        return "MUserRankCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rank != null ? "rank=" + rank + ", " : "") +
                (exp != null ? "exp=" + exp + ", " : "") +
                (questAp != null ? "questAp=" + questAp + ", " : "") +
                (maxFriendCount != null ? "maxFriendCount=" + maxFriendCount + ", " : "") +
            "}";
    }

}
