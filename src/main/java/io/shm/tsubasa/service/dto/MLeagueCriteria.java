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
 * Criteria class for the {@link io.shm.tsubasa.domain.MLeague} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MLeagueResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-leagues?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MLeagueCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter hierarchy;

    private IntegerFilter weight;

    private IntegerFilter rooms;

    private IntegerFilter promotionRate;

    private IntegerFilter demotionRate;

    private IntegerFilter totalParameterRangeUpper;

    private IntegerFilter totalParameterRangeLower;

    private IntegerFilter requiredMatches;

    private IntegerFilter startWeekId;

    public MLeagueCriteria(){
    }

    public MLeagueCriteria(MLeagueCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.hierarchy = other.hierarchy == null ? null : other.hierarchy.copy();
        this.weight = other.weight == null ? null : other.weight.copy();
        this.rooms = other.rooms == null ? null : other.rooms.copy();
        this.promotionRate = other.promotionRate == null ? null : other.promotionRate.copy();
        this.demotionRate = other.demotionRate == null ? null : other.demotionRate.copy();
        this.totalParameterRangeUpper = other.totalParameterRangeUpper == null ? null : other.totalParameterRangeUpper.copy();
        this.totalParameterRangeLower = other.totalParameterRangeLower == null ? null : other.totalParameterRangeLower.copy();
        this.requiredMatches = other.requiredMatches == null ? null : other.requiredMatches.copy();
        this.startWeekId = other.startWeekId == null ? null : other.startWeekId.copy();
    }

    @Override
    public MLeagueCriteria copy() {
        return new MLeagueCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(IntegerFilter hierarchy) {
        this.hierarchy = hierarchy;
    }

    public IntegerFilter getWeight() {
        return weight;
    }

    public void setWeight(IntegerFilter weight) {
        this.weight = weight;
    }

    public IntegerFilter getRooms() {
        return rooms;
    }

    public void setRooms(IntegerFilter rooms) {
        this.rooms = rooms;
    }

    public IntegerFilter getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(IntegerFilter promotionRate) {
        this.promotionRate = promotionRate;
    }

    public IntegerFilter getDemotionRate() {
        return demotionRate;
    }

    public void setDemotionRate(IntegerFilter demotionRate) {
        this.demotionRate = demotionRate;
    }

    public IntegerFilter getTotalParameterRangeUpper() {
        return totalParameterRangeUpper;
    }

    public void setTotalParameterRangeUpper(IntegerFilter totalParameterRangeUpper) {
        this.totalParameterRangeUpper = totalParameterRangeUpper;
    }

    public IntegerFilter getTotalParameterRangeLower() {
        return totalParameterRangeLower;
    }

    public void setTotalParameterRangeLower(IntegerFilter totalParameterRangeLower) {
        this.totalParameterRangeLower = totalParameterRangeLower;
    }

    public IntegerFilter getRequiredMatches() {
        return requiredMatches;
    }

    public void setRequiredMatches(IntegerFilter requiredMatches) {
        this.requiredMatches = requiredMatches;
    }

    public IntegerFilter getStartWeekId() {
        return startWeekId;
    }

    public void setStartWeekId(IntegerFilter startWeekId) {
        this.startWeekId = startWeekId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MLeagueCriteria that = (MLeagueCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(hierarchy, that.hierarchy) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(rooms, that.rooms) &&
            Objects.equals(promotionRate, that.promotionRate) &&
            Objects.equals(demotionRate, that.demotionRate) &&
            Objects.equals(totalParameterRangeUpper, that.totalParameterRangeUpper) &&
            Objects.equals(totalParameterRangeLower, that.totalParameterRangeLower) &&
            Objects.equals(requiredMatches, that.requiredMatches) &&
            Objects.equals(startWeekId, that.startWeekId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        hierarchy,
        weight,
        rooms,
        promotionRate,
        demotionRate,
        totalParameterRangeUpper,
        totalParameterRangeLower,
        requiredMatches,
        startWeekId
        );
    }

    @Override
    public String toString() {
        return "MLeagueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (hierarchy != null ? "hierarchy=" + hierarchy + ", " : "") +
                (weight != null ? "weight=" + weight + ", " : "") +
                (rooms != null ? "rooms=" + rooms + ", " : "") +
                (promotionRate != null ? "promotionRate=" + promotionRate + ", " : "") +
                (demotionRate != null ? "demotionRate=" + demotionRate + ", " : "") +
                (totalParameterRangeUpper != null ? "totalParameterRangeUpper=" + totalParameterRangeUpper + ", " : "") +
                (totalParameterRangeLower != null ? "totalParameterRangeLower=" + totalParameterRangeLower + ", " : "") +
                (requiredMatches != null ? "requiredMatches=" + requiredMatches + ", " : "") +
                (startWeekId != null ? "startWeekId=" + startWeekId + ", " : "") +
            "}";
    }

}
