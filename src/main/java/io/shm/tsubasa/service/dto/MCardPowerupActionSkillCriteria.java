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
 * Criteria class for the {@link io.shm.tsubasa.domain.MCardPowerupActionSkill} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MCardPowerupActionSkillResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-card-powerup-action-skills?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MCardPowerupActionSkillCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter rarity;

    private IntegerFilter attribute;

    private IntegerFilter actionRarity;

    private IntegerFilter gainActionExp;

    private IntegerFilter coin;

    private IntegerFilter sellMedalId;

    private IntegerFilter thumbnailAssetsId;

    private IntegerFilter cardIllustAssetsId;

    private IntegerFilter targetActionCommandType;

    private IntegerFilter targetCharacterId;

    private LongFilter mcardthumbnailassetsId;

    public MCardPowerupActionSkillCriteria(){
    }

    public MCardPowerupActionSkillCriteria(MCardPowerupActionSkillCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.attribute = other.attribute == null ? null : other.attribute.copy();
        this.actionRarity = other.actionRarity == null ? null : other.actionRarity.copy();
        this.gainActionExp = other.gainActionExp == null ? null : other.gainActionExp.copy();
        this.coin = other.coin == null ? null : other.coin.copy();
        this.sellMedalId = other.sellMedalId == null ? null : other.sellMedalId.copy();
        this.thumbnailAssetsId = other.thumbnailAssetsId == null ? null : other.thumbnailAssetsId.copy();
        this.cardIllustAssetsId = other.cardIllustAssetsId == null ? null : other.cardIllustAssetsId.copy();
        this.targetActionCommandType = other.targetActionCommandType == null ? null : other.targetActionCommandType.copy();
        this.targetCharacterId = other.targetCharacterId == null ? null : other.targetCharacterId.copy();
        this.mcardthumbnailassetsId = other.mcardthumbnailassetsId == null ? null : other.mcardthumbnailassetsId.copy();
    }

    @Override
    public MCardPowerupActionSkillCriteria copy() {
        return new MCardPowerupActionSkillCriteria(this);
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

    public IntegerFilter getActionRarity() {
        return actionRarity;
    }

    public void setActionRarity(IntegerFilter actionRarity) {
        this.actionRarity = actionRarity;
    }

    public IntegerFilter getGainActionExp() {
        return gainActionExp;
    }

    public void setGainActionExp(IntegerFilter gainActionExp) {
        this.gainActionExp = gainActionExp;
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

    public IntegerFilter getTargetActionCommandType() {
        return targetActionCommandType;
    }

    public void setTargetActionCommandType(IntegerFilter targetActionCommandType) {
        this.targetActionCommandType = targetActionCommandType;
    }

    public IntegerFilter getTargetCharacterId() {
        return targetCharacterId;
    }

    public void setTargetCharacterId(IntegerFilter targetCharacterId) {
        this.targetCharacterId = targetCharacterId;
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
        final MCardPowerupActionSkillCriteria that = (MCardPowerupActionSkillCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(attribute, that.attribute) &&
            Objects.equals(actionRarity, that.actionRarity) &&
            Objects.equals(gainActionExp, that.gainActionExp) &&
            Objects.equals(coin, that.coin) &&
            Objects.equals(sellMedalId, that.sellMedalId) &&
            Objects.equals(thumbnailAssetsId, that.thumbnailAssetsId) &&
            Objects.equals(cardIllustAssetsId, that.cardIllustAssetsId) &&
            Objects.equals(targetActionCommandType, that.targetActionCommandType) &&
            Objects.equals(targetCharacterId, that.targetCharacterId) &&
            Objects.equals(mcardthumbnailassetsId, that.mcardthumbnailassetsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        rarity,
        attribute,
        actionRarity,
        gainActionExp,
        coin,
        sellMedalId,
        thumbnailAssetsId,
        cardIllustAssetsId,
        targetActionCommandType,
        targetCharacterId,
        mcardthumbnailassetsId
        );
    }

    @Override
    public String toString() {
        return "MCardPowerupActionSkillCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (attribute != null ? "attribute=" + attribute + ", " : "") +
                (actionRarity != null ? "actionRarity=" + actionRarity + ", " : "") +
                (gainActionExp != null ? "gainActionExp=" + gainActionExp + ", " : "") +
                (coin != null ? "coin=" + coin + ", " : "") +
                (sellMedalId != null ? "sellMedalId=" + sellMedalId + ", " : "") +
                (thumbnailAssetsId != null ? "thumbnailAssetsId=" + thumbnailAssetsId + ", " : "") +
                (cardIllustAssetsId != null ? "cardIllustAssetsId=" + cardIllustAssetsId + ", " : "") +
                (targetActionCommandType != null ? "targetActionCommandType=" + targetActionCommandType + ", " : "") +
                (targetCharacterId != null ? "targetCharacterId=" + targetCharacterId + ", " : "") +
                (mcardthumbnailassetsId != null ? "mcardthumbnailassetsId=" + mcardthumbnailassetsId + ", " : "") +
            "}";
    }

}
