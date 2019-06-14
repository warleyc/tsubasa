package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MLoginBonusSerif.
 */
@Entity
@Table(name = "m_login_bonus_serif")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MLoginBonusSerif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "serif_id", nullable = false)
    private Integer serifId;

    @Lob
    @Column(name = "serif_1")
    private String serif1;

    @Lob
    @Column(name = "serif_2")
    private String serif2;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSerifId() {
        return serifId;
    }

    public MLoginBonusSerif serifId(Integer serifId) {
        this.serifId = serifId;
        return this;
    }

    public void setSerifId(Integer serifId) {
        this.serifId = serifId;
    }

    public String getSerif1() {
        return serif1;
    }

    public MLoginBonusSerif serif1(String serif1) {
        this.serif1 = serif1;
        return this;
    }

    public void setSerif1(String serif1) {
        this.serif1 = serif1;
    }

    public String getSerif2() {
        return serif2;
    }

    public MLoginBonusSerif serif2(String serif2) {
        this.serif2 = serif2;
        return this;
    }

    public void setSerif2(String serif2) {
        this.serif2 = serif2;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MLoginBonusSerif)) {
            return false;
        }
        return id != null && id.equals(((MLoginBonusSerif) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MLoginBonusSerif{" +
            "id=" + getId() +
            ", serifId=" + getSerifId() +
            ", serif1='" + getSerif1() + "'" +
            ", serif2='" + getSerif2() + "'" +
            "}";
    }
}
