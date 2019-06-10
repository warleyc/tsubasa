package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MCommonBanner} entity.
 */
public class MCommonBannerDTO implements Serializable {

    private Long id;

    
    @Lob
    private String assetName;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;

    @NotNull
    private Integer labelEndAt;

    @NotNull
    private Integer sceneTransition;

    @Lob
    private String sceneTransitionParam;

    @NotNull
    private Integer orderNum;

    private Integer appealType;

    private Integer appealContentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getLabelEndAt() {
        return labelEndAt;
    }

    public void setLabelEndAt(Integer labelEndAt) {
        this.labelEndAt = labelEndAt;
    }

    public Integer getSceneTransition() {
        return sceneTransition;
    }

    public void setSceneTransition(Integer sceneTransition) {
        this.sceneTransition = sceneTransition;
    }

    public String getSceneTransitionParam() {
        return sceneTransitionParam;
    }

    public void setSceneTransitionParam(String sceneTransitionParam) {
        this.sceneTransitionParam = sceneTransitionParam;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getAppealType() {
        return appealType;
    }

    public void setAppealType(Integer appealType) {
        this.appealType = appealType;
    }

    public Integer getAppealContentId() {
        return appealContentId;
    }

    public void setAppealContentId(Integer appealContentId) {
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

        MCommonBannerDTO mCommonBannerDTO = (MCommonBannerDTO) o;
        if (mCommonBannerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mCommonBannerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MCommonBannerDTO{" +
            "id=" + getId() +
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
