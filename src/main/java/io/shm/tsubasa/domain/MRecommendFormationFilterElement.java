package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MRecommendFormationFilterElement.
 */
@Entity
@Table(name = "m_recommend_formation_filter_element")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRecommendFormationFilterElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "filter_type", nullable = false)
    private Integer filterType;

    @NotNull
    @Column(name = "element_id", nullable = false)
    private Integer elementId;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public MRecommendFormationFilterElement filterType(Integer filterType) {
        this.filterType = filterType;
        return this;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public Integer getElementId() {
        return elementId;
    }

    public MRecommendFormationFilterElement elementId(Integer elementId) {
        this.elementId = elementId;
        return this;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getName() {
        return name;
    }

    public MRecommendFormationFilterElement name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MRecommendFormationFilterElement)) {
            return false;
        }
        return id != null && id.equals(((MRecommendFormationFilterElement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MRecommendFormationFilterElement{" +
            "id=" + getId() +
            ", filterType=" + getFilterType() +
            ", elementId=" + getElementId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
