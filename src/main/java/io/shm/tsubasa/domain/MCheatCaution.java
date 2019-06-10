package io.shm.tsubasa.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MCheatCaution.
 */
@Entity
@Table(name = "m_cheat_caution")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCheatCaution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "caution", nullable = false)
    private Integer caution;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    
    @Lob
    @Column(name = "image_asset_name", nullable = false)
    private String imageAssetName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCaution() {
        return caution;
    }

    public MCheatCaution caution(Integer caution) {
        this.caution = caution;
        return this;
    }

    public void setCaution(Integer caution) {
        this.caution = caution;
    }

    public String getDescription() {
        return description;
    }

    public MCheatCaution description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageAssetName() {
        return imageAssetName;
    }

    public MCheatCaution imageAssetName(String imageAssetName) {
        this.imageAssetName = imageAssetName;
        return this;
    }

    public void setImageAssetName(String imageAssetName) {
        this.imageAssetName = imageAssetName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCheatCaution)) {
            return false;
        }
        return id != null && id.equals(((MCheatCaution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCheatCaution{" +
            "id=" + getId() +
            ", caution=" + getCaution() +
            ", description='" + getDescription() + "'" +
            ", imageAssetName='" + getImageAssetName() + "'" +
            "}";
    }
}
