package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MGuildChatDefaultStamp.
 */
@Entity
@Table(name = "m_guild_chat_default_stamp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MGuildChatDefaultStamp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "master_id", nullable = false)
    private Integer masterId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public MGuildChatDefaultStamp masterId(Integer masterId) {
        this.masterId = masterId;
        return this;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MGuildChatDefaultStamp)) {
            return false;
        }
        return id != null && id.equals(((MGuildChatDefaultStamp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MGuildChatDefaultStamp{" +
            "id=" + getId() +
            ", masterId=" + getMasterId() +
            "}";
    }
}
