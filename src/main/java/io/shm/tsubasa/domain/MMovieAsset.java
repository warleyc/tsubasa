package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MMovieAsset.
 */
@Entity
@Table(name = "m_movie_asset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MMovieAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "lang", nullable = false)
    private Integer lang;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_size", nullable = false)
    private Integer size;

    @NotNull
    @Column(name = "version", nullable = false)
    private Integer version;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private Integer type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLang() {
        return lang;
    }

    public MMovieAsset lang(Integer lang) {
        this.lang = lang;
        return this;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public MMovieAsset name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public MMovieAsset size(Integer size) {
        this.size = size;
        return this;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getVersion() {
        return version;
    }

    public MMovieAsset version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public MMovieAsset type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MMovieAsset)) {
            return false;
        }
        return id != null && id.equals(((MMovieAsset) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MMovieAsset{" +
            "id=" + getId() +
            ", lang=" + getLang() +
            ", name='" + getName() + "'" +
            ", size=" + getSize() +
            ", version=" + getVersion() +
            ", type=" + getType() +
            "}";
    }
}
