package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MCombinationCutPosition.
 */
@Entity
@Table(name = "m_combination_cut_position")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCombinationCutPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "action_cut_id", nullable = false)
    private Integer actionCutId;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private Integer characterId;

    @NotNull
    @Column(name = "activator_position", nullable = false)
    private Integer activatorPosition;

    @NotNull
    @Column(name = "participant_position_1", nullable = false)
    private Integer participantPosition1;

    @NotNull
    @Column(name = "participant_position_2", nullable = false)
    private Integer participantPosition2;

    @NotNull
    @Column(name = "participant_position_3", nullable = false)
    private Integer participantPosition3;

    @NotNull
    @Column(name = "participant_position_4", nullable = false)
    private Integer participantPosition4;

    @NotNull
    @Column(name = "participant_position_5", nullable = false)
    private Integer participantPosition5;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mCombinationCutPositions")
    private MCharacter mcharacter;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActionCutId() {
        return actionCutId;
    }

    public MCombinationCutPosition actionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
        return this;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public MCombinationCutPosition characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getActivatorPosition() {
        return activatorPosition;
    }

    public MCombinationCutPosition activatorPosition(Integer activatorPosition) {
        this.activatorPosition = activatorPosition;
        return this;
    }

    public void setActivatorPosition(Integer activatorPosition) {
        this.activatorPosition = activatorPosition;
    }

    public Integer getParticipantPosition1() {
        return participantPosition1;
    }

    public MCombinationCutPosition participantPosition1(Integer participantPosition1) {
        this.participantPosition1 = participantPosition1;
        return this;
    }

    public void setParticipantPosition1(Integer participantPosition1) {
        this.participantPosition1 = participantPosition1;
    }

    public Integer getParticipantPosition2() {
        return participantPosition2;
    }

    public MCombinationCutPosition participantPosition2(Integer participantPosition2) {
        this.participantPosition2 = participantPosition2;
        return this;
    }

    public void setParticipantPosition2(Integer participantPosition2) {
        this.participantPosition2 = participantPosition2;
    }

    public Integer getParticipantPosition3() {
        return participantPosition3;
    }

    public MCombinationCutPosition participantPosition3(Integer participantPosition3) {
        this.participantPosition3 = participantPosition3;
        return this;
    }

    public void setParticipantPosition3(Integer participantPosition3) {
        this.participantPosition3 = participantPosition3;
    }

    public Integer getParticipantPosition4() {
        return participantPosition4;
    }

    public MCombinationCutPosition participantPosition4(Integer participantPosition4) {
        this.participantPosition4 = participantPosition4;
        return this;
    }

    public void setParticipantPosition4(Integer participantPosition4) {
        this.participantPosition4 = participantPosition4;
    }

    public Integer getParticipantPosition5() {
        return participantPosition5;
    }

    public MCombinationCutPosition participantPosition5(Integer participantPosition5) {
        this.participantPosition5 = participantPosition5;
        return this;
    }

    public void setParticipantPosition5(Integer participantPosition5) {
        this.participantPosition5 = participantPosition5;
    }

    public MCharacter getMcharacter() {
        return mcharacter;
    }

    public MCombinationCutPosition mcharacter(MCharacter mCharacter) {
        this.mcharacter = mCharacter;
        return this;
    }

    public void setMcharacter(MCharacter mCharacter) {
        this.mcharacter = mCharacter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCombinationCutPosition)) {
            return false;
        }
        return id != null && id.equals(((MCombinationCutPosition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCombinationCutPosition{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", characterId=" + getCharacterId() +
            ", activatorPosition=" + getActivatorPosition() +
            ", participantPosition1=" + getParticipantPosition1() +
            ", participantPosition2=" + getParticipantPosition2() +
            ", participantPosition3=" + getParticipantPosition3() +
            ", participantPosition4=" + getParticipantPosition4() +
            ", participantPosition5=" + getParticipantPosition5() +
            "}";
    }
}
