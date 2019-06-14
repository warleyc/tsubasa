package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MNpcDeck.
 */
@Entity
@Table(name = "m_npc_deck")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MNpcDeck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "team_name", nullable = false)
    private String teamName;

    @NotNull
    @Column(name = "uniform_bottom_fp", nullable = false)
    private Integer uniformBottomFp;

    @NotNull
    @Column(name = "uniform_up_fp", nullable = false)
    private Integer uniformUpFp;

    @NotNull
    @Column(name = "uniform_bottom_gk", nullable = false)
    private Integer uniformBottomGk;

    @NotNull
    @Column(name = "uniform_up_gk", nullable = false)
    private Integer uniformUpGk;

    @NotNull
    @Column(name = "formation_id", nullable = false)
    private Integer formationId;

    @NotNull
    @Column(name = "captain_card_id", nullable = false)
    private Integer captainCardId;

    @NotNull
    @Column(name = "team_effect_1_card_id", nullable = false)
    private Integer teamEffect1CardId;

    @NotNull
    @Column(name = "team_effect_2_card_id", nullable = false)
    private Integer teamEffect2CardId;

    @NotNull
    @Column(name = "team_effect_3_card_id", nullable = false)
    private Integer teamEffect3CardId;

    @NotNull
    @Column(name = "npc_card_id_1", nullable = false)
    private Integer npcCardId1;

    @NotNull
    @Column(name = "npc_card_id_2", nullable = false)
    private Integer npcCardId2;

    @NotNull
    @Column(name = "npc_card_id_3", nullable = false)
    private Integer npcCardId3;

    @NotNull
    @Column(name = "npc_card_id_4", nullable = false)
    private Integer npcCardId4;

    @NotNull
    @Column(name = "npc_card_id_5", nullable = false)
    private Integer npcCardId5;

    @NotNull
    @Column(name = "npc_card_id_6", nullable = false)
    private Integer npcCardId6;

    @NotNull
    @Column(name = "npc_card_id_7", nullable = false)
    private Integer npcCardId7;

    @NotNull
    @Column(name = "npc_card_id_8", nullable = false)
    private Integer npcCardId8;

    @NotNull
    @Column(name = "npc_card_id_9", nullable = false)
    private Integer npcCardId9;

    @NotNull
    @Column(name = "npc_card_id_10", nullable = false)
    private Integer npcCardId10;

    @NotNull
    @Column(name = "npc_card_id_11", nullable = false)
    private Integer npcCardId11;

    @NotNull
    @Column(name = "tick", nullable = false)
    private Integer tick;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mNpcDecks")
    private MFormation mformation;

    @OneToMany(mappedBy = "mnpcdeck")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MDummyOpponent> mDummyOpponents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public MNpcDeck teamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getUniformBottomFp() {
        return uniformBottomFp;
    }

    public MNpcDeck uniformBottomFp(Integer uniformBottomFp) {
        this.uniformBottomFp = uniformBottomFp;
        return this;
    }

    public void setUniformBottomFp(Integer uniformBottomFp) {
        this.uniformBottomFp = uniformBottomFp;
    }

    public Integer getUniformUpFp() {
        return uniformUpFp;
    }

    public MNpcDeck uniformUpFp(Integer uniformUpFp) {
        this.uniformUpFp = uniformUpFp;
        return this;
    }

    public void setUniformUpFp(Integer uniformUpFp) {
        this.uniformUpFp = uniformUpFp;
    }

    public Integer getUniformBottomGk() {
        return uniformBottomGk;
    }

    public MNpcDeck uniformBottomGk(Integer uniformBottomGk) {
        this.uniformBottomGk = uniformBottomGk;
        return this;
    }

    public void setUniformBottomGk(Integer uniformBottomGk) {
        this.uniformBottomGk = uniformBottomGk;
    }

    public Integer getUniformUpGk() {
        return uniformUpGk;
    }

    public MNpcDeck uniformUpGk(Integer uniformUpGk) {
        this.uniformUpGk = uniformUpGk;
        return this;
    }

    public void setUniformUpGk(Integer uniformUpGk) {
        this.uniformUpGk = uniformUpGk;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public MNpcDeck formationId(Integer formationId) {
        this.formationId = formationId;
        return this;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public Integer getCaptainCardId() {
        return captainCardId;
    }

    public MNpcDeck captainCardId(Integer captainCardId) {
        this.captainCardId = captainCardId;
        return this;
    }

    public void setCaptainCardId(Integer captainCardId) {
        this.captainCardId = captainCardId;
    }

    public Integer getTeamEffect1CardId() {
        return teamEffect1CardId;
    }

    public MNpcDeck teamEffect1CardId(Integer teamEffect1CardId) {
        this.teamEffect1CardId = teamEffect1CardId;
        return this;
    }

    public void setTeamEffect1CardId(Integer teamEffect1CardId) {
        this.teamEffect1CardId = teamEffect1CardId;
    }

    public Integer getTeamEffect2CardId() {
        return teamEffect2CardId;
    }

    public MNpcDeck teamEffect2CardId(Integer teamEffect2CardId) {
        this.teamEffect2CardId = teamEffect2CardId;
        return this;
    }

    public void setTeamEffect2CardId(Integer teamEffect2CardId) {
        this.teamEffect2CardId = teamEffect2CardId;
    }

    public Integer getTeamEffect3CardId() {
        return teamEffect3CardId;
    }

    public MNpcDeck teamEffect3CardId(Integer teamEffect3CardId) {
        this.teamEffect3CardId = teamEffect3CardId;
        return this;
    }

    public void setTeamEffect3CardId(Integer teamEffect3CardId) {
        this.teamEffect3CardId = teamEffect3CardId;
    }

    public Integer getNpcCardId1() {
        return npcCardId1;
    }

    public MNpcDeck npcCardId1(Integer npcCardId1) {
        this.npcCardId1 = npcCardId1;
        return this;
    }

    public void setNpcCardId1(Integer npcCardId1) {
        this.npcCardId1 = npcCardId1;
    }

    public Integer getNpcCardId2() {
        return npcCardId2;
    }

    public MNpcDeck npcCardId2(Integer npcCardId2) {
        this.npcCardId2 = npcCardId2;
        return this;
    }

    public void setNpcCardId2(Integer npcCardId2) {
        this.npcCardId2 = npcCardId2;
    }

    public Integer getNpcCardId3() {
        return npcCardId3;
    }

    public MNpcDeck npcCardId3(Integer npcCardId3) {
        this.npcCardId3 = npcCardId3;
        return this;
    }

    public void setNpcCardId3(Integer npcCardId3) {
        this.npcCardId3 = npcCardId3;
    }

    public Integer getNpcCardId4() {
        return npcCardId4;
    }

    public MNpcDeck npcCardId4(Integer npcCardId4) {
        this.npcCardId4 = npcCardId4;
        return this;
    }

    public void setNpcCardId4(Integer npcCardId4) {
        this.npcCardId4 = npcCardId4;
    }

    public Integer getNpcCardId5() {
        return npcCardId5;
    }

    public MNpcDeck npcCardId5(Integer npcCardId5) {
        this.npcCardId5 = npcCardId5;
        return this;
    }

    public void setNpcCardId5(Integer npcCardId5) {
        this.npcCardId5 = npcCardId5;
    }

    public Integer getNpcCardId6() {
        return npcCardId6;
    }

    public MNpcDeck npcCardId6(Integer npcCardId6) {
        this.npcCardId6 = npcCardId6;
        return this;
    }

    public void setNpcCardId6(Integer npcCardId6) {
        this.npcCardId6 = npcCardId6;
    }

    public Integer getNpcCardId7() {
        return npcCardId7;
    }

    public MNpcDeck npcCardId7(Integer npcCardId7) {
        this.npcCardId7 = npcCardId7;
        return this;
    }

    public void setNpcCardId7(Integer npcCardId7) {
        this.npcCardId7 = npcCardId7;
    }

    public Integer getNpcCardId8() {
        return npcCardId8;
    }

    public MNpcDeck npcCardId8(Integer npcCardId8) {
        this.npcCardId8 = npcCardId8;
        return this;
    }

    public void setNpcCardId8(Integer npcCardId8) {
        this.npcCardId8 = npcCardId8;
    }

    public Integer getNpcCardId9() {
        return npcCardId9;
    }

    public MNpcDeck npcCardId9(Integer npcCardId9) {
        this.npcCardId9 = npcCardId9;
        return this;
    }

    public void setNpcCardId9(Integer npcCardId9) {
        this.npcCardId9 = npcCardId9;
    }

    public Integer getNpcCardId10() {
        return npcCardId10;
    }

    public MNpcDeck npcCardId10(Integer npcCardId10) {
        this.npcCardId10 = npcCardId10;
        return this;
    }

    public void setNpcCardId10(Integer npcCardId10) {
        this.npcCardId10 = npcCardId10;
    }

    public Integer getNpcCardId11() {
        return npcCardId11;
    }

    public MNpcDeck npcCardId11(Integer npcCardId11) {
        this.npcCardId11 = npcCardId11;
        return this;
    }

    public void setNpcCardId11(Integer npcCardId11) {
        this.npcCardId11 = npcCardId11;
    }

    public Integer getTick() {
        return tick;
    }

    public MNpcDeck tick(Integer tick) {
        this.tick = tick;
        return this;
    }

    public void setTick(Integer tick) {
        this.tick = tick;
    }

    public MFormation getMformation() {
        return mformation;
    }

    public MNpcDeck mformation(MFormation mFormation) {
        this.mformation = mFormation;
        return this;
    }

    public void setMformation(MFormation mFormation) {
        this.mformation = mFormation;
    }

    public Set<MDummyOpponent> getMDummyOpponents() {
        return mDummyOpponents;
    }

    public MNpcDeck mDummyOpponents(Set<MDummyOpponent> mDummyOpponents) {
        this.mDummyOpponents = mDummyOpponents;
        return this;
    }

    public MNpcDeck addMDummyOpponent(MDummyOpponent mDummyOpponent) {
        this.mDummyOpponents.add(mDummyOpponent);
        mDummyOpponent.setMnpcdeck(this);
        return this;
    }

    public MNpcDeck removeMDummyOpponent(MDummyOpponent mDummyOpponent) {
        this.mDummyOpponents.remove(mDummyOpponent);
        mDummyOpponent.setMnpcdeck(null);
        return this;
    }

    public void setMDummyOpponents(Set<MDummyOpponent> mDummyOpponents) {
        this.mDummyOpponents = mDummyOpponents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MNpcDeck)) {
            return false;
        }
        return id != null && id.equals(((MNpcDeck) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MNpcDeck{" +
            "id=" + getId() +
            ", teamName='" + getTeamName() + "'" +
            ", uniformBottomFp=" + getUniformBottomFp() +
            ", uniformUpFp=" + getUniformUpFp() +
            ", uniformBottomGk=" + getUniformBottomGk() +
            ", uniformUpGk=" + getUniformUpGk() +
            ", formationId=" + getFormationId() +
            ", captainCardId=" + getCaptainCardId() +
            ", teamEffect1CardId=" + getTeamEffect1CardId() +
            ", teamEffect2CardId=" + getTeamEffect2CardId() +
            ", teamEffect3CardId=" + getTeamEffect3CardId() +
            ", npcCardId1=" + getNpcCardId1() +
            ", npcCardId2=" + getNpcCardId2() +
            ", npcCardId3=" + getNpcCardId3() +
            ", npcCardId4=" + getNpcCardId4() +
            ", npcCardId5=" + getNpcCardId5() +
            ", npcCardId6=" + getNpcCardId6() +
            ", npcCardId7=" + getNpcCardId7() +
            ", npcCardId8=" + getNpcCardId8() +
            ", npcCardId9=" + getNpcCardId9() +
            ", npcCardId10=" + getNpcCardId10() +
            ", npcCardId11=" + getNpcCardId11() +
            ", tick=" + getTick() +
            "}";
    }
}
