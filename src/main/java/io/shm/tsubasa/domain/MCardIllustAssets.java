package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCardIllustAssets.
 */
@Entity
@Table(name = "m_card_illust_assets")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCardIllustAssets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Lob
    @Column(name = "sub_asset_name")
    private String subAssetName;

    @Lob
    @Column(name = "offset")
    private String offset;

    
    @Lob
    @Column(name = "background_asset_name", nullable = false)
    private String backgroundAssetName;

    @Lob
    @Column(name = "decoration_asset_name")
    private String decorationAssetName;

    @Lob
    @Column(name = "effect_asset_name")
    private String effectAssetName;

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

    public MCardIllustAssets assetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getSubAssetName() {
        return subAssetName;
    }

    public MCardIllustAssets subAssetName(String subAssetName) {
        this.subAssetName = subAssetName;
        return this;
    }

    public void setSubAssetName(String subAssetName) {
        this.subAssetName = subAssetName;
    }

    public String getOffset() {
        return offset;
    }

    public MCardIllustAssets offset(String offset) {
        this.offset = offset;
        return this;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getBackgroundAssetName() {
        return backgroundAssetName;
    }

    public MCardIllustAssets backgroundAssetName(String backgroundAssetName) {
        this.backgroundAssetName = backgroundAssetName;
        return this;
    }

    public void setBackgroundAssetName(String backgroundAssetName) {
        this.backgroundAssetName = backgroundAssetName;
    }

    public String getDecorationAssetName() {
        return decorationAssetName;
    }

    public MCardIllustAssets decorationAssetName(String decorationAssetName) {
        this.decorationAssetName = decorationAssetName;
        return this;
    }

    public void setDecorationAssetName(String decorationAssetName) {
        this.decorationAssetName = decorationAssetName;
    }

    public String getEffectAssetName() {
        return effectAssetName;
    }

    public MCardIllustAssets effectAssetName(String effectAssetName) {
        this.effectAssetName = effectAssetName;
        return this;
    }

    public void setEffectAssetName(String effectAssetName) {
        this.effectAssetName = effectAssetName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCardIllustAssets)) {
            return false;
        }
        return id != null && id.equals(((MCardIllustAssets) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCardIllustAssets{" +
            "id=" + getId() +
            ", assetName='" + getAssetName() + "'" +
            ", subAssetName='" + getSubAssetName() + "'" +
            ", offset='" + getOffset() + "'" +
            ", backgroundAssetName='" + getBackgroundAssetName() + "'" +
            ", decorationAssetName='" + getDecorationAssetName() + "'" +
            ", effectAssetName='" + getEffectAssetName() + "'" +
            "}";
    }
}
