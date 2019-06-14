package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MPenaltyKickCut.
 */
@Entity
@Table(name = "m_penalty_kick_cut")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPenaltyKickCut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "kicker_course", nullable = false)
    private Integer kickerCourse;

    @NotNull
    @Column(name = "keeper_course", nullable = false)
    private Integer keeperCourse;

    @NotNull
    @Column(name = "keeper_command", nullable = false)
    private Integer keeperCommand;

    @NotNull
    @Column(name = "result_type", nullable = false)
    private Integer resultType;

    
    @Lob
    @Column(name = "offense_sequence_text", nullable = false)
    private String offenseSequenceText;

    
    @Lob
    @Column(name = "defense_sequence_text", nullable = false)
    private String defenseSequenceText;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKickerCourse() {
        return kickerCourse;
    }

    public MPenaltyKickCut kickerCourse(Integer kickerCourse) {
        this.kickerCourse = kickerCourse;
        return this;
    }

    public void setKickerCourse(Integer kickerCourse) {
        this.kickerCourse = kickerCourse;
    }

    public Integer getKeeperCourse() {
        return keeperCourse;
    }

    public MPenaltyKickCut keeperCourse(Integer keeperCourse) {
        this.keeperCourse = keeperCourse;
        return this;
    }

    public void setKeeperCourse(Integer keeperCourse) {
        this.keeperCourse = keeperCourse;
    }

    public Integer getKeeperCommand() {
        return keeperCommand;
    }

    public MPenaltyKickCut keeperCommand(Integer keeperCommand) {
        this.keeperCommand = keeperCommand;
        return this;
    }

    public void setKeeperCommand(Integer keeperCommand) {
        this.keeperCommand = keeperCommand;
    }

    public Integer getResultType() {
        return resultType;
    }

    public MPenaltyKickCut resultType(Integer resultType) {
        this.resultType = resultType;
        return this;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getOffenseSequenceText() {
        return offenseSequenceText;
    }

    public MPenaltyKickCut offenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
        return this;
    }

    public void setOffenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
    }

    public String getDefenseSequenceText() {
        return defenseSequenceText;
    }

    public MPenaltyKickCut defenseSequenceText(String defenseSequenceText) {
        this.defenseSequenceText = defenseSequenceText;
        return this;
    }

    public void setDefenseSequenceText(String defenseSequenceText) {
        this.defenseSequenceText = defenseSequenceText;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPenaltyKickCut)) {
            return false;
        }
        return id != null && id.equals(((MPenaltyKickCut) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPenaltyKickCut{" +
            "id=" + getId() +
            ", kickerCourse=" + getKickerCourse() +
            ", keeperCourse=" + getKeeperCourse() +
            ", keeperCommand=" + getKeeperCommand() +
            ", resultType=" + getResultType() +
            ", offenseSequenceText='" + getOffenseSequenceText() + "'" +
            ", defenseSequenceText='" + getDefenseSequenceText() + "'" +
            "}";
    }
}
