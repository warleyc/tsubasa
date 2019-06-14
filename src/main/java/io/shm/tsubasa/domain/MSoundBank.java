package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MSoundBank.
 */
@Entity
@Table(name = "m_sound_bank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MSoundBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "path", nullable = false)
    private String path;

    
    @Lob
    @Column(name = "pf", nullable = false)
    private String pf;

    @NotNull
    @Column(name = "version", nullable = false)
    private Integer version;

    @NotNull
    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public MSoundBank path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPf() {
        return pf;
    }

    public MSoundBank pf(String pf) {
        this.pf = pf;
        return this;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public Integer getVersion() {
        return version;
    }

    public MSoundBank version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public MSoundBank fileSize(Integer fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MSoundBank)) {
            return false;
        }
        return id != null && id.equals(((MSoundBank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MSoundBank{" +
            "id=" + getId() +
            ", path='" + getPath() + "'" +
            ", pf='" + getPf() + "'" +
            ", version=" + getVersion() +
            ", fileSize=" + getFileSize() +
            "}";
    }
}
