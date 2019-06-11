package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MAssetTagMapping.
 */
@Entity
@Table(name = "m_asset_tag_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MAssetTagMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tag", nullable = false)
    private Integer tag;

    
    @Lob
    @Column(name = "ids", nullable = false)
    private String ids;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public MAssetTagMapping tag(Integer tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getIds() {
        return ids;
    }

    public MAssetTagMapping ids(String ids) {
        this.ids = ids;
        return this;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MAssetTagMapping)) {
            return false;
        }
        return id != null && id.equals(((MAssetTagMapping) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MAssetTagMapping{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            ", ids='" + getIds() + "'" +
            "}";
    }
}
