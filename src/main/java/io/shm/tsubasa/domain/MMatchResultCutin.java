package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMatchResultCutin.
 */
@Entity
@Table(name = "m_match_result_cutin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMatchResultCutin implements Serializable {

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
    @Column(name = "is_win", nullable = false)
    private Integer isWin;

    
    @Lob
    @Column(name = "text", nullable = false)
    private String text;

    
    @Lob
    @Column(name = "sound_event", nullable = false)
    private String soundEvent;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mMatchResultCutins")
    private MCharacter id;

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

    public MMatchResultCutin characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public MMatchResultCutin teamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getIsWin() {
        return isWin;
    }

    public MMatchResultCutin isWin(Integer isWin) {
        this.isWin = isWin;
        return this;
    }

    public void setIsWin(Integer isWin) {
        this.isWin = isWin;
    }

    public String getText() {
        return text;
    }

    public MMatchResultCutin text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSoundEvent() {
        return soundEvent;
    }

    public MMatchResultCutin soundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
        return this;
    }

    public void setSoundEvent(String soundEvent) {
        this.soundEvent = soundEvent;
    }

    public MCharacter getId() {
        return id;
    }

    public MMatchResultCutin id(MCharacter mCharacter) {
        this.id = mCharacter;
        return this;
    }

    public void setId(MCharacter mCharacter) {
        this.id = mCharacter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMatchResultCutin)) {
            return false;
        }
        return id != null && id.equals(((MMatchResultCutin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMatchResultCutin{" +
            "id=" + getId() +
            ", characterId=" + getCharacterId() +
            ", teamId=" + getTeamId() +
            ", isWin=" + getIsWin() +
            ", text='" + getText() + "'" +
            ", soundEvent='" + getSoundEvent() + "'" +
            "}";
    }
}
