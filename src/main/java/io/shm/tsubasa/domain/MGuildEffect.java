package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildEffect.
 */
@Entity
@Table(name = "m_guild_effect")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildEffect implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "effect_type", nullable = false)
    private Integer effectType;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "effect_id")
    private Integer effectId;

    
    @Lob
    @Column(name = "thumbnail_path", nullable = false)
    private String thumbnailPath;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffectType() {
        return effectType;
    }

    public MGuildEffect effectType(Integer effectType) {
        this.effectType = effectType;
        return this;
    }

    public void setEffectType(Integer effectType) {
        this.effectType = effectType;
    }

    public String getName() {
        return name;
    }

    public MGuildEffect name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public MGuildEffect effectId(Integer effectId) {
        this.effectId = effectId;
        return this;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public MGuildEffect thumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
        return this;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildEffect)) {
            return false;
        }
        return id != null && id.equals(((MGuildEffect) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildEffect{" +
            "id=" + getId() +
            ", effectType=" + getEffectType() +
            ", name='" + getName() + "'" +
            ", effectId=" + getEffectId() +
            ", thumbnailPath='" + getThumbnailPath() + "'" +
            "}";
    }
}
