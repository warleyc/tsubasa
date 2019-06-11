package io.shm.tsubasa.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MCardThumbnailAssets.
 */
@Entity
@Table(name = "m_card_thumbnail_assets")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCardThumbnailAssets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "thumbnail_asset_name", nullable = false)
    private String thumbnailAssetName;

    
    @Lob
    @Column(name = "thumbnail_frame_name", nullable = false)
    private String thumbnailFrameName;

    @Lob
    @Column(name = "thumbnail_backgound_asset_name")
    private String thumbnailBackgoundAssetName;

    @Lob
    @Column(name = "thumbnail_effect_asset_name")
    private String thumbnailEffectAssetName;

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MCardPowerupActionSkill> mCardPowerupActionSkills = new HashSet<>();

    @OneToMany(mappedBy = "id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTrainingCard> mTrainingCards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnailAssetName() {
        return thumbnailAssetName;
    }

    public MCardThumbnailAssets thumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
        return this;
    }

    public void setThumbnailAssetName(String thumbnailAssetName) {
        this.thumbnailAssetName = thumbnailAssetName;
    }

    public String getThumbnailFrameName() {
        return thumbnailFrameName;
    }

    public MCardThumbnailAssets thumbnailFrameName(String thumbnailFrameName) {
        this.thumbnailFrameName = thumbnailFrameName;
        return this;
    }

    public void setThumbnailFrameName(String thumbnailFrameName) {
        this.thumbnailFrameName = thumbnailFrameName;
    }

    public String getThumbnailBackgoundAssetName() {
        return thumbnailBackgoundAssetName;
    }

    public MCardThumbnailAssets thumbnailBackgoundAssetName(String thumbnailBackgoundAssetName) {
        this.thumbnailBackgoundAssetName = thumbnailBackgoundAssetName;
        return this;
    }

    public void setThumbnailBackgoundAssetName(String thumbnailBackgoundAssetName) {
        this.thumbnailBackgoundAssetName = thumbnailBackgoundAssetName;
    }

    public String getThumbnailEffectAssetName() {
        return thumbnailEffectAssetName;
    }

    public MCardThumbnailAssets thumbnailEffectAssetName(String thumbnailEffectAssetName) {
        this.thumbnailEffectAssetName = thumbnailEffectAssetName;
        return this;
    }

    public void setThumbnailEffectAssetName(String thumbnailEffectAssetName) {
        this.thumbnailEffectAssetName = thumbnailEffectAssetName;
    }

    public Set<MCardPowerupActionSkill> getMCardPowerupActionSkills() {
        return mCardPowerupActionSkills;
    }

    public MCardThumbnailAssets mCardPowerupActionSkills(Set<MCardPowerupActionSkill> mCardPowerupActionSkills) {
        this.mCardPowerupActionSkills = mCardPowerupActionSkills;
        return this;
    }

    public MCardThumbnailAssets addMCardPowerupActionSkill(MCardPowerupActionSkill mCardPowerupActionSkill) {
        this.mCardPowerupActionSkills.add(mCardPowerupActionSkill);
        mCardPowerupActionSkill.setId(this);
        return this;
    }

    public MCardThumbnailAssets removeMCardPowerupActionSkill(MCardPowerupActionSkill mCardPowerupActionSkill) {
        this.mCardPowerupActionSkills.remove(mCardPowerupActionSkill);
        mCardPowerupActionSkill.setId(null);
        return this;
    }

    public void setMCardPowerupActionSkills(Set<MCardPowerupActionSkill> mCardPowerupActionSkills) {
        this.mCardPowerupActionSkills = mCardPowerupActionSkills;
    }

    public Set<MTrainingCard> getMTrainingCards() {
        return mTrainingCards;
    }

    public MCardThumbnailAssets mTrainingCards(Set<MTrainingCard> mTrainingCards) {
        this.mTrainingCards = mTrainingCards;
        return this;
    }

    public MCardThumbnailAssets addMTrainingCard(MTrainingCard mTrainingCard) {
        this.mTrainingCards.add(mTrainingCard);
        mTrainingCard.setId(this);
        return this;
    }

    public MCardThumbnailAssets removeMTrainingCard(MTrainingCard mTrainingCard) {
        this.mTrainingCards.remove(mTrainingCard);
        mTrainingCard.setId(null);
        return this;
    }

    public void setMTrainingCards(Set<MTrainingCard> mTrainingCards) {
        this.mTrainingCards = mTrainingCards;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCardThumbnailAssets)) {
            return false;
        }
        return id != null && id.equals(((MCardThumbnailAssets) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCardThumbnailAssets{" +
            "id=" + getId() +
            ", thumbnailAssetName='" + getThumbnailAssetName() + "'" +
            ", thumbnailFrameName='" + getThumbnailFrameName() + "'" +
            ", thumbnailBackgoundAssetName='" + getThumbnailBackgoundAssetName() + "'" +
            ", thumbnailEffectAssetName='" + getThumbnailEffectAssetName() + "'" +
            "}";
    }
}
