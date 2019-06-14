package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MQuestSpecialReward.
 */
@Entity
@Table(name = "m_quest_special_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MQuestSpecialReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @NotNull
    @Column(name = "jhi_rank", nullable = false)
    private Integer rank;

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

    public MQuestSpecialReward groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getWeight() {
        return weight;
    }

    public MQuestSpecialReward weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getRank() {
        return rank;
    }

    public MQuestSpecialReward rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MQuestSpecialReward contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MQuestSpecialReward contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MQuestSpecialReward contentAmount(Integer contentAmount) {
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
        if (!(o instanceof MQuestSpecialReward)) {
            return false;
        }
        return id != null && id.equals(((MQuestSpecialReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MQuestSpecialReward{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", weight=" + getWeight() +
            ", rank=" + getRank() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
