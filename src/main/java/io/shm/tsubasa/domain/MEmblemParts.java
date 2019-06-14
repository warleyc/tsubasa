package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MEmblemParts.
 */
@Entity
@Table(name = "m_emblem_parts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MEmblemParts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @NotNull
    @Column(name = "parts_type", nullable = false)
    private Integer partsType;

    @OneToMany(mappedBy = "memblemparts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MDummyEmblem> mDummyEmblems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public MEmblemParts assetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getPartsType() {
        return partsType;
    }

    public MEmblemParts partsType(Integer partsType) {
        this.partsType = partsType;
        return this;
    }

    public void setPartsType(Integer partsType) {
        this.partsType = partsType;
    }

    public Set<MDummyEmblem> getMDummyEmblems() {
        return mDummyEmblems;
    }

    public MEmblemParts mDummyEmblems(Set<MDummyEmblem> mDummyEmblems) {
        this.mDummyEmblems = mDummyEmblems;
        return this;
    }

    public MEmblemParts addMDummyEmblem(MDummyEmblem mDummyEmblem) {
        this.mDummyEmblems.add(mDummyEmblem);
        mDummyEmblem.setMemblemparts(this);
        return this;
    }

    public MEmblemParts removeMDummyEmblem(MDummyEmblem mDummyEmblem) {
        this.mDummyEmblems.remove(mDummyEmblem);
        mDummyEmblem.setMemblemparts(null);
        return this;
    }

    public void setMDummyEmblems(Set<MDummyEmblem> mDummyEmblems) {
        this.mDummyEmblems = mDummyEmblems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MEmblemParts)) {
            return false;
        }
        return id != null && id.equals(((MEmblemParts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MEmblemParts{" +
            "id=" + getId() +
            ", assetName='" + getAssetName() + "'" +
            ", partsType=" + getPartsType() +
            "}";
    }
}
