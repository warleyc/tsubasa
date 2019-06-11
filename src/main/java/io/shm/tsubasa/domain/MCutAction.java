package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCutAction.
 */
@Entity
@Table(name = "m_cut_action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCutAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "action_cut_id", nullable = false)
    private Integer actionCutId;

    @NotNull
    @Column(name = "cut_action_type", nullable = false)
    private Integer cutActionType;

    
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

    public MCutAction actionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
        return this;
    }

    public void setActionCutId(Integer actionCutId) {
        this.actionCutId = actionCutId;
    }

    public Integer getCutActionType() {
        return cutActionType;
    }

    public MCutAction cutActionType(Integer cutActionType) {
        this.cutActionType = cutActionType;
        return this;
    }

    public void setCutActionType(Integer cutActionType) {
        this.cutActionType = cutActionType;
    }

    public String getCutSequenceText() {
        return cutSequenceText;
    }

    public MCutAction cutSequenceText(String cutSequenceText) {
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
        if (!(o instanceof MCutAction)) {
            return false;
        }
        return id != null && id.equals(((MCutAction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCutAction{" +
            "id=" + getId() +
            ", actionCutId=" + getActionCutId() +
            ", cutActionType=" + getCutActionType() +
            ", cutSequenceText='" + getCutSequenceText() + "'" +
            "}";
    }
}
