package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTargetCharacterGroup.
 */
@Entity
@Table(name = "m_target_character_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTargetCharacterGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private Integer characterId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTargetCharacterGroups")
    private MCharacter id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public MTargetCharacterGroup groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public MTargetCharacterGroup characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public MCharacter getId() {
        return id;
    }

    public MTargetCharacterGroup id(MCharacter mCharacter) {
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
        if (!(o instanceof MTargetCharacterGroup)) {
            return false;
        }
        return id != null && id.equals(((MTargetCharacterGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTargetCharacterGroup{" +
            "id=" + getId() +
            ", groupId=" + getGroupId() +
            ", characterId=" + getCharacterId() +
            "}";
    }
}
