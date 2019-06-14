package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MHomeBanner.
 */
@Entity
@Table(name = "m_home_banner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MHomeBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "banner_type", nullable = false)
    private Integer bannerType;

    
    @Lob
    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    @NotNull
    @Column(name = "label_end_at", nullable = false)
    private Integer labelEndAt;

    @NotNull
    @Column(name = "scene_transition", nullable = false)
    private Integer sceneTransition;

    @Lob
    @Column(name = "scene_transition_param")
    private String sceneTransitionParam;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @Column(name = "appeal_type")
    private Integer appealType;

    @Column(name = "appeal_content_id")
    private Integer appealContentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public MHomeBanner bannerType(Integer bannerType) {
        this.bannerType = bannerType;
        return this;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public String getAssetName() {
        return assetName;
    }

    public MHomeBanner assetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MHomeBanner startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MHomeBanner endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getLabelEndAt() {
        return labelEndAt;
    }

    public MHomeBanner labelEndAt(Integer labelEndAt) {
        this.labelEndAt = labelEndAt;
        return this;
    }

    public void setLabelEndAt(Integer labelEndAt) {
        this.labelEndAt = labelEndAt;
    }

    public Integer getSceneTransition() {
        return sceneTransition;
    }

    public MHomeBanner sceneTransition(Integer sceneTransition) {
        this.sceneTransition = sceneTransition;
        return this;
    }

    public void setSceneTransition(Integer sceneTransition) {
        this.sceneTransition = sceneTransition;
    }

    public String getSceneTransitionParam() {
        return sceneTransitionParam;
    }

    public MHomeBanner sceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
        return this;
    }

    public void setSceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MHomeBanner orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getAppealType() {
        return appealType;
    }

    public MHomeBanner appealType(Integer appealType) {
        this.appealType = appealType;
        return this;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
    }

    public Integer getAppealContentId() {
        return appealContentId;
    }

    public MHomeBanner appealContentId(Integer appealContentId) {
        this.appealContentId = appealContentId;
        return this;
    }

    public void setAppealContentId(Integer appealContentId) {
        this.appealContentId = appealContentId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MHomeBanner)) {
            return false;
        }
        return id != null && id.equals(((MHomeBanner) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MHomeBanner{" +
            "id=" + getId() +
            ", bannerType=" + getBannerType() +
            ", assetName='" + getAssetName() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", labelEndAt=" + getLabelEndAt() +
            ", sceneTransition=" + getSceneTransition() +
            ", sceneTransitionParam='" + getSceneTransitionParam() + "'" +
            ", orderNum=" + getOrderNum() +
            ", appealType=" + getAppealType() +
            ", appealContentId=" + getAppealContentId() +
            "}";
    }
}
