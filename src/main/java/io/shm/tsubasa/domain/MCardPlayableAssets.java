package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MCardPlayableAssets.
 */
@Entity
@Table(name = "m_card_playable_assets")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCardPlayableAssets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "cutin_image_asset_name", nullable = false)
    private String cutinImageAssetName;

    @Lob
    @Column(name = "sound_event_suffix")
    private String soundEventSuffix;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCutinImageAssetName() {
        return cutinImageAssetName;
    }

    public MCardPlayableAssets cutinImageAssetName(String cutinImageAssetName) {
        this.cutinImageAssetName = cutinImageAssetName;
        return this;
    }

    public void setCutinImageAssetName(String cutinImageAssetName) {
        this.cutinImageAssetName = cutinImageAssetName;
    }

    public String getSoundEventSuffix() {
        return soundEventSuffix;
    }

    public MCardPlayableAssets soundEventSuffix(String soundEventSuffix) {
        this.soundEventSuffix = soundEventSuffix;
        return this;
    }

    public void setSoundEventSuffix(String soundEventSuffix) {
        this.soundEventSuffix = soundEventSuffix;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCardPlayableAssets)) {
            return false;
        }
        return id != null && id.equals(((MCardPlayableAssets) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCardPlayableAssets{" +
            "id=" + getId() +
            ", cutinImageAssetName='" + getCutinImageAssetName() + "'" +
            ", soundEventSuffix='" + getSoundEventSuffix() + "'" +
            "}";
    }
}
