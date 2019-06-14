package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMarathonLoopReward.
 */
@Entity
@Table(name = "m_marathon_loop_reward")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMarathonLoopReward implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @NotNull
    @Column(name = "loop_point", nullable = false)
    private Integer loopPoint;

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

    public MMarathonLoopReward eventId(Integer eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getLoopPoint() {
        return loopPoint;
    }

    public MMarathonLoopReward loopPoint(Integer loopPoint) {
        this.loopPoint = loopPoint;
        return this;
    }

    public void setLoopPoint(Integer loopPoint) {
        this.loopPoint = loopPoint;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMarathonLoopReward)) {
            return false;
        }
        return id != null && id.equals(((MMarathonLoopReward) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMarathonLoopReward{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", loopPoint=" + getLoopPoint() +
            "}";
    }
}
