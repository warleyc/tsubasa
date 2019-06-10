package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MExtraNews} entity.
 */
public class MExtraNewsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer orderNum;

    
    @Lob
    private String assetName;

    
    @Lob
    private String webviewId;

    @NotNull
    private Integer startAt;

    @NotNull
    private Integer endAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getWebviewId() {
        return webviewId;
    }

    public void setWebviewId(String webviewId) {
        this.webviewId = webviewId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MExtraNewsDTO mExtraNewsDTO = (MExtraNewsDTO) o;
        if (mExtraNewsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mExtraNewsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MExtraNewsDTO{" +
            "id=" + getId() +
            ", orderNum=" + getOrderNum() +
            ", assetName='" + getAssetName() + "'" +
            ", webviewId='" + getWebviewId() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
