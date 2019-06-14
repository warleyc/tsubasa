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
 * Criteria class for the {@link io.shm.tsubasa.domain.MSellCardCoin} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MSellCardCoinResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-sell-card-coins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MSellCardCoinCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter groupNum;

    private IntegerFilter level;

    private IntegerFilter coin;

    public MSellCardCoinCriteria(){
    }

    public MSellCardCoinCriteria(MSellCardCoinCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.groupNum = other.groupNum == null ? null : other.groupNum.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.coin = other.coin == null ? null : other.coin.copy();
    }

    @Override
    public MSellCardCoinCriteria copy() {
        return new MSellCardCoinCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(IntegerFilter groupNum) {
        this.groupNum = groupNum;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
    }

    public IntegerFilter getCoin() {
        return coin;
    }

    public void setCoin(IntegerFilter coin) {
        this.coin = coin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MSellCardCoinCriteria that = (MSellCardCoinCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupNum, that.groupNum) &&
            Objects.equals(level, that.level) &&
            Objects.equals(coin, that.coin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        groupNum,
        level,
        coin
        );
    }

    @Override
    public String toString() {
        return "MSellCardCoinCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupNum != null ? "groupNum=" + groupNum + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (coin != null ? "coin=" + coin + ", " : "") +
            "}";
    }

}
