package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMarathonEffectiveCard.
 */
@Entity
@Table(name = "m_marathon_effective_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMarathonEffectiveCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "playable_card_id", nullable = false)
    private Integer playableCardId;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Integer rate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public MMarathonEffectiveCard eventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPlayableCardId() {
        return playableCardId;
    }

    public MMarathonEffectiveCard playableCardId(Integer playableCardId) {
        this.playableCardId = playableCardId;
        return this;
    }

    public void setPlayableCardId(Integer playableCardId) {
        this.playableCardId = playableCardId;
    }

    public Integer getRate() {
        return rate;
    }

    public MMarathonEffectiveCard rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMarathonEffectiveCard)) {
            return false;
        }
        return id != null && id.equals(((MMarathonEffectiveCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMarathonEffectiveCard{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", playableCardId=" + getPlayableCardId() +
            ", rate=" + getRate() +
            "}";
    }
}
