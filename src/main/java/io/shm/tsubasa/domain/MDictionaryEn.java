package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MDictionaryEn.
 */
@Entity
@Table(name = "m_dictionary_en")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDictionaryEn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "jhi_key", nullable = false)
    private String key;

    
    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public MDictionaryEn key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public MDictionaryEn message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDictionaryEn)) {
            return false;
        }
        return id != null && id.equals(((MDictionaryEn) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDictionaryEn{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
