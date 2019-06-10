package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MContentableCard.
 */
@Entity
@Table(name = "m_contentable_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MContentableCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "playable_card_id", nullable = false)
    private Integer playableCardId;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "action_main_level", nullable = false)
    private Integer actionMainLevel;

    @NotNull
    @Column(name = "action_sub_1_level", nullable = false)
    private Integer actionSub1Level;

    @NotNull
    @Column(name = "action_sub_2_level", nullable = false)
    private Integer actionSub2Level;

    @NotNull
    @Column(name = "action_sub_3_level", nullable = false)
    private Integer actionSub3Level;

    @NotNull
    @Column(name = "action_sub_4_level", nullable = false)
    private Integer actionSub4Level;

    @NotNull
    @Column(name = "plus_rate", nullable = false)
    private Integer plusRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayableCardId() {
        return playableCardId;
    }

    public MContentableCard playableCardId(Integer playableCardId) {
        this.playableCardId = playableCardId;
        return this;
    }

    public void setPlayableCardId(Integer playableCardId) {
        this.playableCardId = playableCardId;
    }

    public Integer getLevel() {
        return level;
    }

    public MContentableCard level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getActionMainLevel() {
        return actionMainLevel;
    }

    public MContentableCard actionMainLevel(Integer actionMainLevel) {
        this.actionMainLevel = actionMainLevel;
        return this;
    }

    public void setActionMainLevel(Integer actionMainLevel) {
        this.actionMainLevel = actionMainLevel;
    }

    public Integer getActionSub1Level() {
        return actionSub1Level;
    }

    public MContentableCard actionSub1Level(Integer actionSub1Level) {
        this.actionSub1Level = actionSub1Level;
        return this;
    }

    public void setActionSub1Level(Integer actionSub1Level) {
        this.actionSub1Level = actionSub1Level;
    }

    public Integer getActionSub2Level() {
        return actionSub2Level;
    }

    public MContentableCard actionSub2Level(Integer actionSub2Level) {
        this.actionSub2Level = actionSub2Level;
        return this;
    }

    public void setActionSub2Level(Integer actionSub2Level) {
        this.actionSub2Level = actionSub2Level;
    }

    public Integer getActionSub3Level() {
        return actionSub3Level;
    }

    public MContentableCard actionSub3Level(Integer actionSub3Level) {
        this.actionSub3Level = actionSub3Level;
        return this;
    }

    public void setActionSub3Level(Integer actionSub3Level) {
        this.actionSub3Level = actionSub3Level;
    }

    public Integer getActionSub4Level() {
        return actionSub4Level;
    }

    public MContentableCard actionSub4Level(Integer actionSub4Level) {
        this.actionSub4Level = actionSub4Level;
        return this;
    }

    public void setActionSub4Level(Integer actionSub4Level) {
        this.actionSub4Level = actionSub4Level;
    }

    public Integer getPlusRate() {
        return plusRate;
    }

    public MContentableCard plusRate(Integer plusRate) {
        this.plusRate = plusRate;
        return this;
    }

    public void setPlusRate(Integer plusRate) {
        this.plusRate = plusRate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MContentableCard)) {
            return false;
        }
        return id != null && id.equals(((MContentableCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MContentableCard{" +
            "id=" + getId() +
            ", playableCardId=" + getPlayableCardId() +
            ", level=" + getLevel() +
            ", actionMainLevel=" + getActionMainLevel() +
            ", actionSub1Level=" + getActionSub1Level() +
            ", actionSub2Level=" + getActionSub2Level() +
            ", actionSub3Level=" + getActionSub3Level() +
            ", actionSub4Level=" + getActionSub4Level() +
            ", plusRate=" + getPlusRate() +
            "}";
    }
}
