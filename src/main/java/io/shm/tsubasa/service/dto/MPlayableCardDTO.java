package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MPlayableCard} entity.
 */
public class MPlayableCardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer modelId;

    @NotNull
    private Integer properPositionGk;

    @NotNull
    private Integer properPositionFw;

    @NotNull
    private Integer properPositionOmf;

    @NotNull
    private Integer properPositionDmf;

    @NotNull
    private Integer properPositionDf;

    @NotNull
    private Integer characterId;

    
    @Lob
    private String nickName;

    @NotNull
    private Integer teamId;

    @NotNull
    private Integer nationalityId;

    @NotNull
    private Integer rarity;

    @NotNull
    private Integer attribute;

    @NotNull
    private Integer thumbnailAssetsId;

    @NotNull
    private Integer cardIllustAssetsId;

    @NotNull
    private Integer playableAssetsId;

    private Integer teamEffectId;

    private Integer triggerEffectId;

    @NotNull
    private Integer maxActionSlot;

    private Integer initialActionId1;

    private Integer initialActionId2;

    private Integer initialActionId3;

    private Integer initialActionId4;

    private Integer initialActionId5;

    @NotNull
    private Integer initialStamina;

    @NotNull
    private Integer initialDribble;

    @NotNull
    private Integer initialShoot;

    @NotNull
    private Integer initialPass;

    @NotNull
    private Integer initialTackle;

    @NotNull
    private Integer initialBlock;

    @NotNull
    private Integer initialIntercept;

    @NotNull
    private Integer initialSpeed;

    @NotNull
    private Integer initialPower;

    @NotNull
    private Integer initialTechnique;

    @NotNull
    private Integer initialPunching;

    @NotNull
    private Integer initialCatching;

    @NotNull
    private Integer maxStamina;

    @NotNull
    private Integer maxDribble;

    @NotNull
    private Integer maxShoot;

    @NotNull
    private Integer maxPass;

    @NotNull
    private Integer maxTackle;

    @NotNull
    private Integer maxBlock;

    @NotNull
    private Integer maxIntercept;

    @NotNull
    private Integer maxSpeed;

    @NotNull
    private Integer maxPower;

    @NotNull
    private Integer maxTechnique;

    @NotNull
    private Integer maxPunching;

    @NotNull
    private Integer maxCatching;

    @NotNull
    private Integer maxPlusDribble;

    @NotNull
    private Integer maxPlusShoot;

    @NotNull
    private Integer maxPlusPass;

    @NotNull
    private Integer maxPlusTackle;

    @NotNull
    private Integer maxPlusBlock;

    @NotNull
    private Integer maxPlusIntercept;

    @NotNull
    private Integer maxPlusSpeed;

    @NotNull
    private Integer maxPlusPower;

    @NotNull
    private Integer maxPlusTechnique;

    @NotNull
    private Integer maxPlusPunching;

    @NotNull
    private Integer maxPlusCatching;

    @NotNull
    private Integer highBallBonus;

    @NotNull
    private Integer lowBallBonus;

    @NotNull
    private Integer isDropOnly;

    @NotNull
    private Integer sellCoinGroupNum;

    private Integer sellMedalId;

    @NotNull
    private Integer characterBookId;

    @NotNull
    private Integer bookNo;

    @NotNull
    private Integer isShowBook;

    @NotNull
    private Integer levelGroupId;

    private Integer startAt;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getProperPositionGk() {
        return properPositionGk;
    }

    public void setProperPositionGk(Integer properPositionGk) {
        this.properPositionGk = properPositionGk;
    }

    public Integer getProperPositionFw() {
        return properPositionFw;
    }

    public void setProperPositionFw(Integer properPositionFw) {
        this.properPositionFw = properPositionFw;
    }

    public Integer getProperPositionOmf() {
        return properPositionOmf;
    }

    public void setProperPositionOmf(Integer properPositionOmf) {
        this.properPositionOmf = properPositionOmf;
    }

    public Integer getProperPositionDmf() {
        return properPositionDmf;
    }

    public void setProperPositionDmf(Integer properPositionDmf) {
        this.properPositionDmf = properPositionDmf;
    }

    public Integer getProperPositionDf() {
        return properPositionDf;
    }

    public void setProperPositionDf(Integer properPositionDf) {
        this.properPositionDf = properPositionDf;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
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

    public Integer getPlayableAssetsId() {
        return playableAssetsId;
    }

    public void setPlayableAssetsId(Integer playableAssetsId) {
        this.playableAssetsId = playableAssetsId;
    }

    public Integer getTeamEffectId() {
        return teamEffectId;
    }

    public void setTeamEffectId(Integer teamEffectId) {
        this.teamEffectId = teamEffectId;
    }

    public Integer getTriggerEffectId() {
        return triggerEffectId;
    }

    public void setTriggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public Integer getMaxActionSlot() {
        return maxActionSlot;
    }

    public void setMaxActionSlot(Integer maxActionSlot) {
        this.maxActionSlot = maxActionSlot;
    }

    public Integer getInitialActionId1() {
        return initialActionId1;
    }

    public void setInitialActionId1(Integer initialActionId1) {
        this.initialActionId1 = initialActionId1;
    }

    public Integer getInitialActionId2() {
        return initialActionId2;
    }

    public void setInitialActionId2(Integer initialActionId2) {
        this.initialActionId2 = initialActionId2;
    }

    public Integer getInitialActionId3() {
        return initialActionId3;
    }

    public void setInitialActionId3(Integer initialActionId3) {
        this.initialActionId3 = initialActionId3;
    }

    public Integer getInitialActionId4() {
        return initialActionId4;
    }

    public void setInitialActionId4(Integer initialActionId4) {
        this.initialActionId4 = initialActionId4;
    }

    public Integer getInitialActionId5() {
        return initialActionId5;
    }

    public void setInitialActionId5(Integer initialActionId5) {
        this.initialActionId5 = initialActionId5;
    }

    public Integer getInitialStamina() {
        return initialStamina;
    }

    public void setInitialStamina(Integer initialStamina) {
        this.initialStamina = initialStamina;
    }

    public Integer getInitialDribble() {
        return initialDribble;
    }

    public void setInitialDribble(Integer initialDribble) {
        this.initialDribble = initialDribble;
    }

    public Integer getInitialShoot() {
        return initialShoot;
    }

    public void setInitialShoot(Integer initialShoot) {
        this.initialShoot = initialShoot;
    }

    public Integer getInitialPass() {
        return initialPass;
    }

    public void setInitialPass(Integer initialPass) {
        this.initialPass = initialPass;
    }

    public Integer getInitialTackle() {
        return initialTackle;
    }

    public void setInitialTackle(Integer initialTackle) {
        this.initialTackle = initialTackle;
    }

    public Integer getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(Integer initialBlock) {
        this.initialBlock = initialBlock;
    }

    public Integer getInitialIntercept() {
        return initialIntercept;
    }

    public void setInitialIntercept(Integer initialIntercept) {
        this.initialIntercept = initialIntercept;
    }

    public Integer getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(Integer initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public Integer getInitialPower() {
        return initialPower;
    }

    public void setInitialPower(Integer initialPower) {
        this.initialPower = initialPower;
    }

    public Integer getInitialTechnique() {
        return initialTechnique;
    }

    public void setInitialTechnique(Integer initialTechnique) {
        this.initialTechnique = initialTechnique;
    }

    public Integer getInitialPunching() {
        return initialPunching;
    }

    public void setInitialPunching(Integer initialPunching) {
        this.initialPunching = initialPunching;
    }

    public Integer getInitialCatching() {
        return initialCatching;
    }

    public void setInitialCatching(Integer initialCatching) {
        this.initialCatching = initialCatching;
    }

    public Integer getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Integer getMaxDribble() {
        return maxDribble;
    }

    public void setMaxDribble(Integer maxDribble) {
        this.maxDribble = maxDribble;
    }

    public Integer getMaxShoot() {
        return maxShoot;
    }

    public void setMaxShoot(Integer maxShoot) {
        this.maxShoot = maxShoot;
    }

    public Integer getMaxPass() {
        return maxPass;
    }

    public void setMaxPass(Integer maxPass) {
        this.maxPass = maxPass;
    }

    public Integer getMaxTackle() {
        return maxTackle;
    }

    public void setMaxTackle(Integer maxTackle) {
        this.maxTackle = maxTackle;
    }

    public Integer getMaxBlock() {
        return maxBlock;
    }

    public void setMaxBlock(Integer maxBlock) {
        this.maxBlock = maxBlock;
    }

    public Integer getMaxIntercept() {
        return maxIntercept;
    }

    public void setMaxIntercept(Integer maxIntercept) {
        this.maxIntercept = maxIntercept;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getMaxTechnique() {
        return maxTechnique;
    }

    public void setMaxTechnique(Integer maxTechnique) {
        this.maxTechnique = maxTechnique;
    }

    public Integer getMaxPunching() {
        return maxPunching;
    }

    public void setMaxPunching(Integer maxPunching) {
        this.maxPunching = maxPunching;
    }

    public Integer getMaxCatching() {
        return maxCatching;
    }

    public void setMaxCatching(Integer maxCatching) {
        this.maxCatching = maxCatching;
    }

    public Integer getMaxPlusDribble() {
        return maxPlusDribble;
    }

    public void setMaxPlusDribble(Integer maxPlusDribble) {
        this.maxPlusDribble = maxPlusDribble;
    }

    public Integer getMaxPlusShoot() {
        return maxPlusShoot;
    }

    public void setMaxPlusShoot(Integer maxPlusShoot) {
        this.maxPlusShoot = maxPlusShoot;
    }

    public Integer getMaxPlusPass() {
        return maxPlusPass;
    }

    public void setMaxPlusPass(Integer maxPlusPass) {
        this.maxPlusPass = maxPlusPass;
    }

    public Integer getMaxPlusTackle() {
        return maxPlusTackle;
    }

    public void setMaxPlusTackle(Integer maxPlusTackle) {
        this.maxPlusTackle = maxPlusTackle;
    }

    public Integer getMaxPlusBlock() {
        return maxPlusBlock;
    }

    public void setMaxPlusBlock(Integer maxPlusBlock) {
        this.maxPlusBlock = maxPlusBlock;
    }

    public Integer getMaxPlusIntercept() {
        return maxPlusIntercept;
    }

    public void setMaxPlusIntercept(Integer maxPlusIntercept) {
        this.maxPlusIntercept = maxPlusIntercept;
    }

    public Integer getMaxPlusSpeed() {
        return maxPlusSpeed;
    }

    public void setMaxPlusSpeed(Integer maxPlusSpeed) {
        this.maxPlusSpeed = maxPlusSpeed;
    }

    public Integer getMaxPlusPower() {
        return maxPlusPower;
    }

    public void setMaxPlusPower(Integer maxPlusPower) {
        this.maxPlusPower = maxPlusPower;
    }

    public Integer getMaxPlusTechnique() {
        return maxPlusTechnique;
    }

    public void setMaxPlusTechnique(Integer maxPlusTechnique) {
        this.maxPlusTechnique = maxPlusTechnique;
    }

    public Integer getMaxPlusPunching() {
        return maxPlusPunching;
    }

    public void setMaxPlusPunching(Integer maxPlusPunching) {
        this.maxPlusPunching = maxPlusPunching;
    }

    public Integer getMaxPlusCatching() {
        return maxPlusCatching;
    }

    public void setMaxPlusCatching(Integer maxPlusCatching) {
        this.maxPlusCatching = maxPlusCatching;
    }

    public Integer getHighBallBonus() {
        return highBallBonus;
    }

    public void setHighBallBonus(Integer highBallBonus) {
        this.highBallBonus = highBallBonus;
    }

    public Integer getLowBallBonus() {
        return lowBallBonus;
    }

    public void setLowBallBonus(Integer lowBallBonus) {
        this.lowBallBonus = lowBallBonus;
    }

    public Integer getIsDropOnly() {
        return isDropOnly;
    }

    public void setIsDropOnly(Integer isDropOnly) {
        this.isDropOnly = isDropOnly;
    }

    public Integer getSellCoinGroupNum() {
        return sellCoinGroupNum;
    }

    public void setSellCoinGroupNum(Integer sellCoinGroupNum) {
        this.sellCoinGroupNum = sellCoinGroupNum;
    }

    public Integer getSellMedalId() {
        return sellMedalId;
    }

    public void setSellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public Integer getCharacterBookId() {
        return characterBookId;
    }

    public void setCharacterBookId(Integer characterBookId) {
        this.characterBookId = characterBookId;
    }

    public Integer getBookNo() {
        return bookNo;
    }

    public void setBookNo(Integer bookNo) {
        this.bookNo = bookNo;
    }

    public Integer getIsShowBook() {
        return isShowBook;
    }

    public void setIsShowBook(Integer isShowBook) {
        this.isShowBook = isShowBook;
    }

    public Integer getLevelGroupId() {
        return levelGroupId;
    }

    public void setLevelGroupId(Integer levelGroupId) {
        this.levelGroupId = levelGroupId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long mModelCardId) {
        this.idId = mModelCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MPlayableCardDTO mPlayableCardDTO = (MPlayableCardDTO) o;
        if (mPlayableCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mPlayableCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MPlayableCardDTO{" +
            "id=" + getId() +
            ", modelId=" + getModelId() +
            ", properPositionGk=" + getProperPositionGk() +
            ", properPositionFw=" + getProperPositionFw() +
            ", properPositionOmf=" + getProperPositionOmf() +
            ", properPositionDmf=" + getProperPositionDmf() +
            ", properPositionDf=" + getProperPositionDf() +
            ", characterId=" + getCharacterId() +
            ", nickName='" + getNickName() + "'" +
            ", teamId=" + getTeamId() +
            ", nationalityId=" + getNationalityId() +
            ", rarity=" + getRarity() +
            ", attribute=" + getAttribute() +
            ", thumbnailAssetsId=" + getThumbnailAssetsId() +
            ", cardIllustAssetsId=" + getCardIllustAssetsId() +
            ", playableAssetsId=" + getPlayableAssetsId() +
            ", teamEffectId=" + getTeamEffectId() +
            ", triggerEffectId=" + getTriggerEffectId() +
            ", maxActionSlot=" + getMaxActionSlot() +
            ", initialActionId1=" + getInitialActionId1() +
            ", initialActionId2=" + getInitialActionId2() +
            ", initialActionId3=" + getInitialActionId3() +
            ", initialActionId4=" + getInitialActionId4() +
            ", initialActionId5=" + getInitialActionId5() +
            ", initialStamina=" + getInitialStamina() +
            ", initialDribble=" + getInitialDribble() +
            ", initialShoot=" + getInitialShoot() +
            ", initialPass=" + getInitialPass() +
            ", initialTackle=" + getInitialTackle() +
            ", initialBlock=" + getInitialBlock() +
            ", initialIntercept=" + getInitialIntercept() +
            ", initialSpeed=" + getInitialSpeed() +
            ", initialPower=" + getInitialPower() +
            ", initialTechnique=" + getInitialTechnique() +
            ", initialPunching=" + getInitialPunching() +
            ", initialCatching=" + getInitialCatching() +
            ", maxStamina=" + getMaxStamina() +
            ", maxDribble=" + getMaxDribble() +
            ", maxShoot=" + getMaxShoot() +
            ", maxPass=" + getMaxPass() +
            ", maxTackle=" + getMaxTackle() +
            ", maxBlock=" + getMaxBlock() +
            ", maxIntercept=" + getMaxIntercept() +
            ", maxSpeed=" + getMaxSpeed() +
            ", maxPower=" + getMaxPower() +
            ", maxTechnique=" + getMaxTechnique() +
            ", maxPunching=" + getMaxPunching() +
            ", maxCatching=" + getMaxCatching() +
            ", maxPlusDribble=" + getMaxPlusDribble() +
            ", maxPlusShoot=" + getMaxPlusShoot() +
            ", maxPlusPass=" + getMaxPlusPass() +
            ", maxPlusTackle=" + getMaxPlusTackle() +
            ", maxPlusBlock=" + getMaxPlusBlock() +
            ", maxPlusIntercept=" + getMaxPlusIntercept() +
            ", maxPlusSpeed=" + getMaxPlusSpeed() +
            ", maxPlusPower=" + getMaxPlusPower() +
            ", maxPlusTechnique=" + getMaxPlusTechnique() +
            ", maxPlusPunching=" + getMaxPlusPunching() +
            ", maxPlusCatching=" + getMaxPlusCatching() +
            ", highBallBonus=" + getHighBallBonus() +
            ", lowBallBonus=" + getLowBallBonus() +
            ", isDropOnly=" + getIsDropOnly() +
            ", sellCoinGroupNum=" + getSellCoinGroupNum() +
            ", sellMedalId=" + getSellMedalId() +
            ", characterBookId=" + getCharacterBookId() +
            ", bookNo=" + getBookNo() +
            ", isShowBook=" + getIsShowBook() +
            ", levelGroupId=" + getLevelGroupId() +
            ", startAt=" + getStartAt() +
            ", id=" + getIdId() +
            "}";
    }
}
