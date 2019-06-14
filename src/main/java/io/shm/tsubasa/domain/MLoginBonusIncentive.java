package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLoginBonusIncentive.
 */
@Entity
@Table(name = "m_login_bonus_incentive")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLoginBonusIncentive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "round_id", nullable = false)
    private Integer roundId;

    @NotNull
    @Column(name = "day", nullable = false)
    private Integer day;

    @NotNull
    @Column(name = "content_type", nullable = false)
    private Integer contentType;

    @Column(name = "content_id")
    private Integer contentId;

    @NotNull
    @Column(name = "content_amount", nullable = false)
    private Integer contentAmount;

    
    @Lob
    @Column(name = "present_detail", nullable = false)
    private String presentDetail;

    @NotNull
    @Column(name = "is_decorated", nullable = false)
    private Integer isDecorated;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoundId() {
        return roundId;
    }

    public MLoginBonusIncentive roundId(Integer roundId) {
        this.roundId = roundId;
        return this;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getDay() {
        return day;
    }

    public MLoginBonusIncentive day(Integer day) {
        this.day = day;
        return this;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getContentType() {
        return contentType;
    }

    public MLoginBonusIncentive contentType(Integer contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public MLoginBonusIncentive contentId(Integer contentId) {
        this.contentId = contentId;
        return this;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentAmount() {
        return contentAmount;
    }

    public MLoginBonusIncentive contentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
        return this;
    }

    public void setContentAmount(Integer contentAmount) {
        this.contentAmount = contentAmount;
    }

    public String getPresentDetail() {
        return presentDetail;
    }

    public MLoginBonusIncentive presentDetail(String presentDetail) {
        this.presentDetail = presentDetail;
        return this;
    }

    public void setPresentDetail(String presentDetail) {
        this.presentDetail = presentDetail;
    }

    public Integer getIsDecorated() {
        return isDecorated;
    }

    public MLoginBonusIncentive isDecorated(Integer isDecorated) {
        this.isDecorated = isDecorated;
        return this;
    }

    public void setIsDecorated(Integer isDecorated) {
        this.isDecorated = isDecorated;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLoginBonusIncentive)) {
            return false;
        }
        return id != null && id.equals(((MLoginBonusIncentive) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLoginBonusIncentive{" +
            "id=" + getId() +
            ", roundId=" + getRoundId() +
            ", day=" + getDay() +
            ", contentType=" + getContentType() +
            ", contentId=" + getContentId() +
            ", contentAmount=" + getContentAmount() +
            ", presentDetail='" + getPresentDetail() + "'" +
            ", isDecorated=" + getIsDecorated() +
            "}";
    }
}
