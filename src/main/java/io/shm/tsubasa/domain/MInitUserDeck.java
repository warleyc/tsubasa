package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MInitUserDeck.
 */
@Entity
@Table(name = "m_init_user_deck")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MInitUserDeck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "deck_id", nullable = false)
    private Integer deckId;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "formation_id", nullable = false)
    private Integer formationId;

    @Column(name = "captain_card_id")
    private Integer captainCardId;

    @Column(name = "gk_card_id")
    private Integer gkCardId;

    @Column(name = "fp_1_card_id")
    private Integer fp1CardId;

    @Column(name = "fp_2_card_id")
    private Integer fp2CardId;

    @Column(name = "fp_3_card_id")
    private Integer fp3CardId;

    @Column(name = "fp_4_card_id")
    private Integer fp4CardId;

    @Column(name = "fp_5_card_id")
    private Integer fp5CardId;

    @Column(name = "fp_6_card_id")
    private Integer fp6CardId;

    @Column(name = "fp_7_card_id")
    private Integer fp7CardId;

    @Column(name = "fp_8_card_id")
    private Integer fp8CardId;

    @Column(name = "fp_9_card_id")
    private Integer fp9CardId;

    @Column(name = "fp_10_card_id")
    private Integer fp10CardId;

    @Column(name = "sub_1_card_id")
    private Integer sub1CardId;

    @Column(name = "sub_2_card_id")
    private Integer sub2CardId;

    @Column(name = "sub_3_card_id")
    private Integer sub3CardId;

    @Column(name = "sub_4_card_id")
    private Integer sub4CardId;

    @Column(name = "sub_5_card_id")
    private Integer sub5CardId;

    @Column(name = "team_effect_1_card_id")
    private Integer teamEffect1CardId;

    @Column(name = "team_effect_2_card_id")
    private Integer teamEffect2CardId;

    @Column(name = "team_effect_3_card_id")
    private Integer teamEffect3CardId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeckId() {
        return deckId;
    }

    public MInitUserDeck deckId(Integer deckId) {
        this.deckId = deckId;
        return this;
    }

    public void setDeckId(Integer deckId) {
        this.deckId = deckId;
    }

    public String getName() {
        return name;
    }

    public MInitUserDeck name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFormationId() {
        return formationId;
    }

    public MInitUserDeck formationId(Integer formationId) {
        this.formationId = formationId;
        return this;
    }

    public void setFormationId(Integer formationId) {
        this.formationId = formationId;
    }

    public Integer getCaptainCardId() {
        return captainCardId;
    }

    public MInitUserDeck captainCardId(Integer captainCardId) {
        this.captainCardId = captainCardId;
        return this;
    }

    public void setCaptainCardId(Integer captainCardId) {
        this.captainCardId = captainCardId;
    }

    public Integer getGkCardId() {
        return gkCardId;
    }

    public MInitUserDeck gkCardId(Integer gkCardId) {
        this.gkCardId = gkCardId;
        return this;
    }

    public void setGkCardId(Integer gkCardId) {
        this.gkCardId = gkCardId;
    }

    public Integer getFp1CardId() {
        return fp1CardId;
    }

    public MInitUserDeck fp1CardId(Integer fp1CardId) {
        this.fp1CardId = fp1CardId;
        return this;
    }

    public void setFp1CardId(Integer fp1CardId) {
        this.fp1CardId = fp1CardId;
    }

    public Integer getFp2CardId() {
        return fp2CardId;
    }

    public MInitUserDeck fp2CardId(Integer fp2CardId) {
        this.fp2CardId = fp2CardId;
        return this;
    }

    public void setFp2CardId(Integer fp2CardId) {
        this.fp2CardId = fp2CardId;
    }

    public Integer getFp3CardId() {
        return fp3CardId;
    }

    public MInitUserDeck fp3CardId(Integer fp3CardId) {
        this.fp3CardId = fp3CardId;
        return this;
    }

    public void setFp3CardId(Integer fp3CardId) {
        this.fp3CardId = fp3CardId;
    }

    public Integer getFp4CardId() {
        return fp4CardId;
    }

    public MInitUserDeck fp4CardId(Integer fp4CardId) {
        this.fp4CardId = fp4CardId;
        return this;
    }

    public void setFp4CardId(Integer fp4CardId) {
        this.fp4CardId = fp4CardId;
    }

    public Integer getFp5CardId() {
        return fp5CardId;
    }

    public MInitUserDeck fp5CardId(Integer fp5CardId) {
        this.fp5CardId = fp5CardId;
        return this;
    }

    public void setFp5CardId(Integer fp5CardId) {
        this.fp5CardId = fp5CardId;
    }

    public Integer getFp6CardId() {
        return fp6CardId;
    }

    public MInitUserDeck fp6CardId(Integer fp6CardId) {
        this.fp6CardId = fp6CardId;
        return this;
    }

    public void setFp6CardId(Integer fp6CardId) {
        this.fp6CardId = fp6CardId;
    }

    public Integer getFp7CardId() {
        return fp7CardId;
    }

    public MInitUserDeck fp7CardId(Integer fp7CardId) {
        this.fp7CardId = fp7CardId;
        return this;
    }

    public void setFp7CardId(Integer fp7CardId) {
        this.fp7CardId = fp7CardId;
    }

    public Integer getFp8CardId() {
        return fp8CardId;
    }

    public MInitUserDeck fp8CardId(Integer fp8CardId) {
        this.fp8CardId = fp8CardId;
        return this;
    }

    public void setFp8CardId(Integer fp8CardId) {
        this.fp8CardId = fp8CardId;
    }

    public Integer getFp9CardId() {
        return fp9CardId;
    }

    public MInitUserDeck fp9CardId(Integer fp9CardId) {
        this.fp9CardId = fp9CardId;
        return this;
    }

    public void setFp9CardId(Integer fp9CardId) {
        this.fp9CardId = fp9CardId;
    }

    public Integer getFp10CardId() {
        return fp10CardId;
    }

    public MInitUserDeck fp10CardId(Integer fp10CardId) {
        this.fp10CardId = fp10CardId;
        return this;
    }

    public void setFp10CardId(Integer fp10CardId) {
        this.fp10CardId = fp10CardId;
    }

    public Integer getSub1CardId() {
        return sub1CardId;
    }

    public MInitUserDeck sub1CardId(Integer sub1CardId) {
        this.sub1CardId = sub1CardId;
        return this;
    }

    public void setSub1CardId(Integer sub1CardId) {
        this.sub1CardId = sub1CardId;
    }

    public Integer getSub2CardId() {
        return sub2CardId;
    }

    public MInitUserDeck sub2CardId(Integer sub2CardId) {
        this.sub2CardId = sub2CardId;
        return this;
    }

    public void setSub2CardId(Integer sub2CardId) {
        this.sub2CardId = sub2CardId;
    }

    public Integer getSub3CardId() {
        return sub3CardId;
    }

    public MInitUserDeck sub3CardId(Integer sub3CardId) {
        this.sub3CardId = sub3CardId;
        return this;
    }

    public void setSub3CardId(Integer sub3CardId) {
        this.sub3CardId = sub3CardId;
    }

    public Integer getSub4CardId() {
        return sub4CardId;
    }

    public MInitUserDeck sub4CardId(Integer sub4CardId) {
        this.sub4CardId = sub4CardId;
        return this;
    }

    public void setSub4CardId(Integer sub4CardId) {
        this.sub4CardId = sub4CardId;
    }

    public Integer getSub5CardId() {
        return sub5CardId;
    }

    public MInitUserDeck sub5CardId(Integer sub5CardId) {
        this.sub5CardId = sub5CardId;
        return this;
    }

    public void setSub5CardId(Integer sub5CardId) {
        this.sub5CardId = sub5CardId;
    }

    public Integer getTeamEffect1CardId() {
        return teamEffect1CardId;
    }

    public MInitUserDeck teamEffect1CardId(Integer teamEffect1CardId) {
        this.teamEffect1CardId = teamEffect1CardId;
        return this;
    }

    public void setTeamEffect1CardId(Integer teamEffect1CardId) {
        this.teamEffect1CardId = teamEffect1CardId;
    }

    public Integer getTeamEffect2CardId() {
        return teamEffect2CardId;
    }

    public MInitUserDeck teamEffect2CardId(Integer teamEffect2CardId) {
        this.teamEffect2CardId = teamEffect2CardId;
        return this;
    }

    public void setTeamEffect2CardId(Integer teamEffect2CardId) {
        this.teamEffect2CardId = teamEffect2CardId;
    }

    public Integer getTeamEffect3CardId() {
        return teamEffect3CardId;
    }

    public MInitUserDeck teamEffect3CardId(Integer teamEffect3CardId) {
        this.teamEffect3CardId = teamEffect3CardId;
        return this;
    }

    public void setTeamEffect3CardId(Integer teamEffect3CardId) {
        this.teamEffect3CardId = teamEffect3CardId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MInitUserDeck)) {
            return false;
        }
        return id != null && id.equals(((MInitUserDeck) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MInitUserDeck{" +
            "id=" + getId() +
            ", deckId=" + getDeckId() +
            ", name='" + getName() + "'" +
            ", formationId=" + getFormationId() +
            ", captainCardId=" + getCaptainCardId() +
            ", gkCardId=" + getGkCardId() +
            ", fp1CardId=" + getFp1CardId() +
            ", fp2CardId=" + getFp2CardId() +
            ", fp3CardId=" + getFp3CardId() +
            ", fp4CardId=" + getFp4CardId() +
            ", fp5CardId=" + getFp5CardId() +
            ", fp6CardId=" + getFp6CardId() +
            ", fp7CardId=" + getFp7CardId() +
            ", fp8CardId=" + getFp8CardId() +
            ", fp9CardId=" + getFp9CardId() +
            ", fp10CardId=" + getFp10CardId() +
            ", sub1CardId=" + getSub1CardId() +
            ", sub2CardId=" + getSub2CardId() +
            ", sub3CardId=" + getSub3CardId() +
            ", sub4CardId=" + getSub4CardId() +
            ", sub5CardId=" + getSub5CardId() +
            ", teamEffect1CardId=" + getTeamEffect1CardId() +
            ", teamEffect2CardId=" + getTeamEffect2CardId() +
            ", teamEffect3CardId=" + getTeamEffect3CardId() +
            "}";
    }
}
