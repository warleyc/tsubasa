package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MHomeMarquee.
 */
@Entity
@Table(name = "m_home_marquee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MHomeMarquee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "priority", nullable = false)
    private Integer priority;

    
    @Lob
    @Column(name = "marquee_text", nullable = false)
    private String marqueeText;

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

    public Integer getPriority() {
        return priority;
    }

    public MHomeMarquee priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMarqueeText() {
        return marqueeText;
    }

    public MHomeMarquee marqueeText(String marqueeText) {
        this.marqueeText = marqueeText;
        return this;
    }

    public void setMarqueeText(String marqueeText) {
        this.marqueeText = marqueeText;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MHomeMarquee startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MHomeMarquee endAt(Integer endAt) {
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
        if (!(o instanceof MHomeMarquee)) {
            return false;
        }
        return id != null && id.equals(((MHomeMarquee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MHomeMarquee{" +
            "id=" + getId() +
            ", priority=" + getPriority() +
            ", marqueeText='" + getMarqueeText() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            "}";
    }
}
