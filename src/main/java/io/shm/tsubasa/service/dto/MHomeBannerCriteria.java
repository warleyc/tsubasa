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
 * Criteria class for the {@link io.shm.tsubasa.domain.MHomeBanner} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MHomeBannerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-home-banners?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MHomeBannerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter bannerType;

    private IntegerFilter startAt;

    private IntegerFilter endAt;

    private IntegerFilter labelEndAt;

    private IntegerFilter sceneTransition;

    private IntegerFilter orderNum;

    private IntegerFilter appealType;

    private IntegerFilter appealContentId;

    public MHomeBannerCriteria(){
    }

    public MHomeBannerCriteria(MHomeBannerCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.bannerType = other.bannerType == null ? null : other.bannerType.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.endAt = other.endAt == null ? null : other.endAt.copy();
        this.labelEndAt = other.labelEndAt == null ? null : other.labelEndAt.copy();
        this.sceneTransition = other.sceneTransition == null ? null : other.sceneTransition.copy();
        this.orderNum = other.orderNum == null ? null : other.orderNum.copy();
        this.appealType = other.appealType == null ? null : other.appealType.copy();
        this.appealContentId = other.appealContentId == null ? null : other.appealContentId.copy();
    }

    @Override
    public MHomeBannerCriteria copy() {
        return new MHomeBannerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getBannerType() {
        return bannerType;
    }

    public void setBannerType(IntegerFilter bannerType) {
        this.bannerType = bannerType;
    }

    public IntegerFilter getStartAt() {
        return startAt;
    }

    public void setStartAt(IntegerFilter startAt) {
        this.startAt = startAt;
    }

    public IntegerFilter getEndAt() {
        return endAt;
    }

    public void setEndAt(IntegerFilter endAt) {
        this.endAt = endAt;
    }

    public IntegerFilter getLabelEndAt() {
        return labelEndAt;
    }

    public void setLabelEndAt(IntegerFilter labelEndAt) {
        this.labelEndAt = labelEndAt;
    }

    public IntegerFilter getSceneTransition() {
        return sceneTransition;
    }

    public void setSceneTransition(IntegerFilter sceneTransition) {
        this.sceneTransition = sceneTransition;
    }

    public IntegerFilter getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(IntegerFilter orderNum) {
        this.orderNum = orderNum;
    }

    public IntegerFilter getAppealType() {
        return appealType;
    }

    public void setAppealType(IntegerFilter appealType) {
        this.appealType = appealType;
    }

    public IntegerFilter getAppealContentId() {
        return appealContentId;
    }

    public void setAppealContentId(IntegerFilter appealContentId) {
        this.appealContentId = appealContentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MHomeBannerCriteria that = (MHomeBannerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(bannerType, that.bannerType) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(endAt, that.endAt) &&
            Objects.equals(labelEndAt, that.labelEndAt) &&
            Objects.equals(sceneTransition, that.sceneTransition) &&
            Objects.equals(orderNum, that.orderNum) &&
            Objects.equals(appealType, that.appealType) &&
            Objects.equals(appealContentId, that.appealContentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        bannerType,
        startAt,
        endAt,
        labelEndAt,
        sceneTransition,
        orderNum,
        appealType,
        appealContentId
        );
    }

    @Override
    public String toString() {
        return "MHomeBannerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (bannerType != null ? "bannerType=" + bannerType + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (endAt != null ? "endAt=" + endAt + ", " : "") +
                (labelEndAt != null ? "labelEndAt=" + labelEndAt + ", " : "") +
                (sceneTransition != null ? "sceneTransition=" + sceneTransition + ", " : "") +
                (orderNum != null ? "orderNum=" + orderNum + ", " : "") +
                (appealType != null ? "appealType=" + appealType + ", " : "") +
                (appealContentId != null ? "appealContentId=" + appealContentId + ", " : "") +
            "}";
    }

}
