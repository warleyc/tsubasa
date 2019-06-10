package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MCutSeqGroup.
 */
@Entity
@Table(name = "m_cut_seq_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCutSeqGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "jhi_key", nullable = false)
    private String key;

    
    @Lob
    @Column(name = "param", nullable = false)
    private String param;

    
    @Lob
    @Column(name = "cut_sequence_text", nullable = false)
    private String cutSequenceText;

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

    public MCutSeqGroup key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParam() {
        return param;
    }

    public MCutSeqGroup param(String param) {
        this.param = param;
        return this;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCutSequenceText() {
        return cutSequenceText;
    }

    public MCutSeqGroup cutSequenceText(String cutSequenceText) {
        this.cutSequenceText = cutSequenceText;
        return this;
    }

    public void setCutSequenceText(String cutSequenceText) {
        this.cutSequenceText = cutSequenceText;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCutSeqGroup)) {
            return false;
        }
        return id != null && id.equals(((MCutSeqGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCutSeqGroup{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", param='" + getParam() + "'" +
            ", cutSequenceText='" + getCutSequenceText() + "'" +
            "}";
    }
}
