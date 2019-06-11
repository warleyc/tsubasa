package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MDbMapping.
 */
@Entity
@Table(name = "m_db_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDbMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "file_name", nullable = false)
    private String fileName;

    
    @Lob
    @Column(name = "db_name", nullable = false)
    private String dbName;

    
    @Lob
    @Column(name = "path", nullable = false)
    private String path;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public MDbMapping fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDbName() {
        return dbName;
    }

    public MDbMapping dbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getPath() {
        return path;
    }

    public MDbMapping path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDbMapping)) {
            return false;
        }
        return id != null && id.equals(((MDbMapping) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDbMapping{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", dbName='" + getDbName() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
