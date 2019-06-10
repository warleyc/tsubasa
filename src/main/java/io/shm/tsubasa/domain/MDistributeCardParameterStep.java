package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MDistributeCardParameterStep.
 */
@Entity
@Table(name = "m_distribute_card_parameter_step")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MDistributeCardParameterStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "is_gk", nullable = false)
    private Integer isGk;

    @NotNull
    @Column(name = "step", nullable = false)
    private Integer step;

    @NotNull
    @Column(name = "param", nullable = false)
    private Integer param;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsGk() {
        return isGk;
    }

    public MDistributeCardParameterStep isGk(Integer isGk) {
        this.isGk = isGk;
        return this;
    }

    public void setIsGk(Integer isGk) {
        this.isGk = isGk;
    }

    public Integer getStep() {
        return step;
    }

    public MDistributeCardParameterStep step(Integer step) {
        this.step = step;
        return this;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getParam() {
        return param;
    }

    public MDistributeCardParameterStep param(Integer param) {
        this.param = param;
        return this;
    }

    public void setParam(Integer param) {
        this.param = param;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MDistributeCardParameterStep)) {
            return false;
        }
        return id != null && id.equals(((MDistributeCardParameterStep) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MDistributeCardParameterStep{" +
            "id=" + getId() +
            ", isGk=" + getIsGk() +
            ", step=" + getStep() +
            ", param=" + getParam() +
            "}";
    }
}
