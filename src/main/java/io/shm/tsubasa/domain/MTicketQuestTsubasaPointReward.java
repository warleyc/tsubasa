package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTicketQuestTsubasaPointReward.
 */
@Entity
@Table(name = "m_ticket_quest_tsubasa_point_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTicketQuestTsubasaPointReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "stage_id", nullable = false)
    private Integer stageId;

    @NotNull
    @Column(name = "tsubasa_point", nullable = false)
    private Integer tsubasaPoint;

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

    public Integer getStageId() {
        return stageId;
    }

    public MTicketQuestTsubasaPointReward stageId(Integer stageId) {
        this.stageId = stageId;
        return this;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getTsubasaPoint() {
        return tsubasaPoint;
    }

    public MTicketQuestTsubasaPointReward tsubasaPoint(Integer tsubasaPoint) {
        this.tsubasaPoint = tsubasaPoint;
        return this;
    }

    public void setTsubasaPoint(Integer tsubasaPoint) {
        this.tsubasaPoint = tsubasaPoint;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MTicketQuestTsubasaPointReward contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MTicketQuestTsubasaPointReward contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MTicketQuestTsubasaPointReward contentAmount(Integer contentAmount) {
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
        if (!(o instanceof MTicketQuestTsubasaPointReward)) {
            return false;
        }
        return id != null && id.equals(((MTicketQuestTsubasaPointReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTicketQuestTsubasaPointReward{" +
            "id=" + getId() +
            ", stageId=" + getStageId() +
            ", tsubasaPoint=" + getTsubasaPoint() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            "}";
    }
}
