package io.shm.tsubasa.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.shm.tsubasa.domain.MTrainingCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MTrainingCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-training-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MTrainingCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter attribute;

    private IntegerFilter gainExp;

    private IntegerFilter coin;

    private IntegerFilter sellMedalId;

    private IntegerFilter plusDribble;

    private IntegerFilter plusShoot;

    private IntegerFilter plusPass;

    private IntegerFilter plusTackle;

    private IntegerFilter plusBlock;

    private IntegerFilter plusIntercept;

    private IntegerFilter plusSpeed;

    private IntegerFilter plusPower;

    private IntegerFilter plusTechnique;

    private IntegerFilter plusPunching;

    private IntegerFilter plusCatching;

    private IntegerFilter thumbnailAssetsId;

    private IntegerFilter cardIllustAssetsId;

    private LongFilter mcardthumbnailassetsId;

    public MTrainingCardCriteria(){
    }

    public MTrainingCardCriteria(MTrainingCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.attribute = other.attribute == null ? null : other.attribute.copy();
        this.gainExp = other.gainExp == null ? null : other.gainExp.copy();
        this.coin = other.coin == null ? null : other.coin.copy();
        this.sellMedalId = other.sellMedalId == null ? null : other.sellMedalId.copy();
        this.plusDribble = other.plusDribble == null ? null : other.plusDribble.copy();
        this.plusShoot = other.plusShoot == null ? null : other.plusShoot.copy();
        this.plusPass = other.plusPass == null ? null : other.plusPass.copy();
        this.plusTackle = other.plusTackle == null ? null : other.plusTackle.copy();
        this.plusBlock = other.plusBlock == null ? null : other.plusBlock.copy();
        this.plusIntercept = other.plusIntercept == null ? null : other.plusIntercept.copy();
        this.plusSpeed = other.plusSpeed == null ? null : other.plusSpeed.copy();
        this.plusPower = other.plusPower == null ? null : other.plusPower.copy();
        this.plusTechnique = other.plusTechnique == null ? null : other.plusTechnique.copy();
        this.plusPunching = other.plusPunching == null ? null : other.plusPunching.copy();
        this.plusCatching = other.plusCatching == null ? null : other.plusCatching.copy();
        this.thumbnailAssetsId = other.thumbnailAssetsId == null ? null : other.thumbnailAssetsId.copy();
        this.cardIllustAssetsId = other.cardIllustAssetsId == null ? null : other.cardIllustAssetsId.copy();
        this.mcardthumbnailassetsId = other.mcardthumbnailassetsId == null ? null : other.mcardthumbnailassetsId.copy();
    }

    @Override
    public MTrainingCardCriteria copy() {
        return new MTrainingCardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getRarity() {
        return rarity;
    }

    public void setRarity(IntegerFilter rarity) {
        this.rarity = rarity;
    }

    public IntegerFilter getAttribute() {
        return attribute;
    }

    public void setAttribute(IntegerFilter attribute) {
        this.attribute = attribute;
    }

    public IntegerFilter getGainExp() {
        return gainExp;
    }

    public void setGainExp(IntegerFilter gainExp) {
        this.gainExp = gainExp;
    }

    public IntegerFilter getCoin() {
        return coin;
    }

    public void setCoin(IntegerFilter coin) {
        this.coin = coin;
    }

    public IntegerFilter getSellMedalId() {
        return sellMedalId;
    }

    public void setSellMedalId(IntegerFilter sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public IntegerFilter getPlusDribble() {
        return plusDribble;
    }

    public void setPlusDribble(IntegerFilter plusDribble) {
        this.plusDribble = plusDribble;
    }

    public IntegerFilter getPlusShoot() {
        return plusShoot;
    }

    public void setPlusShoot(IntegerFilter plusShoot) {
        this.plusShoot = plusShoot;
    }

    public IntegerFilter getPlusPass() {
        return plusPass;
    }

    public void setPlusPass(IntegerFilter plusPass) {
        this.plusPass = plusPass;
    }

    public IntegerFilter getPlusTackle() {
        return plusTackle;
    }

    public void setPlusTackle(IntegerFilter plusTackle) {
        this.plusTackle = plusTackle;
    }

    public IntegerFilter getPlusBlock() {
        return plusBlock;
    }

    public void setPlusBlock(IntegerFilter plusBlock) {
        this.plusBlock = plusBlock;
    }

    public IntegerFilter getPlusIntercept() {
        return plusIntercept;
    }

    public void setPlusIntercept(IntegerFilter plusIntercept) {
        this.plusIntercept = plusIntercept;
    }

    public IntegerFilter getPlusSpeed() {
        return plusSpeed;
    }

    public void setPlusSpeed(IntegerFilter plusSpeed) {
        this.plusSpeed = plusSpeed;
    }

    public IntegerFilter getPlusPower() {
        return plusPower;
    }

    public void setPlusPower(IntegerFilter plusPower) {
        this.plusPower = plusPower;
    }

    public IntegerFilter getPlusTechnique() {
        return plusTechnique;
    }

    public void setPlusTechnique(IntegerFilter plusTechnique) {
        this.plusTechnique = plusTechnique;
    }

    public IntegerFilter getPlusPunching() {
        return plusPunching;
    }

    public void setPlusPunching(IntegerFilter plusPunching) {
        this.plusPunching = plusPunching;
    }

    public IntegerFilter getPlusCatching() {
        return plusCatching;
    }

    public void setPlusCatching(IntegerFilter plusCatching) {
        this.plusCatching = plusCatching;
    }

    public IntegerFilter getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public void setThumbnailAssetsId(IntegerFilter thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public IntegerFilter getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public void setCardIllustAssetsId(IntegerFilter cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public LongFilter getMcardthumbnailassetsId() {
        return mcardthumbnailassetsId;
    }

    public void setMcardthumbnailassetsId(LongFilter mcardthumbnailassetsId) {
        this.mcardthumbnailassetsId = mcardthumbnailassetsId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MTrainingCardCriteria that = (MTrainingCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(attribute, that.attribute) &&
            Objects.equals(gainExp, that.gainExp) &&
            Objects.equals(coin, that.coin) &&
            Objects.equals(sellMedalId, that.sellMedalId) &&
            Objects.equals(plusDribble, that.plusDribble) &&
            Objects.equals(plusShoot, that.plusShoot) &&
            Objects.equals(plusPass, that.plusPass) &&
            Objects.equals(plusTackle, that.plusTackle) &&
            Objects.equals(plusBlock, that.plusBlock) &&
            Objects.equals(plusIntercept, that.plusIntercept) &&
            Objects.equals(plusSpeed, that.plusSpeed) &&
            Objects.equals(plusPower, that.plusPower) &&
            Objects.equals(plusTechnique, that.plusTechnique) &&
            Objects.equals(plusPunching, that.plusPunching) &&
            Objects.equals(plusCatching, that.plusCatching) &&
            Objects.equals(thumbnailAssetsId, that.thumbnailAssetsId) &&
            Objects.equals(cardIllustAssetsId, that.cardIllustAssetsId) &&
            Objects.equals(mcardthumbnailassetsId, that.mcardthumbnailassetsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        attribute,
        gainExp,
        coin,
        sellMedalId,
        plusDribble,
        plusShoot,
        plusPass,
        plusTackle,
        plusBlock,
        plusIntercept,
        plusSpeed,
        plusPower,
        plusTechnique,
        plusPunching,
        plusCatching,
        thumbnailAssetsId,
        cardIllustAssetsId,
        mcardthumbnailassetsId
        );
    }

    @Override
    public String toString() {
        return "MTrainingCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (attribute != null ? "attribute=" + attribute + ", " : "") +
                (gainExp != null ? "gainExp=" + gainExp + ", " : "") +
                (coin != null ? "coin=" + coin + ", " : "") +
                (sellMedalId != null ? "sellMedalId=" + sellMedalId + ", " : "") +
                (plusDribble != null ? "plusDribble=" + plusDribble + ", " : "") +
                (plusShoot != null ? "plusShoot=" + plusShoot + ", " : "") +
                (plusPass != null ? "plusPass=" + plusPass + ", " : "") +
                (plusTackle != null ? "plusTackle=" + plusTackle + ", " : "") +
                (plusBlock != null ? "plusBlock=" + plusBlock + ", " : "") +
                (plusIntercept != null ? "plusIntercept=" + plusIntercept + ", " : "") +
                (plusSpeed != null ? "plusSpeed=" + plusSpeed + ", " : "") +
                (plusPower != null ? "plusPower=" + plusPower + ", " : "") +
                (plusTechnique != null ? "plusTechnique=" + plusTechnique + ", " : "") +
                (plusPunching != null ? "plusPunching=" + plusPunching + ", " : "") +
                (plusCatching != null ? "plusCatching=" + plusCatching + ", " : "") +
                (thumbnailAssetsId != null ? "thumbnailAssetsId=" + thumbnailAssetsId + ", " : "") +
                (cardIllustAssetsId != null ? "cardIllustAssetsId=" + cardIllustAssetsId + ", " : "") +
                (mcardthumbnailassetsId != null ? "mcardthumbnailassetsId=" + mcardthumbnailassetsId + ", " : "") +
            "}";
    }

}
