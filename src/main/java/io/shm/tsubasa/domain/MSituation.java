package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MSituation.
 */
@Entity
@Table(name = "m_situation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MSituation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "kickoff", nullable = false)
    private Integer kickoff;

    @NotNull
    @Column(name = "penalty_kick", nullable = false)
    private Integer penaltyKick;

    @NotNull
    @Column(name = "match_interval", nullable = false)
    private Integer matchInterval;

    @NotNull
    @Column(name = "out_of_play", nullable = false)
    private Integer outOfPlay;

    @NotNull
    @Column(name = "foul", nullable = false)
    private Integer foul;

    @NotNull
    @Column(name = "goal", nullable = false)
    private Integer goal;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

    @NotNull
    @Column(name = "jhi_time", nullable = false)
    private Integer time;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKickoff() {
        return kickoff;
    }

    public MSituation kickoff(Integer kickoff) {
        this.kickoff = kickoff;
        return this;
    }

    public void setKickoff(Integer kickoff) {
        this.kickoff = kickoff;
    }

    public Integer getPenaltyKick() {
        return penaltyKick;
    }

    public MSituation penaltyKick(Integer penaltyKick) {
        this.penaltyKick = penaltyKick;
        return this;
    }

    public void setPenaltyKick(Integer penaltyKick) {
        this.penaltyKick = penaltyKick;
    }

    public Integer getMatchInterval() {
        return matchInterval;
    }

    public MSituation matchInterval(Integer matchInterval) {
        this.matchInterval = matchInterval;
        return this;
    }

    public void setMatchInterval(Integer matchInterval) {
        this.matchInterval = matchInterval;
    }

    public Integer getOutOfPlay() {
        return outOfPlay;
    }

    public MSituation outOfPlay(Integer outOfPlay) {
        this.outOfPlay = outOfPlay;
        return this;
    }

    public void setOutOfPlay(Integer outOfPlay) {
        this.outOfPlay = outOfPlay;
    }

    public Integer getFoul() {
        return foul;
    }

    public MSituation foul(Integer foul) {
        this.foul = foul;
        return this;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getGoal() {
        return goal;
    }

    public MSituation goal(Integer goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getScore() {
        return score;
    }

    public MSituation score(Integer score) {
        this.score = score;
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public MSituation time(Integer time) {
        this.time = time;
        return this;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MSituation)) {
            return false;
        }
        return id != null && id.equals(((MSituation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MSituation{" +
            "id=" + getId() +
            ", kickoff=" + getKickoff() +
            ", penaltyKick=" + getPenaltyKick() +
            ", matchInterval=" + getMatchInterval() +
            ", outOfPlay=" + getOutOfPlay() +
            ", foul=" + getFoul() +
            ", goal=" + getGoal() +
            ", score=" + getScore() +
            ", time=" + getTime() +
            "}";
    }
}
