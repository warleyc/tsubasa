package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCharacterScoreCut.
 */
@Entity
@Table(name = "m_character_score_cut")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCharacterScoreCut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private Integer characterId;

    @NotNull
    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    @NotNull
    @Column(name = "score_cut_type", nullable = false)
    private Integer scoreCutType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public MCharacterScoreCut characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public MCharacterScoreCut teamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getScoreCutType() {
        return scoreCutType;
    }

    public MCharacterScoreCut scoreCutType(Integer scoreCutType) {
        this.scoreCutType = scoreCutType;
        return this;
    }

    public void setScoreCutType(Integer scoreCutType) {
        this.scoreCutType = scoreCutType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCharacterScoreCut)) {
            return false;
        }
        return id != null && id.equals(((MCharacterScoreCut) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCharacterScoreCut{" +
            "id=" + getId() +
            ", characterId=" + getCharacterId() +
            ", teamId=" + getTeamId() +
            ", scoreCutType=" + getScoreCutType() +
            "}";
    }
}
