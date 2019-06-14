package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MKeeperCutAction.
 */
@Entity
@Table(name = "m_keeper_cut_action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MKeeperCutAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "action_cut_id", nullable = false)
    private Integer actionCutId;

    @NotNull
    @Column(name = "keeper_cut_action_type", nullable = false)
    private Integer keeperCutActionType;

    
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

    public Integer getActionCutId() {
        return actionCutId;
    }

    public MKeeperCutAction actionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
        return this;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getKeeperCutActionType() {
        return keeperCutActionType;
    }

    public MKeeperCutAction keeperCutActionType(Integer keeperCutActionType) {
        this.keeperCutActionType = keeperCutActionType;
        return this;
    }

    public void setKeeperCutActionType(Integer keeperCutActionType) {
        this.keeperCutActionType = keeperCutActionType;
    }

    public String getCutSequenceText() {
        return cutSequenceText;
    }

    public MKeeperCutAction cutSequenceText(String cutSequenceText) {
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
        if (!(o instanceof MKeeperCutAction)) {
            return false;
        }
        return id != null && id.equals(((MKeeperCutAction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MKeeperCutAction{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", keeperCutActionType=" + getKeeperCutActionType() +
            ", cutSequenceText='" + getCutSequenceText() + "'" +
            "}";
    }
}
