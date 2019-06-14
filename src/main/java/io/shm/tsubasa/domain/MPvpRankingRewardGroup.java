package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPvpRankingRewardGroup.
 */
@Entity
@Table(name = "m_pvp_ranking_reward_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpRankingRewardGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "content_type", nullable = false)
    private Integer contentType;

    @Column(name = "content_id")
    private Integer contentId;

    @NotNull
    @Column(name = "content_amount", nullable = false)
    private Integer contentAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MPvpRankingRewardGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MPvpRankingRewardGroup contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MPvpRankingRewardGroup contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MPvpRankingRewardGroup contentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
        return this;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpRankingRewardGroup)) {
            return false;
        }
        return id != null && id.equals(((MPvpRankingRewardGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpRankingRewardGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
