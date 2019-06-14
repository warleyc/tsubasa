package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MTrainingCard} entity.
 */
public class MTrainingCardDTO implements Serializable {

    private Long id;

    
    @Lob
    private String name;

    
    @Lob
    private String shortName;

    
    @Lob
    private String description;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer attribute;

    @NotNull
    private Integer gainExp;

    @NotNull
    private Integer coin;

    private Integer sellMedalId;

    @NotNull
    private Integer plusDribble;

    @NotNull
    private Integer plusShoot;

    @NotNull
    private Integer plusPass;

    @NotNull
    private Integer plusTackle;

    @NotNull
    private Integer plusBlock;

    @NotNull
    private Integer plusIntercept;

    @NotNull
    private Integer plusSpeed;

    @NotNull
    private Integer plusPower;

    @NotNull
    private Integer plusTechnique;

    @NotNull
    private Integer plusPunching;

    @NotNull
    private Integer plusCatching;

    @NotNull
    private Integer thumbnailAssetsId;

    @NotNull
    private Integer cardIllustAssetsId;


    private Long mcardthumbnailassetsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getGainExp() {
        return gainExp;
    }

    public void setGainExp(Integer gainExp) {
        this.gainExp = gainExp;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getSellMedalId() {
        return sellMedalId;
    }

    public void setSellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public Integer getPlusDribble() {
        return plusDribble;
    }

    public void setPlusDribble(Integer plusDribble) {
        this.plusDribble = plusDribble;
    }

    public Integer getPlusShoot() {
        return plusShoot;
    }

    public void setPlusShoot(Integer plusShoot) {
        this.plusShoot = plusShoot;
    }

    public Integer getPlusPass() {
        return plusPass;
    }

    public void setPlusPass(Integer plusPass) {
        this.plusPass = plusPass;
    }

    public Integer getPlusTackle() {
        return plusTackle;
    }

    public void setPlusTackle(Integer plusTackle) {
        this.plusTackle = plusTackle;
    }

    public Integer getPlusBlock() {
        return plusBlock;
    }

    public void setPlusBlock(Integer plusBlock) {
        this.plusBlock = plusBlock;
    }

    public Integer getPlusIntercept() {
        return plusIntercept;
    }

    public void setPlusIntercept(Integer plusIntercept) {
        this.plusIntercept = plusIntercept;
    }

    public Integer getPlusSpeed() {
        return plusSpeed;
    }

    public void setPlusSpeed(Integer plusSpeed) {
        this.plusSpeed = plusSpeed;
    }

    public Integer getPlusPower() {
        return plusPower;
    }

    public void setPlusPower(Integer plusPower) {
        this.plusPower = plusPower;
    }

    public Integer getPlusTechnique() {
        return plusTechnique;
    }

    public void setPlusTechnique(Integer plusTechnique) {
        this.plusTechnique = plusTechnique;
    }

    public Integer getPlusPunching() {
        return plusPunching;
    }

    public void setPlusPunching(Integer plusPunching) {
        this.plusPunching = plusPunching;
    }

    public Integer getPlusCatching() {
        return plusCatching;
    }

    public void setPlusCatching(Integer plusCatching) {
        this.plusCatching = plusCatching;
    }

    public Integer getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public void setThumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public Integer getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public void setCardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public Long getMcardthumbnailassetsId() {
        return mcardthumbnailassetsId;
    }

    public void setMcardthumbnailassetsId(Long mCardThumbnailAssetsId) {
        this.mcardthumbnailassetsId = mCardThumbnailAssetsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MTrainingCardDTO mTrainingCardDTO = (MTrainingCardDTO) o;
        if (mTrainingCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mTrainingCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MTrainingCardDTO{" +
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
            ", mcardthumbnailassets=" + getMcardthumbnailassetsId() +
            "}";
    }
}
