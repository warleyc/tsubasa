package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MSellCardMedal.
 */
@Entity
@Table(name = "m_sell_card_medal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MSellCardMedal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "medal_id", nullable = false)
    private Integer medalId;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedalId() {
        return medalId;
    }

    public MSellCardMedal medalId(Integer medalId) {
        this.medalId = medalId;
        return this;
    }

    public void setMedalId(Integer medalId) {
        this.medalId = medalId;
    }

    public Integer getAmount() {
        return amount;
    }

    public MSellCardMedal amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MSellCardMedal)) {
            return false;
        }
        return id != null && id.equals(((MSellCardMedal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MSellCardMedal{" +
            "id=" + getId() +
            ", medalId=" + getMedalId() +
            ", amount=" + getAmount() +
            "}";
    }
}
