package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MChatSystemMessage.
 */
@Entity
@Table(name = "m_chat_system_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MChatSystemMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "message_type", nullable = false)
    private Integer messageType;

    
    @Lob
    @Column(name = "message_key", nullable = false)
    private String messageKey;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public MChatSystemMessage messageType(Integer messageType) {
        this.messageType = messageType;
        return this;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public MChatSystemMessage messageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MChatSystemMessage)) {
            return false;
        }
        return id != null && id.equals(((MChatSystemMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MChatSystemMessage{" +
            "id=" + getId() +
            ", messageType=" + getMessageType() +
            ", messageKey='" + getMessageKey() + "'" +
            "}";
    }
}
