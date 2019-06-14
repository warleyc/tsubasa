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
 * Criteria class for the {@link io.shm.tsubasa.domain.MPlayableCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MPlayableCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-playable-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MPlayableCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter modelId;

    private IntegerFilter properPositionGk;

    private IntegerFilter properPositionFw;

    private IntegerFilter properPositionOmf;

    private IntegerFilter properPositionDmf;

    private IntegerFilter properPositionDf;

    private IntegerFilter characterId;

    private IntegerFilter teamId;

    private IntegerFilter nationalityId;

    private IntegerFilter rarity;

    private IntegerFilter attribute;

    private IntegerFilter thumbnailAssetsId;

    private IntegerFilter cardIllustAssetsId;

    private IntegerFilter playableAssetsId;

    private IntegerFilter teamEffectId;

    private IntegerFilter triggerEffectId;

    private IntegerFilter maxActionSlot;

    private IntegerFilter initialActionId1;

    private IntegerFilter initialActionId2;

    private IntegerFilter initialActionId3;

    private IntegerFilter initialActionId4;

    private IntegerFilter initialActionId5;

    private IntegerFilter initialStamina;

    private IntegerFilter initialDribble;

    private IntegerFilter initialShoot;

    private IntegerFilter initialPass;

    private IntegerFilter initialTackle;

    private IntegerFilter initialBlock;

    private IntegerFilter initialIntercept;

    private IntegerFilter initialSpeed;

    private IntegerFilter initialPower;

    private IntegerFilter initialTechnique;

    private IntegerFilter initialPunching;

    private IntegerFilter initialCatching;

    private IntegerFilter maxStamina;

    private IntegerFilter maxDribble;

    private IntegerFilter maxShoot;

    private IntegerFilter maxPass;

    private IntegerFilter maxTackle;

    private IntegerFilter maxBlock;

    private IntegerFilter maxIntercept;

    private IntegerFilter maxSpeed;

    private IntegerFilter maxPower;

    private IntegerFilter maxTechnique;

    private IntegerFilter maxPunching;

    private IntegerFilter maxCatching;

    private IntegerFilter maxPlusDribble;

    private IntegerFilter maxPlusShoot;

    private IntegerFilter maxPlusPass;

    private IntegerFilter maxPlusTackle;

    private IntegerFilter maxPlusBlock;

    private IntegerFilter maxPlusIntercept;

    private IntegerFilter maxPlusSpeed;

    private IntegerFilter maxPlusPower;

    private IntegerFilter maxPlusTechnique;

    private IntegerFilter maxPlusPunching;

    private IntegerFilter maxPlusCatching;

    private IntegerFilter highBallBonus;

    private IntegerFilter lowBallBonus;

    private IntegerFilter isDropOnly;

    private IntegerFilter sellCoinGroupNum;

    private IntegerFilter sellMedalId;

    private IntegerFilter characterBookId;

    private IntegerFilter bookNo;

    private IntegerFilter isShowBook;

    private IntegerFilter levelGroupId;

    private IntegerFilter startAt;

    private LongFilter mmodelcardId;

    private LongFilter mArousalId;

    private LongFilter mTargetPlayableCardGroupId;

    public MPlayableCardCriteria(){
    }

    public MPlayableCardCriteria(MPlayableCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.modelId = other.modelId == null ? null : other.modelId.copy();
        this.properPositionGk = other.properPositionGk == null ? null : other.properPositionGk.copy();
        this.properPositionFw = other.properPositionFw == null ? null : other.properPositionFw.copy();
        this.properPositionOmf = other.properPositionOmf == null ? null : other.properPositionOmf.copy();
        this.properPositionDmf = other.properPositionDmf == null ? null : other.properPositionDmf.copy();
        this.properPositionDf = other.properPositionDf == null ? null : other.properPositionDf.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.teamId = other.teamId == null ? null : other.teamId.copy();
        this.nationalityId = other.nationalityId == null ? null : other.nationalityId.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.attribute = other.attribute == null ? null : other.attribute.copy();
        this.thumbnailAssetsId = other.thumbnailAssetsId == null ? null : other.thumbnailAssetsId.copy();
        this.cardIllustAssetsId = other.cardIllustAssetsId == null ? null : other.cardIllustAssetsId.copy();
        this.playableAssetsId = other.playableAssetsId == null ? null : other.playableAssetsId.copy();
        this.teamEffectId = other.teamEffectId == null ? null : other.teamEffectId.copy();
        this.triggerEffectId = other.triggerEffectId == null ? null : other.triggerEffectId.copy();
        this.maxActionSlot = other.maxActionSlot == null ? null : other.maxActionSlot.copy();
        this.initialActionId1 = other.initialActionId1 == null ? null : other.initialActionId1.copy();
        this.initialActionId2 = other.initialActionId2 == null ? null : other.initialActionId2.copy();
        this.initialActionId3 = other.initialActionId3 == null ? null : other.initialActionId3.copy();
        this.initialActionId4 = other.initialActionId4 == null ? null : other.initialActionId4.copy();
        this.initialActionId5 = other.initialActionId5 == null ? null : other.initialActionId5.copy();
        this.initialStamina = other.initialStamina == null ? null : other.initialStamina.copy();
        this.initialDribble = other.initialDribble == null ? null : other.initialDribble.copy();
        this.initialShoot = other.initialShoot == null ? null : other.initialShoot.copy();
        this.initialPass = other.initialPass == null ? null : other.initialPass.copy();
        this.initialTackle = other.initialTackle == null ? null : other.initialTackle.copy();
        this.initialBlock = other.initialBlock == null ? null : other.initialBlock.copy();
        this.initialIntercept = other.initialIntercept == null ? null : other.initialIntercept.copy();
        this.initialSpeed = other.initialSpeed == null ? null : other.initialSpeed.copy();
        this.initialPower = other.initialPower == null ? null : other.initialPower.copy();
        this.initialTechnique = other.initialTechnique == null ? null : other.initialTechnique.copy();
        this.initialPunching = other.initialPunching == null ? null : other.initialPunching.copy();
        this.initialCatching = other.initialCatching == null ? null : other.initialCatching.copy();
        this.maxStamina = other.maxStamina == null ? null : other.maxStamina.copy();
        this.maxDribble = other.maxDribble == null ? null : other.maxDribble.copy();
        this.maxShoot = other.maxShoot == null ? null : other.maxShoot.copy();
        this.maxPass = other.maxPass == null ? null : other.maxPass.copy();
        this.maxTackle = other.maxTackle == null ? null : other.maxTackle.copy();
        this.maxBlock = other.maxBlock == null ? null : other.maxBlock.copy();
        this.maxIntercept = other.maxIntercept == null ? null : other.maxIntercept.copy();
        this.maxSpeed = other.maxSpeed == null ? null : other.maxSpeed.copy();
        this.maxPower = other.maxPower == null ? null : other.maxPower.copy();
        this.maxTechnique = other.maxTechnique == null ? null : other.maxTechnique.copy();
        this.maxPunching = other.maxPunching == null ? null : other.maxPunching.copy();
        this.maxCatching = other.maxCatching == null ? null : other.maxCatching.copy();
        this.maxPlusDribble = other.maxPlusDribble == null ? null : other.maxPlusDribble.copy();
        this.maxPlusShoot = other.maxPlusShoot == null ? null : other.maxPlusShoot.copy();
        this.maxPlusPass = other.maxPlusPass == null ? null : other.maxPlusPass.copy();
        this.maxPlusTackle = other.maxPlusTackle == null ? null : other.maxPlusTackle.copy();
        this.maxPlusBlock = other.maxPlusBlock == null ? null : other.maxPlusBlock.copy();
        this.maxPlusIntercept = other.maxPlusIntercept == null ? null : other.maxPlusIntercept.copy();
        this.maxPlusSpeed = other.maxPlusSpeed == null ? null : other.maxPlusSpeed.copy();
        this.maxPlusPower = other.maxPlusPower == null ? null : other.maxPlusPower.copy();
        this.maxPlusTechnique = other.maxPlusTechnique == null ? null : other.maxPlusTechnique.copy();
        this.maxPlusPunching = other.maxPlusPunching == null ? null : other.maxPlusPunching.copy();
        this.maxPlusCatching = other.maxPlusCatching == null ? null : other.maxPlusCatching.copy();
        this.highBallBonus = other.highBallBonus == null ? null : other.highBallBonus.copy();
        this.lowBallBonus = other.lowBallBonus == null ? null : other.lowBallBonus.copy();
        this.isDropOnly = other.isDropOnly == null ? null : other.isDropOnly.copy();
        this.sellCoinGroupNum = other.sellCoinGroupNum == null ? null : other.sellCoinGroupNum.copy();
        this.sellMedalId = other.sellMedalId == null ? null : other.sellMedalId.copy();
        this.characterBookId = other.characterBookId == null ? null : other.characterBookId.copy();
        this.bookNo = other.bookNo == null ? null : other.bookNo.copy();
        this.isShowBook = other.isShowBook == null ? null : other.isShowBook.copy();
        this.levelGroupId = other.levelGroupId == null ? null : other.levelGroupId.copy();
        this.startAt = other.startAt == null ? null : other.startAt.copy();
        this.mmodelcardId = other.mmodelcardId == null ? null : other.mmodelcardId.copy();
        this.mArousalId = other.mArousalId == null ? null : other.mArousalId.copy();
        this.mTargetPlayableCardGroupId = other.mTargetPlayableCardGroupId == null ? null : other.mTargetPlayableCardGroupId.copy();
    }

    @Override
    public MPlayableCardCriteria copy() {
        return new MPlayableCardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getModelId() {
        return modelId;
    }

    public void setModelId(IntegerFilter modelId) {
        this.modelId = modelId;
    }

    public IntegerFilter getProperPositionGk() {
        return properPositionGk;
    }

    public void setProperPositionGk(IntegerFilter properPositionGk) {
        this.properPositionGk = properPositionGk;
    }

    public IntegerFilter getProperPositionFw() {
        return properPositionFw;
    }

    public void setProperPositionFw(IntegerFilter properPositionFw) {
        this.properPositionFw = properPositionFw;
    }

    public IntegerFilter getProperPositionOmf() {
        return properPositionOmf;
    }

    public void setProperPositionOmf(IntegerFilter properPositionOmf) {
        this.properPositionOmf = properPositionOmf;
    }

    public IntegerFilter getProperPositionDmf() {
        return properPositionDmf;
    }

    public void setProperPositionDmf(IntegerFilter properPositionDmf) {
        this.properPositionDmf = properPositionDmf;
    }

    public IntegerFilter getProperPositionDf() {
        return properPositionDf;
    }

    public void setProperPositionDf(IntegerFilter properPositionDf) {
        this.properPositionDf = properPositionDf;
    }

    public IntegerFilter getCharacterId() {
        return characterId;
    }

    public void setCharacterId(IntegerFilter characterId) {
        this.characterId = characterId;
    }

    public IntegerFilter getTeamId() {
        return teamId;
    }

    public void setTeamId(IntegerFilter teamId) {
        this.teamId = teamId;
    }

    public IntegerFilter getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(IntegerFilter nationalityId) {
        this.nationalityId = nationalityId;
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

    public IntegerFilter getPlayableAssetsId() {
        return playableAssetsId;
    }

    public void setPlayableAssetsId(IntegerFilter playableAssetsId) {
        this.playableAssetsId = playableAssetsId;
    }

    public IntegerFilter getTeamEffectId() {
        return teamEffectId;
    }

    public void setTeamEffectId(IntegerFilter teamEffectId) {
        this.teamEffectId = teamEffectId;
    }

    public IntegerFilter getTriggerEffectId() {
        return triggerEffectId;
    }

    public void setTriggerEffectId(IntegerFilter triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public IntegerFilter getMaxActionSlot() {
        return maxActionSlot;
    }

    public void setMaxActionSlot(IntegerFilter maxActionSlot) {
        this.maxActionSlot = maxActionSlot;
    }

    public IntegerFilter getInitialActionId1() {
        return initialActionId1;
    }

    public void setInitialActionId1(IntegerFilter initialActionId1) {
        this.initialActionId1 = initialActionId1;
    }

    public IntegerFilter getInitialActionId2() {
        return initialActionId2;
    }

    public void setInitialActionId2(IntegerFilter initialActionId2) {
        this.initialActionId2 = initialActionId2;
    }

    public IntegerFilter getInitialActionId3() {
        return initialActionId3;
    }

    public void setInitialActionId3(IntegerFilter initialActionId3) {
        this.initialActionId3 = initialActionId3;
    }

    public IntegerFilter getInitialActionId4() {
        return initialActionId4;
    }

    public void setInitialActionId4(IntegerFilter initialActionId4) {
        this.initialActionId4 = initialActionId4;
    }

    public IntegerFilter getInitialActionId5() {
        return initialActionId5;
    }

    public void setInitialActionId5(IntegerFilter initialActionId5) {
        this.initialActionId5 = initialActionId5;
    }

    public IntegerFilter getInitialStamina() {
        return initialStamina;
    }

    public void setInitialStamina(IntegerFilter initialStamina) {
        this.initialStamina = initialStamina;
    }

    public IntegerFilter getInitialDribble() {
        return initialDribble;
    }

    public void setInitialDribble(IntegerFilter initialDribble) {
        this.initialDribble = initialDribble;
    }

    public IntegerFilter getInitialShoot() {
        return initialShoot;
    }

    public void setInitialShoot(IntegerFilter initialShoot) {
        this.initialShoot = initialShoot;
    }

    public IntegerFilter getInitialPass() {
        return initialPass;
    }

    public void setInitialPass(IntegerFilter initialPass) {
        this.initialPass = initialPass;
    }

    public IntegerFilter getInitialTackle() {
        return initialTackle;
    }

    public void setInitialTackle(IntegerFilter initialTackle) {
        this.initialTackle = initialTackle;
    }

    public IntegerFilter getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(IntegerFilter initialBlock) {
        this.initialBlock = initialBlock;
    }

    public IntegerFilter getInitialIntercept() {
        return initialIntercept;
    }

    public void setInitialIntercept(IntegerFilter initialIntercept) {
        this.initialIntercept = initialIntercept;
    }

    public IntegerFilter getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(IntegerFilter initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public IntegerFilter getInitialPower() {
        return initialPower;
    }

    public void setInitialPower(IntegerFilter initialPower) {
        this.initialPower = initialPower;
    }

    public IntegerFilter getInitialTechnique() {
        return initialTechnique;
    }

    public void setInitialTechnique(IntegerFilter initialTechnique) {
        this.initialTechnique = initialTechnique;
    }

    public IntegerFilter getInitialPunching() {
        return initialPunching;
    }

    public void setInitialPunching(IntegerFilter initialPunching) {
        this.initialPunching = initialPunching;
    }

    public IntegerFilter getInitialCatching() {
        return initialCatching;
    }

    public void setInitialCatching(IntegerFilter initialCatching) {
        this.initialCatching = initialCatching;
    }

    public IntegerFilter getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(IntegerFilter maxStamina) {
        this.maxStamina = maxStamina;
    }

    public IntegerFilter getMaxDribble() {
        return maxDribble;
    }

    public void setMaxDribble(IntegerFilter maxDribble) {
        this.maxDribble = maxDribble;
    }

    public IntegerFilter getMaxShoot() {
        return maxShoot;
    }

    public void setMaxShoot(IntegerFilter maxShoot) {
        this.maxShoot = maxShoot;
    }

    public IntegerFilter getMaxPass() {
        return maxPass;
    }

    public void setMaxPass(IntegerFilter maxPass) {
        this.maxPass = maxPass;
    }

    public IntegerFilter getMaxTackle() {
        return maxTackle;
    }

    public void setMaxTackle(IntegerFilter maxTackle) {
        this.maxTackle = maxTackle;
    }

    public IntegerFilter getMaxBlock() {
        return maxBlock;
    }

    public void setMaxBlock(IntegerFilter maxBlock) {
        this.maxBlock = maxBlock;
    }

    public IntegerFilter getMaxIntercept() {
        return maxIntercept;
    }

    public void setMaxIntercept(IntegerFilter maxIntercept) {
        this.maxIntercept = maxIntercept;
    }

    public IntegerFilter getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(IntegerFilter maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public IntegerFilter getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(IntegerFilter maxPower) {
        this.maxPower = maxPower;
    }

    public IntegerFilter getMaxTechnique() {
        return maxTechnique;
    }

    public void setMaxTechnique(IntegerFilter maxTechnique) {
        this.maxTechnique = maxTechnique;
    }

    public IntegerFilter getMaxPunching() {
        return maxPunching;
    }

    public void setMaxPunching(IntegerFilter maxPunching) {
        this.maxPunching = maxPunching;
    }

    public IntegerFilter getMaxCatching() {
        return maxCatching;
    }

    public void setMaxCatching(IntegerFilter maxCatching) {
        this.maxCatching = maxCatching;
    }

    public IntegerFilter getMaxPlusDribble() {
        return maxPlusDribble;
    }

    public void setMaxPlusDribble(IntegerFilter maxPlusDribble) {
        this.maxPlusDribble = maxPlusDribble;
    }

    public IntegerFilter getMaxPlusShoot() {
        return maxPlusShoot;
    }

    public void setMaxPlusShoot(IntegerFilter maxPlusShoot) {
        this.maxPlusShoot = maxPlusShoot;
    }

    public IntegerFilter getMaxPlusPass() {
        return maxPlusPass;
    }

    public void setMaxPlusPass(IntegerFilter maxPlusPass) {
        this.maxPlusPass = maxPlusPass;
    }

    public IntegerFilter getMaxPlusTackle() {
        return maxPlusTackle;
    }

    public void setMaxPlusTackle(IntegerFilter maxPlusTackle) {
        this.maxPlusTackle = maxPlusTackle;
    }

    public IntegerFilter getMaxPlusBlock() {
        return maxPlusBlock;
    }

    public void setMaxPlusBlock(IntegerFilter maxPlusBlock) {
        this.maxPlusBlock = maxPlusBlock;
    }

    public IntegerFilter getMaxPlusIntercept() {
        return maxPlusIntercept;
    }

    public void setMaxPlusIntercept(IntegerFilter maxPlusIntercept) {
        this.maxPlusIntercept = maxPlusIntercept;
    }

    public IntegerFilter getMaxPlusSpeed() {
        return maxPlusSpeed;
    }

    public void setMaxPlusSpeed(IntegerFilter maxPlusSpeed) {
        this.maxPlusSpeed = maxPlusSpeed;
    }

    public IntegerFilter getMaxPlusPower() {
        return maxPlusPower;
    }

    public void setMaxPlusPower(IntegerFilter maxPlusPower) {
        this.maxPlusPower = maxPlusPower;
    }

    public IntegerFilter getMaxPlusTechnique() {
        return maxPlusTechnique;
    }

    public void setMaxPlusTechnique(IntegerFilter maxPlusTechnique) {
        this.maxPlusTechnique = maxPlusTechnique;
    }

    public IntegerFilter getMaxPlusPunching() {
        return maxPlusPunching;
    }

    public void setMaxPlusPunching(IntegerFilter maxPlusPunching) {
        this.maxPlusPunching = maxPlusPunching;
    }

    public IntegerFilter getMaxPlusCatching() {
        return maxPlusCatching;
    }

    public void setMaxPlusCatching(IntegerFilter maxPlusCatching) {
        this.maxPlusCatching = maxPlusCatching;
    }

    public IntegerFilter getHighBallBonus() {
        return highBallBonus;
    }

    public void setHighBallBonus(IntegerFilter highBallBonus) {
        this.highBallBonus = highBallBonus;
    }

    public IntegerFilter getLowBallBonus() {
        return lowBallBonus;
    }

    public void setLowBallBonus(IntegerFilter lowBallBonus) {
        this.lowBallBonus = lowBallBonus;
    }

    public IntegerFilter getIsDropOnly() {
        return isDropOnly;
    }

    public void setIsDropOnly(IntegerFilter isDropOnly) {
        this.isDropOnly = isDropOnly;
    }

    public IntegerFilter getSellCoinGroupNum() {
        return sellCoinGroupNum;
    }

    public void setSellCoinGroupNum(IntegerFilter sellCoinGroupNum) {
        this.sellCoinGroupNum = sellCoinGroupNum;
    }

    public IntegerFilter getSellMedalId() {
        return sellMedalId;
    }

    public void setSellMedalId(IntegerFilter sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public IntegerFilter getCharacterBookId() {
        return characterBookId;
    }

    public void setCharacterBookId(IntegerFilter characterBookId) {
        this.characterBookId = characterBookId;
    }

    public IntegerFilter getBookNo() {
        return bookNo;
    }

    public void setBookNo(IntegerFilter bookNo) {
        this.bookNo = bookNo;
    }

    public IntegerFilter getIsShowBook() {
        return isShowBook;
    }

    public void setIsShowBook(IntegerFilter isShowBook) {
        this.isShowBook = isShowBook;
    }

    public IntegerFilter getLevelGroupId() {
        return levelGroupId;
    }

    public void setLevelGroupId(IntegerFilter levelGroupId) {
        this.levelGroupId = levelGroupId;
    }

    public IntegerFilter getStartAt() {
        return startAt;
    }

    public void setStartAt(IntegerFilter startAt) {
        this.startAt = startAt;
    }

    public LongFilter getMmodelcardId() {
        return mmodelcardId;
    }

    public void setMmodelcardId(LongFilter mmodelcardId) {
        this.mmodelcardId = mmodelcardId;
    }

    public LongFilter getMArousalId() {
        return mArousalId;
    }

    public void setMArousalId(LongFilter mArousalId) {
        this.mArousalId = mArousalId;
    }

    public LongFilter getMTargetPlayableCardGroupId() {
        return mTargetPlayableCardGroupId;
    }

    public void setMTargetPlayableCardGroupId(LongFilter mTargetPlayableCardGroupId) {
        this.mTargetPlayableCardGroupId = mTargetPlayableCardGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MPlayableCardCriteria that = (MPlayableCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(modelId, that.modelId) &&
            Objects.equals(properPositionGk, that.properPositionGk) &&
            Objects.equals(properPositionFw, that.properPositionFw) &&
            Objects.equals(properPositionOmf, that.properPositionOmf) &&
            Objects.equals(properPositionDmf, that.properPositionDmf) &&
            Objects.equals(properPositionDf, that.properPositionDf) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(teamId, that.teamId) &&
            Objects.equals(nationalityId, that.nationalityId) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(attribute, that.attribute) &&
            Objects.equals(thumbnailAssetsId, that.thumbnailAssetsId) &&
            Objects.equals(cardIllustAssetsId, that.cardIllustAssetsId) &&
            Objects.equals(playableAssetsId, that.playableAssetsId) &&
            Objects.equals(teamEffectId, that.teamEffectId) &&
            Objects.equals(triggerEffectId, that.triggerEffectId) &&
            Objects.equals(maxActionSlot, that.maxActionSlot) &&
            Objects.equals(initialActionId1, that.initialActionId1) &&
            Objects.equals(initialActionId2, that.initialActionId2) &&
            Objects.equals(initialActionId3, that.initialActionId3) &&
            Objects.equals(initialActionId4, that.initialActionId4) &&
            Objects.equals(initialActionId5, that.initialActionId5) &&
            Objects.equals(initialStamina, that.initialStamina) &&
            Objects.equals(initialDribble, that.initialDribble) &&
            Objects.equals(initialShoot, that.initialShoot) &&
            Objects.equals(initialPass, that.initialPass) &&
            Objects.equals(initialTackle, that.initialTackle) &&
            Objects.equals(initialBlock, that.initialBlock) &&
            Objects.equals(initialIntercept, that.initialIntercept) &&
            Objects.equals(initialSpeed, that.initialSpeed) &&
            Objects.equals(initialPower, that.initialPower) &&
            Objects.equals(initialTechnique, that.initialTechnique) &&
            Objects.equals(initialPunching, that.initialPunching) &&
            Objects.equals(initialCatching, that.initialCatching) &&
            Objects.equals(maxStamina, that.maxStamina) &&
            Objects.equals(maxDribble, that.maxDribble) &&
            Objects.equals(maxShoot, that.maxShoot) &&
            Objects.equals(maxPass, that.maxPass) &&
            Objects.equals(maxTackle, that.maxTackle) &&
            Objects.equals(maxBlock, that.maxBlock) &&
            Objects.equals(maxIntercept, that.maxIntercept) &&
            Objects.equals(maxSpeed, that.maxSpeed) &&
            Objects.equals(maxPower, that.maxPower) &&
            Objects.equals(maxTechnique, that.maxTechnique) &&
            Objects.equals(maxPunching, that.maxPunching) &&
            Objects.equals(maxCatching, that.maxCatching) &&
            Objects.equals(maxPlusDribble, that.maxPlusDribble) &&
            Objects.equals(maxPlusShoot, that.maxPlusShoot) &&
            Objects.equals(maxPlusPass, that.maxPlusPass) &&
            Objects.equals(maxPlusTackle, that.maxPlusTackle) &&
            Objects.equals(maxPlusBlock, that.maxPlusBlock) &&
            Objects.equals(maxPlusIntercept, that.maxPlusIntercept) &&
            Objects.equals(maxPlusSpeed, that.maxPlusSpeed) &&
            Objects.equals(maxPlusPower, that.maxPlusPower) &&
            Objects.equals(maxPlusTechnique, that.maxPlusTechnique) &&
            Objects.equals(maxPlusPunching, that.maxPlusPunching) &&
            Objects.equals(maxPlusCatching, that.maxPlusCatching) &&
            Objects.equals(highBallBonus, that.highBallBonus) &&
            Objects.equals(lowBallBonus, that.lowBallBonus) &&
            Objects.equals(isDropOnly, that.isDropOnly) &&
            Objects.equals(sellCoinGroupNum, that.sellCoinGroupNum) &&
            Objects.equals(sellMedalId, that.sellMedalId) &&
            Objects.equals(characterBookId, that.characterBookId) &&
            Objects.equals(bookNo, that.bookNo) &&
            Objects.equals(isShowBook, that.isShowBook) &&
            Objects.equals(levelGroupId, that.levelGroupId) &&
            Objects.equals(startAt, that.startAt) &&
            Objects.equals(mmodelcardId, that.mmodelcardId) &&
            Objects.equals(mArousalId, that.mArousalId) &&
            Objects.equals(mTargetPlayableCardGroupId, that.mTargetPlayableCardGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        modelId,
        properPositionGk,
        properPositionFw,
        properPositionOmf,
        properPositionDmf,
        properPositionDf,
        characterId,
        teamId,
        nationalityId,
        rarity,
        attribute,
        thumbnailAssetsId,
        cardIllustAssetsId,
        playableAssetsId,
        teamEffectId,
        triggerEffectId,
        maxActionSlot,
        initialActionId1,
        initialActionId2,
        initialActionId3,
        initialActionId4,
        initialActionId5,
        initialStamina,
        initialDribble,
        initialShoot,
        initialPass,
        initialTackle,
        initialBlock,
        initialIntercept,
        initialSpeed,
        initialPower,
        initialTechnique,
        initialPunching,
        initialCatching,
        maxStamina,
        maxDribble,
        maxShoot,
        maxPass,
        maxTackle,
        maxBlock,
        maxIntercept,
        maxSpeed,
        maxPower,
        maxTechnique,
        maxPunching,
        maxCatching,
        maxPlusDribble,
        maxPlusShoot,
        maxPlusPass,
        maxPlusTackle,
        maxPlusBlock,
        maxPlusIntercept,
        maxPlusSpeed,
        maxPlusPower,
        maxPlusTechnique,
        maxPlusPunching,
        maxPlusCatching,
        highBallBonus,
        lowBallBonus,
        isDropOnly,
        sellCoinGroupNum,
        sellMedalId,
        characterBookId,
        bookNo,
        isShowBook,
        levelGroupId,
        startAt,
        mmodelcardId,
        mArousalId,
        mTargetPlayableCardGroupId
        );
    }

    @Override
    public String toString() {
        return "MPlayableCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (modelId != null ? "modelId=" + modelId + ", " : "") +
                (properPositionGk != null ? "properPositionGk=" + properPositionGk + ", " : "") +
                (properPositionFw != null ? "properPositionFw=" + properPositionFw + ", " : "") +
                (properPositionOmf != null ? "properPositionOmf=" + properPositionOmf + ", " : "") +
                (properPositionDmf != null ? "properPositionDmf=" + properPositionDmf + ", " : "") +
                (properPositionDf != null ? "properPositionDf=" + properPositionDf + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (teamId != null ? "teamId=" + teamId + ", " : "") +
                (nationalityId != null ? "nationalityId=" + nationalityId + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (attribute != null ? "attribute=" + attribute + ", " : "") +
                (thumbnailAssetsId != null ? "thumbnailAssetsId=" + thumbnailAssetsId + ", " : "") +
                (cardIllustAssetsId != null ? "cardIllustAssetsId=" + cardIllustAssetsId + ", " : "") +
                (playableAssetsId != null ? "playableAssetsId=" + playableAssetsId + ", " : "") +
                (teamEffectId != null ? "teamEffectId=" + teamEffectId + ", " : "") +
                (triggerEffectId != null ? "triggerEffectId=" + triggerEffectId + ", " : "") +
                (maxActionSlot != null ? "maxActionSlot=" + maxActionSlot + ", " : "") +
                (initialActionId1 != null ? "initialActionId1=" + initialActionId1 + ", " : "") +
                (initialActionId2 != null ? "initialActionId2=" + initialActionId2 + ", " : "") +
                (initialActionId3 != null ? "initialActionId3=" + initialActionId3 + ", " : "") +
                (initialActionId4 != null ? "initialActionId4=" + initialActionId4 + ", " : "") +
                (initialActionId5 != null ? "initialActionId5=" + initialActionId5 + ", " : "") +
                (initialStamina != null ? "initialStamina=" + initialStamina + ", " : "") +
                (initialDribble != null ? "initialDribble=" + initialDribble + ", " : "") +
                (initialShoot != null ? "initialShoot=" + initialShoot + ", " : "") +
                (initialPass != null ? "initialPass=" + initialPass + ", " : "") +
                (initialTackle != null ? "initialTackle=" + initialTackle + ", " : "") +
                (initialBlock != null ? "initialBlock=" + initialBlock + ", " : "") +
                (initialIntercept != null ? "initialIntercept=" + initialIntercept + ", " : "") +
                (initialSpeed != null ? "initialSpeed=" + initialSpeed + ", " : "") +
                (initialPower != null ? "initialPower=" + initialPower + ", " : "") +
                (initialTechnique != null ? "initialTechnique=" + initialTechnique + ", " : "") +
                (initialPunching != null ? "initialPunching=" + initialPunching + ", " : "") +
                (initialCatching != null ? "initialCatching=" + initialCatching + ", " : "") +
                (maxStamina != null ? "maxStamina=" + maxStamina + ", " : "") +
                (maxDribble != null ? "maxDribble=" + maxDribble + ", " : "") +
                (maxShoot != null ? "maxShoot=" + maxShoot + ", " : "") +
                (maxPass != null ? "maxPass=" + maxPass + ", " : "") +
                (maxTackle != null ? "maxTackle=" + maxTackle + ", " : "") +
                (maxBlock != null ? "maxBlock=" + maxBlock + ", " : "") +
                (maxIntercept != null ? "maxIntercept=" + maxIntercept + ", " : "") +
                (maxSpeed != null ? "maxSpeed=" + maxSpeed + ", " : "") +
                (maxPower != null ? "maxPower=" + maxPower + ", " : "") +
                (maxTechnique != null ? "maxTechnique=" + maxTechnique + ", " : "") +
                (maxPunching != null ? "maxPunching=" + maxPunching + ", " : "") +
                (maxCatching != null ? "maxCatching=" + maxCatching + ", " : "") +
                (maxPlusDribble != null ? "maxPlusDribble=" + maxPlusDribble + ", " : "") +
                (maxPlusShoot != null ? "maxPlusShoot=" + maxPlusShoot + ", " : "") +
                (maxPlusPass != null ? "maxPlusPass=" + maxPlusPass + ", " : "") +
                (maxPlusTackle != null ? "maxPlusTackle=" + maxPlusTackle + ", " : "") +
                (maxPlusBlock != null ? "maxPlusBlock=" + maxPlusBlock + ", " : "") +
                (maxPlusIntercept != null ? "maxPlusIntercept=" + maxPlusIntercept + ", " : "") +
                (maxPlusSpeed != null ? "maxPlusSpeed=" + maxPlusSpeed + ", " : "") +
                (maxPlusPower != null ? "maxPlusPower=" + maxPlusPower + ", " : "") +
                (maxPlusTechnique != null ? "maxPlusTechnique=" + maxPlusTechnique + ", " : "") +
                (maxPlusPunching != null ? "maxPlusPunching=" + maxPlusPunching + ", " : "") +
                (maxPlusCatching != null ? "maxPlusCatching=" + maxPlusCatching + ", " : "") +
                (highBallBonus != null ? "highBallBonus=" + highBallBonus + ", " : "") +
                (lowBallBonus != null ? "lowBallBonus=" + lowBallBonus + ", " : "") +
                (isDropOnly != null ? "isDropOnly=" + isDropOnly + ", " : "") +
                (sellCoinGroupNum != null ? "sellCoinGroupNum=" + sellCoinGroupNum + ", " : "") +
                (sellMedalId != null ? "sellMedalId=" + sellMedalId + ", " : "") +
                (characterBookId != null ? "characterBookId=" + characterBookId + ", " : "") +
                (bookNo != null ? "bookNo=" + bookNo + ", " : "") +
                (isShowBook != null ? "isShowBook=" + isShowBook + ", " : "") +
                (levelGroupId != null ? "levelGroupId=" + levelGroupId + ", " : "") +
                (startAt != null ? "startAt=" + startAt + ", " : "") +
                (mmodelcardId != null ? "mmodelcardId=" + mmodelcardId + ", " : "") +
                (mArousalId != null ? "mArousalId=" + mArousalId + ", " : "") +
                (mTargetPlayableCardGroupId != null ? "mTargetPlayableCardGroupId=" + mTargetPlayableCardGroupId + ", " : "") +
            "}";
    }

}
