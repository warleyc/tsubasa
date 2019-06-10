package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MExtensionSale.
 */
@Entity
@Table(name = "m_extension_sale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MExtensionSale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "menu_message")
    private String menuMessage;

    @NotNull
    @Column(name = "start_at", nullable = false)
    private Integer startAt;

    @NotNull
    @Column(name = "end_at", nullable = false)
    private Integer endAt;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private Integer type;

    @NotNull
    @Column(name = "add_extension", nullable = false)
    private Integer addExtension;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuMessage() {
        return menuMessage;
    }

    public MExtensionSale menuMessage(String menuMessage) {
        this.menuMessage = menuMessage;
        return this;
    }

    public void setMenuMessage(String menuMessage) {
        this.menuMessage = menuMessage;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MExtensionSale startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getEndAt() {
        return endAt;
    }

    public MExtensionSale endAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Integer getType() {
        return type;
    }

    public MExtensionSale type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAddExtension() {
        return addExtension;
    }

    public MExtensionSale addExtension(Integer addExtension) {
        this.addExtension = addExtension;
        return this;
    }

    public void setAddExtension(Integer addExtension) {
        this.addExtension = addExtension;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MExtensionSale)) {
            return false;
        }
        return id != null && id.equals(((MExtensionSale) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MExtensionSale{" +
            "id=" + getId() +
            ", menuMessage='" + getMenuMessage() + "'" +
            ", startAt=" + getStartAt() +
            ", endAt=" + getEndAt() +
            ", type=" + getType() +
            ", addExtension=" + getAddExtension() +
            "}";
    }
}
