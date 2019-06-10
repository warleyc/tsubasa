package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MExtraNews.
 */
@Entity
@Table(name = "m_extra_news")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MExtraNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    
    @Lob
    @Column(name = "asset_name", nullable = false)
    private String assetName;

    
    @Lob
    @Column(name = "webview_id", nullable = false)
    private String webviewId;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public MExtraNews orderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getAssetName() {
        return assetName;
    }

    public MExtraNews assetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getWebviewId() {
        return webviewId;
    }

    public MExtraNews webviewId(String webviewId) {
        this.webviewId = webviewId;
        return this;
    }

    public void setWebviewId(String webviewId) {
        this.webviewId = webviewId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MExtraNews startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MExtraNews endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MExtraNews)) {
            return false;
        }
        return id != null && id.equals(((MExtraNews) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MExtraNews{" +
            "id=" + getId() +
            ", orderNum=" + getOrderNum() +
            ", assetName='" + getAssetName() + "'" +
            ", webviewId='" + getWebviewId() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
