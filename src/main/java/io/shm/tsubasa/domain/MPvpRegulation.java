package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPvpRegulation.
 */
@Entity
@Table(name = "m_pvp_regulation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPvpRegulation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    @NotNull
    @Column(name = "match_option_id", nullable = false)
    private Integer matchOptionId;

    @NotNull
    @Column(name = "deck_condition_id", nullable = false)
    private Integer deckConditionId;

    @NotNull
    @Column(name = "rule_tutorial_id", nullable = false)
    private Integer ruleTutorialId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mPvpRegulations")
    private MMatchOption mmatchoption;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MPvpRegulation startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MPvpRegulation endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getMatchOptionId() {
        return matchOptionId;
    }

    public MPvpRegulation matchOptionId(Integer matchOptionId) {
        this.matchOptionId = matchOptionId;
        return this;
    }

    public void setMatchOptionId(Integer matchOptionId) {
        this.matchOptionId = matchOptionId;
    }

    public Integer getDeckConditionId() {
        return deckConditionId;
    }

    public MPvpRegulation deckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
        return this;
    }

    public void setDeckConditionId(Integer deckConditionId) {
        this.deckConditionId = deckConditionId;
    }

    public Integer getRuleTutorialId() {
        return ruleTutorialId;
    }

    public MPvpRegulation ruleTutorialId(Integer ruleTutorialId) {
        this.ruleTutorialId = ruleTutorialId;
        return this;
    }

    public void setRuleTutorialId(Integer ruleTutorialId) {
        this.ruleTutorialId = ruleTutorialId;
    }

    public MMatchOption getMmatchoption() {
        return mmatchoption;
    }

    public MPvpRegulation mmatchoption(MMatchOption mMatchOption) {
        this.mmatchoption = mMatchOption;
        return this;
    }

    public void setMmatchoption(MMatchOption mMatchOption) {
        this.mmatchoption = mMatchOption;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPvpRegulation)) {
            return false;
        }
        return id != null && id.equals(((MPvpRegulation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPvpRegulation{" +
            "id=" + getId() +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", matchOptionId=" + getMatchOptionId() +
            ", deckConditionId=" + getDeckConditionId() +
            ", ruleTutorialId=" + getRuleTutorialId() +
            "}";
    }
}
