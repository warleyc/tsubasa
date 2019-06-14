package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MModelUniformUpResource.
 */
@Entity
@Table(name = "m_model_uniform_up_resource")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MModelUniformUpResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uniform_type_id", nullable = false)
    private Integer uniformTypeId;

    @NotNull
    @Column(name = "dressing_type", nullable = false)
    private Integer dressingType;

    @NotNull
    @Column(name = "width", nullable = false)
    private Integer width;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniformTypeId() {
        return uniformTypeId;
    }

    public MModelUniformUpResource uniformTypeId(Integer uniformTypeId) {
        this.uniformTypeId = uniformTypeId;
        return this;
    }

    public void setUniformTypeId(Integer uniformTypeId) {
        this.uniformTypeId = uniformTypeId;
    }

    public Integer getDressingType() {
        return dressingType;
    }

    public MModelUniformUpResource dressingType(Integer dressingType) {
        this.dressingType = dressingType;
        return this;
    }

    public void setDressingType(Integer dressingType) {
        this.dressingType = dressingType;
    }

    public Integer getWidth() {
        return width;
    }

    public MModelUniformUpResource width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MModelUniformUpResource)) {
            return false;
        }
        return id != null && id.equals(((MModelUniformUpResource) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MModelUniformUpResource{" +
            "id=" + getId() +
            ", uniformTypeId=" + getUniformTypeId() +
            ", dressingType=" + getDressingType() +
            ", width=" + getWidth() +
            "}";
    }
}
