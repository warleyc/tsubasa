package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MEncountersCut.
 */
@Entity
@Table(name = "m_encounters_cut")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MEncountersCut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_condition", nullable = false)
    private Integer condition;

    @NotNull
    @Column(name = "ball_float_condition", nullable = false)
    private Integer ballFloatCondition;

    @NotNull
    @Column(name = "command_1_type", nullable = false)
    private Integer command1Type;

    @NotNull
    @Column(name = "command_1_is_skill", nullable = false)
    private Integer command1IsSkill;

    @NotNull
    @Column(name = "command_2_type", nullable = false)
    private Integer command2Type;

    @NotNull
    @Column(name = "command_2_is_skill", nullable = false)
    private Integer command2IsSkill;

    @NotNull
    @Column(name = "result", nullable = false)
    private Integer result;

    @NotNull
    @Column(name = "shoot_result", nullable = false)
    private Integer shootResult;

    @NotNull
    @Column(name = "is_thrown", nullable = false)
    private Integer isThrown;

    
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

    public Integer getCondition() {
        return condition;
    }

    public MEncountersCut condition(Integer condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getBallFloatCondition() {
        return ballFloatCondition;
    }

    public MEncountersCut ballFloatCondition(Integer ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
        return this;
    }

    public void setBallFloatCondition(Integer ballFloatCondition) {
        this.ballFloatCondition = ballFloatCondition;
    }

    public Integer getCommand1Type() {
        return command1Type;
    }

    public MEncountersCut command1Type(Integer command1Type) {
        this.command1Type = command1Type;
        return this;
    }

    public void setCommand1Type(Integer command1Type) {
        this.command1Type = command1Type;
    }

    public Integer getCommand1IsSkill() {
        return command1IsSkill;
    }

    public MEncountersCut command1IsSkill(Integer command1IsSkill) {
        this.command1IsSkill = command1IsSkill;
        return this;
    }

    public void setCommand1IsSkill(Integer command1IsSkill) {
        this.command1IsSkill = command1IsSkill;
    }

    public Integer getCommand2Type() {
        return command2Type;
    }

    public MEncountersCut command2Type(Integer command2Type) {
        this.command2Type = command2Type;
        return this;
    }

    public void setCommand2Type(Integer command2Type) {
        this.command2Type = command2Type;
    }

    public Integer getCommand2IsSkill() {
        return command2IsSkill;
    }

    public MEncountersCut command2IsSkill(Integer command2IsSkill) {
        this.command2IsSkill = command2IsSkill;
        return this;
    }

    public void setCommand2IsSkill(Integer command2IsSkill) {
        this.command2IsSkill = command2IsSkill;
    }

    public Integer getResult() {
        return result;
    }

    public MEncountersCut result(Integer result) {
        this.result = result;
        return this;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getShootResult() {
        return shootResult;
    }

    public MEncountersCut shootResult(Integer shootResult) {
        this.shootResult = shootResult;
        return this;
    }

    public void setShootResult(Integer shootResult) {
        this.shootResult = shootResult;
    }

    public Integer getIsThrown() {
        return isThrown;
    }

    public MEncountersCut isThrown(Integer isThrown) {
        this.isThrown = isThrown;
        return this;
    }

    public void setIsThrown(Integer isThrown) {
        this.isThrown = isThrown;
    }

    public String getOffenseSequenceText() {
        return offenseSequenceText;
    }

    public MEncountersCut offenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
        return this;
    }

    public void setOffenseSequenceText(String offenseSequenceText) {
        this.offenseSequenceText = offenseSequenceText;
    }

    public String getDefenseSequenceText() {
        return defenseSequenceText;
    }

    public MEncountersCut defenseSequenceText(String defenseSequenceText) {
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
        if (!(o instanceof MEncountersCut)) {
            return false;
        }
        return id != null && id.equals(((MEncountersCut) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MEncountersCut{" +
            "id=" + getId() +
            ", condition=" + getCondition() +
            ", ballFloatCondition=" + getBallFloatCondition() +
            ", command1Type=" + getCommand1Type() +
            ", command1IsSkill=" + getCommand1IsSkill() +
            ", command2Type=" + getCommand2Type() +
            ", command2IsSkill=" + getCommand2IsSkill() +
            ", result=" + getResult() +
            ", shootResult=" + getShootResult() +
            ", isThrown=" + getIsThrown() +
            ", offenseSequenceText='" + getOffenseSequenceText() + "'" +
            ", defenseSequenceText='" + getDefenseSequenceText() + "'" +
            "}";
    }
}
