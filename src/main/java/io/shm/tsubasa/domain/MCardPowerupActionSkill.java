package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MCardPowerupActionSkill.
 */
@Entity
@Table(name = "m_card_powerup_action_skill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MCardPowerupActionSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    @NotNull
    @Column(name = "attribute", nullable = false)
    private Integer attribute;

    @NotNull
    @Column(name = "action_rarity", nullable = false)
    private Integer actionRarity;

    @NotNull
    @Column(name = "gain_action_exp", nullable = false)
    private Integer gainActionExp;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @Column(name = "sell_medal_id")
    private Integer sellMedalId;

    @NotNull
    @Column(name = "thumbnail_assets_id", nullable = false)
    private Integer thumbnailAssetsId;

    @NotNull
    @Column(name = "card_illust_assets_id", nullable = false)
    private Integer cardIllustAssetsId;

    @Column(name = "target_action_command_type")
    private Integer targetActionCommandType;

    @Column(name = "target_character_id")
    private Integer targetCharacterId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mCardPowerupActionSkills")
    private MCardThumbnailAssets id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MCardPowerupActionSkill name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public MCardPowerupActionSkill shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public MCardPowerupActionSkill description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MCardPowerupActionSkill rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public MCardPowerupActionSkill attribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getActionRarity() {
        return actionRarity;
    }

    public MCardPowerupActionSkill actionRarity(Integer actionRarity) {
        this.actionRarity = actionRarity;
        return this;
    }

    public void setActionRarity(Integer actionRarity) {
        this.actionRarity = actionRarity;
    }

    public Integer getGainActionExp() {
        return gainActionExp;
    }

    public MCardPowerupActionSkill gainActionExp(Integer gainActionExp) {
        this.gainActionExp = gainActionExp;
        return this;
    }

    public void setGainActionExp(Integer gainActionExp) {
        this.gainActionExp = gainActionExp;
    }

    public Integer getCoin() {
        return coin;
    }

    public MCardPowerupActionSkill coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getSellMedalId() {
        return sellMedalId;
    }

    public MCardPowerupActionSkill sellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
        return this;
    }

    public void setSellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public Integer getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public MCardPowerupActionSkill thumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
        return this;
    }

    public void setThumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public Integer getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public MCardPowerupActionSkill cardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
        return this;
    }

    public void setCardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public Integer getTargetActionCommandType() {
        return targetActionCommandType;
    }

    public MCardPowerupActionSkill targetActionCommandType(Integer targetActionCommandType) {
        this.targetActionCommandType = targetActionCommandType;
        return this;
    }

    public void setTargetActionCommandType(Integer targetActionCommandType) {
        this.targetActionCommandType = targetActionCommandType;
    }

    public Integer getTargetCharacterId() {
        return targetCharacterId;
    }

    public MCardPowerupActionSkill targetCharacterId(Integer targetCharacterId) {
        this.targetCharacterId = targetCharacterId;
        return this;
    }

    public void setTargetCharacterId(Integer targetCharacterId) {
        this.targetCharacterId = targetCharacterId;
    }

    public MCardThumbnailAssets getId() {
        return id;
    }

    public MCardPowerupActionSkill id(MCardThumbnailAssets mCardThumbnailAssets) {
        this.id = mCardThumbnailAssets;
        return this;
    }

    public void setId(MCardThumbnailAssets mCardThumbnailAssets) {
        this.id = mCardThumbnailAssets;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MCardPowerupActionSkill)) {
            return false;
        }
        return id != null && id.equals(((MCardPowerupActionSkill) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MCardPowerupActionSkill{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", description='" + getDescription() + "'" +
            ", rarity=" + getRarity() +
            ", attribute=" + getAttribute() +
            ", actionRarity=" + getActionRarity() +
            ", gainActionExp=" + getGainActionExp() +
            ", coin=" + getCoin() +
            ", sellMedalId=" + getSellMedalId() +
            ", thumbnailAssetsId=" + getThumbnailAssetsId() +
            ", cardIllustAssetsId=" + getCardIllustAssetsId() +
            ", targetActionCommandType=" + getTargetActionCommandType() +
            ", targetCharacterId=" + getTargetCharacterId() +
            "}";
    }
}
