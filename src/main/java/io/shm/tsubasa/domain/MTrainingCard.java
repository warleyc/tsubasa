package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MTrainingCard.
 */
@Entity
@Table(name = "m_training_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MTrainingCard implements Serializable {

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
    @Column(name = "gain_exp", nullable = false)
    private Integer gainExp;

    @NotNull
    @Column(name = "coin", nullable = false)
    private Integer coin;

    @Column(name = "sell_medal_id")
    private Integer sellMedalId;

    @NotNull
    @Column(name = "plus_dribble", nullable = false)
    private Integer plusDribble;

    @NotNull
    @Column(name = "plus_shoot", nullable = false)
    private Integer plusShoot;

    @NotNull
    @Column(name = "plus_pass", nullable = false)
    private Integer plusPass;

    @NotNull
    @Column(name = "plus_tackle", nullable = false)
    private Integer plusTackle;

    @NotNull
    @Column(name = "plus_block", nullable = false)
    private Integer plusBlock;

    @NotNull
    @Column(name = "plus_intercept", nullable = false)
    private Integer plusIntercept;

    @NotNull
    @Column(name = "plus_speed", nullable = false)
    private Integer plusSpeed;

    @NotNull
    @Column(name = "plus_power", nullable = false)
    private Integer plusPower;

    @NotNull
    @Column(name = "plus_technique", nullable = false)
    private Integer plusTechnique;

    @NotNull
    @Column(name = "plus_punching", nullable = false)
    private Integer plusPunching;

    @NotNull
    @Column(name = "plus_catching", nullable = false)
    private Integer plusCatching;

    @NotNull
    @Column(name = "thumbnail_assets_id", nullable = false)
    private Integer thumbnailAssetsId;

    @NotNull
    @Column(name = "card_illust_assets_id", nullable = false)
    private Integer cardIllustAssetsId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mTrainingCards")
    private MCardThumbnailAssets mcardthumbnailassets;

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

    public MTrainingCard name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public MTrainingCard shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public MTrainingCard description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MTrainingCard rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public MTrainingCard attribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getGainExp() {
        return gainExp;
    }

    public MTrainingCard gainExp(Integer gainExp) {
        this.gainExp = gainExp;
        return this;
    }

    public void setGainExp(Integer gainExp) {
        this.gainExp = gainExp;
    }

    public Integer getCoin() {
        return coin;
    }

    public MTrainingCard coin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getSellMedalId() {
        return sellMedalId;
    }

    public MTrainingCard sellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
        return this;
    }

    public void setSellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public Integer getPlusDribble() {
        return plusDribble;
    }

    public MTrainingCard plusDribble(Integer plusDribble) {
        this.plusDribble = plusDribble;
        return this;
    }

    public void setPlusDribble(Integer plusDribble) {
        this.plusDribble = plusDribble;
    }

    public Integer getPlusShoot() {
        return plusShoot;
    }

    public MTrainingCard plusShoot(Integer plusShoot) {
        this.plusShoot = plusShoot;
        return this;
    }

    public void setPlusShoot(Integer plusShoot) {
        this.plusShoot = plusShoot;
    }

    public Integer getPlusPass() {
        return plusPass;
    }

    public MTrainingCard plusPass(Integer plusPass) {
        this.plusPass = plusPass;
        return this;
    }

    public void setPlusPass(Integer plusPass) {
        this.plusPass = plusPass;
    }

    public Integer getPlusTackle() {
        return plusTackle;
    }

    public MTrainingCard plusTackle(Integer plusTackle) {
        this.plusTackle = plusTackle;
        return this;
    }

    public void setPlusTackle(Integer plusTackle) {
        this.plusTackle = plusTackle;
    }

    public Integer getPlusBlock() {
        return plusBlock;
    }

    public MTrainingCard plusBlock(Integer plusBlock) {
        this.plusBlock = plusBlock;
        return this;
    }

    public void setPlusBlock(Integer plusBlock) {
        this.plusBlock = plusBlock;
    }

    public Integer getPlusIntercept() {
        return plusIntercept;
    }

    public MTrainingCard plusIntercept(Integer plusIntercept) {
        this.plusIntercept = plusIntercept;
        return this;
    }

    public void setPlusIntercept(Integer plusIntercept) {
        this.plusIntercept = plusIntercept;
    }

    public Integer getPlusSpeed() {
        return plusSpeed;
    }

    public MTrainingCard plusSpeed(Integer plusSpeed) {
        this.plusSpeed = plusSpeed;
        return this;
    }

    public void setPlusSpeed(Integer plusSpeed) {
        this.plusSpeed = plusSpeed;
    }

    public Integer getPlusPower() {
        return plusPower;
    }

    public MTrainingCard plusPower(Integer plusPower) {
        this.plusPower = plusPower;
        return this;
    }

    public void setPlusPower(Integer plusPower) {
        this.plusPower = plusPower;
    }

    public Integer getPlusTechnique() {
        return plusTechnique;
    }

    public MTrainingCard plusTechnique(Integer plusTechnique) {
        this.plusTechnique = plusTechnique;
        return this;
    }

    public void setPlusTechnique(Integer plusTechnique) {
        this.plusTechnique = plusTechnique;
    }

    public Integer getPlusPunching() {
        return plusPunching;
    }

    public MTrainingCard plusPunching(Integer plusPunching) {
        this.plusPunching = plusPunching;
        return this;
    }

    public void setPlusPunching(Integer plusPunching) {
        this.plusPunching = plusPunching;
    }

    public Integer getPlusCatching() {
        return plusCatching;
    }

    public MTrainingCard plusCatching(Integer plusCatching) {
        this.plusCatching = plusCatching;
        return this;
    }

    public void setPlusCatching(Integer plusCatching) {
        this.plusCatching = plusCatching;
    }

    public Integer getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public MTrainingCard thumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
        return this;
    }

    public void setThumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public Integer getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public MTrainingCard cardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
        return this;
    }

    public void setCardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public MCardThumbnailAssets getMcardthumbnailassets() {
        return mcardthumbnailassets;
    }

    public MTrainingCard mcardthumbnailassets(MCardThumbnailAssets mCardThumbnailAssets) {
        this.mcardthumbnailassets = mCardThumbnailAssets;
        return this;
    }

    public void setMcardthumbnailassets(MCardThumbnailAssets mCardThumbnailAssets) {
        this.mcardthumbnailassets = mCardThumbnailAssets;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MTrainingCard)) {
            return false;
        }
        return id != null && id.equals(((MTrainingCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MTrainingCard{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", shortName='" + getShortName() + "'" +
            ", description='" + getDescription() + "'" +
            ", rarity=" + getRarity() +
            ", attribute=" + getAttribute() +
            ", gainExp=" + getGainExp() +
            ", coin=" + getCoin() +
            ", sellMedalId=" + getSellMedalId() +
            ", plusDribble=" + getPlusDribble() +
            ", plusShoot=" + getPlusShoot() +
            ", plusPass=" + getPlusPass() +
            ", plusTackle=" + getPlusTackle() +
            ", plusBlock=" + getPlusBlock() +
            ", plusIntercept=" + getPlusIntercept() +
            ", plusSpeed=" + getPlusSpeed() +
            ", plusPower=" + getPlusPower() +
            ", plusTechnique=" + getPlusTechnique() +
            ", plusPunching=" + getPlusPunching() +
            ", plusCatching=" + getPlusCatching() +
            ", thumbnailAssetsId=" + getThumbnailAssetsId() +
            ", cardIllustAssetsId=" + getCardIllustAssetsId() +
            "}";
    }
}
