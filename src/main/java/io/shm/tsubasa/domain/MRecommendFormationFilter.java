package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MRecommendFormationFilter.
 */
@Entity
@Table(name = "m_recommend_formation_filter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRecommendFormationFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "filter_type", nullable = false)
    private Integer filterType;

    
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

    public MRecommendFormationFilter filterType(Integer filterType) {
        this.filterType = filterType;
        return this;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public String getName() {
        return name;
    }

    public MRecommendFormationFilter name(String name) {
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
        if (!(o instanceof MRecommendFormationFilter)) {
            return false;
        }
        return id != null && id.equals(((MRecommendFormationFilter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MRecommendFormationFilter{" +
            "id=" + getId() +
            ", filterType=" + getFilterType() +
            ", name='" + getName() + "'" +
            "}";
    }
}
