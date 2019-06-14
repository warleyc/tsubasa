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
 * Criteria class for the {@link io.shm.tsubasa.domain.MNpcCard} entity. This class is used
 * in {@link io.shm.tsubasa.web.rest.MNpcCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /m-npc-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MNpcCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter characterId;

    private IntegerFilter teamId;

    private IntegerFilter nationalityId;

    private IntegerFilter rarity;

    private IntegerFilter attribute;

    private IntegerFilter modelId;

    private IntegerFilter level;

    private IntegerFilter thumbnailAssetsId;

    private IntegerFilter cardIllustAssetsId;

    private IntegerFilter playableAssetsId;

    private IntegerFilter teamEffectId;

    private IntegerFilter teamEffectLevel;

    private IntegerFilter triggerEffectId;

    private IntegerFilter action1Id;

    private IntegerFilter action1Level;

    private IntegerFilter action2Id;

    private IntegerFilter action2Level;

    private IntegerFilter action3Id;

    private IntegerFilter action3Level;

    private IntegerFilter action4Id;

    private IntegerFilter action4Level;

    private IntegerFilter action5Id;

    private IntegerFilter action5Level;

    private IntegerFilter stamina;

    private IntegerFilter dribble;

    private IntegerFilter shoot;

    private IntegerFilter ballPass;

    private IntegerFilter tackle;

    private IntegerFilter block;

    private IntegerFilter intercept;

    private IntegerFilter speed;

    private IntegerFilter power;

    private IntegerFilter technique;

    private IntegerFilter punching;

    private IntegerFilter catching;

    private IntegerFilter highBallBonus;

    private IntegerFilter lowBallBonus;

    private IntegerFilter personalityId;

    private IntegerFilter uniformNo;

    private IntegerFilter levelGroupId;

    private LongFilter idId;

    public MNpcCardCriteria(){
    }

    public MNpcCardCriteria(MNpcCardCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.characterId = other.characterId == null ? null : other.characterId.copy();
        this.teamId = other.teamId == null ? null : other.teamId.copy();
        this.nationalityId = other.nationalityId == null ? null : other.nationalityId.copy();
        this.rarity = other.rarity == null ? null : other.rarity.copy();
        this.attribute = other.attribute == null ? null : other.attribute.copy();
        this.modelId = other.modelId == null ? null : other.modelId.copy();
        this.level = other.level == null ? null : other.level.copy();
        this.thumbnailAssetsId = other.thumbnailAssetsId == null ? null : other.thumbnailAssetsId.copy();
        this.cardIllustAssetsId = other.cardIllustAssetsId == null ? null : other.cardIllustAssetsId.copy();
        this.playableAssetsId = other.playableAssetsId == null ? null : other.playableAssetsId.copy();
        this.teamEffectId = other.teamEffectId == null ? null : other.teamEffectId.copy();
        this.teamEffectLevel = other.teamEffectLevel == null ? null : other.teamEffectLevel.copy();
        this.triggerEffectId = other.triggerEffectId == null ? null : other.triggerEffectId.copy();
        this.action1Id = other.action1Id == null ? null : other.action1Id.copy();
        this.action1Level = other.action1Level == null ? null : other.action1Level.copy();
        this.action2Id = other.action2Id == null ? null : other.action2Id.copy();
        this.action2Level = other.action2Level == null ? null : other.action2Level.copy();
        this.action3Id = other.action3Id == null ? null : other.action3Id.copy();
        this.action3Level = other.action3Level == null ? null : other.action3Level.copy();
        this.action4Id = other.action4Id == null ? null : other.action4Id.copy();
        this.action4Level = other.action4Level == null ? null : other.action4Level.copy();
        this.action5Id = other.action5Id == null ? null : other.action5Id.copy();
        this.action5Level = other.action5Level == null ? null : other.action5Level.copy();
        this.stamina = other.stamina == null ? null : other.stamina.copy();
        this.dribble = other.dribble == null ? null : other.dribble.copy();
        this.shoot = other.shoot == null ? null : other.shoot.copy();
        this.ballPass = other.ballPass == null ? null : other.ballPass.copy();
        this.tackle = other.tackle == null ? null : other.tackle.copy();
        this.block = other.block == null ? null : other.block.copy();
        this.intercept = other.intercept == null ? null : other.intercept.copy();
        this.speed = other.speed == null ? null : other.speed.copy();
        this.power = other.power == null ? null : other.power.copy();
        this.technique = other.technique == null ? null : other.technique.copy();
        this.punching = other.punching == null ? null : other.punching.copy();
        this.catching = other.catching == null ? null : other.catching.copy();
        this.highBallBonus = other.highBallBonus == null ? null : other.highBallBonus.copy();
        this.lowBallBonus = other.lowBallBonus == null ? null : other.lowBallBonus.copy();
        this.personalityId = other.personalityId == null ? null : other.personalityId.copy();
        this.uniformNo = other.uniformNo == null ? null : other.uniformNo.copy();
        this.levelGroupId = other.levelGroupId == null ? null : other.levelGroupId.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public MNpcCardCriteria copy() {
        return new MNpcCardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public IntegerFilter getModelId() {
        return modelId;
    }

    public void setModelId(IntegerFilter modelId) {
        this.modelId = modelId;
    }

    public IntegerFilter getLevel() {
        return level;
    }

    public void setLevel(IntegerFilter level) {
        this.level = level;
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

    public IntegerFilter getTeamEffectLevel() {
        return teamEffectLevel;
    }

    public void setTeamEffectLevel(IntegerFilter teamEffectLevel) {
        this.teamEffectLevel = teamEffectLevel;
    }

    public IntegerFilter getTriggerEffectId() {
        return triggerEffectId;
    }

    public void setTriggerEffectId(IntegerFilter triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public IntegerFilter getAction1Id() {
        return action1Id;
    }

    public void setAction1Id(IntegerFilter action1Id) {
        this.action1Id = action1Id;
    }

    public IntegerFilter getAction1Level() {
        return action1Level;
    }

    public void setAction1Level(IntegerFilter action1Level) {
        this.action1Level = action1Level;
    }

    public IntegerFilter getAction2Id() {
        return action2Id;
    }

    public void setAction2Id(IntegerFilter action2Id) {
        this.action2Id = action2Id;
    }

    public IntegerFilter getAction2Level() {
        return action2Level;
    }

    public void setAction2Level(IntegerFilter action2Level) {
        this.action2Level = action2Level;
    }

    public IntegerFilter getAction3Id() {
        return action3Id;
    }

    public void setAction3Id(IntegerFilter action3Id) {
        this.action3Id = action3Id;
    }

    public IntegerFilter getAction3Level() {
        return action3Level;
    }

    public void setAction3Level(IntegerFilter action3Level) {
        this.action3Level = action3Level;
    }

    public IntegerFilter getAction4Id() {
        return action4Id;
    }

    public void setAction4Id(IntegerFilter action4Id) {
        this.action4Id = action4Id;
    }

    public IntegerFilter getAction4Level() {
        return action4Level;
    }

    public void setAction4Level(IntegerFilter action4Level) {
        this.action4Level = action4Level;
    }

    public IntegerFilter getAction5Id() {
        return action5Id;
    }

    public void setAction5Id(IntegerFilter action5Id) {
        this.action5Id = action5Id;
    }

    public IntegerFilter getAction5Level() {
        return action5Level;
    }

    public void setAction5Level(IntegerFilter action5Level) {
        this.action5Level = action5Level;
    }

    public IntegerFilter getStamina() {
        return stamina;
    }

    public void setStamina(IntegerFilter stamina) {
        this.stamina = stamina;
    }

    public IntegerFilter getDribble() {
        return dribble;
    }

    public void setDribble(IntegerFilter dribble) {
        this.dribble = dribble;
    }

    public IntegerFilter getShoot() {
        return shoot;
    }

    public void setShoot(IntegerFilter shoot) {
        this.shoot = shoot;
    }

    public IntegerFilter getBallPass() {
        return ballPass;
    }

    public void setBallPass(IntegerFilter ballPass) {
        this.ballPass = ballPass;
    }

    public IntegerFilter getTackle() {
        return tackle;
    }

    public void setTackle(IntegerFilter tackle) {
        this.tackle = tackle;
    }

    public IntegerFilter getBlock() {
        return block;
    }

    public void setBlock(IntegerFilter block) {
        this.block = block;
    }

    public IntegerFilter getIntercept() {
        return intercept;
    }

    public void setIntercept(IntegerFilter intercept) {
        this.intercept = intercept;
    }

    public IntegerFilter getSpeed() {
        return speed;
    }

    public void setSpeed(IntegerFilter speed) {
        this.speed = speed;
    }

    public IntegerFilter getPower() {
        return power;
    }

    public void setPower(IntegerFilter power) {
        this.power = power;
    }

    public IntegerFilter getTechnique() {
        return technique;
    }

    public void setTechnique(IntegerFilter technique) {
        this.technique = technique;
    }

    public IntegerFilter getPunching() {
        return punching;
    }

    public void setPunching(IntegerFilter punching) {
        this.punching = punching;
    }

    public IntegerFilter getCatching() {
        return catching;
    }

    public void setCatching(IntegerFilter catching) {
        this.catching = catching;
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

    public IntegerFilter getPersonalityId() {
        return personalityId;
    }

    public void setPersonalityId(IntegerFilter personalityId) {
        this.personalityId = personalityId;
    }

    public IntegerFilter getUniformNo() {
        return uniformNo;
    }

    public void setUniformNo(IntegerFilter uniformNo) {
        this.uniformNo = uniformNo;
    }

    public IntegerFilter getLevelGroupId() {
        return levelGroupId;
    }

    public void setLevelGroupId(IntegerFilter levelGroupId) {
        this.levelGroupId = levelGroupId;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MNpcCardCriteria that = (MNpcCardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(characterId, that.characterId) &&
            Objects.equals(teamId, that.teamId) &&
            Objects.equals(nationalityId, that.nationalityId) &&
            Objects.equals(rarity, that.rarity) &&
            Objects.equals(attribute, that.attribute) &&
            Objects.equals(modelId, that.modelId) &&
            Objects.equals(level, that.level) &&
            Objects.equals(thumbnailAssetsId, that.thumbnailAssetsId) &&
            Objects.equals(cardIllustAssetsId, that.cardIllustAssetsId) &&
            Objects.equals(playableAssetsId, that.playableAssetsId) &&
            Objects.equals(teamEffectId, that.teamEffectId) &&
            Objects.equals(teamEffectLevel, that.teamEffectLevel) &&
            Objects.equals(triggerEffectId, that.triggerEffectId) &&
            Objects.equals(action1Id, that.action1Id) &&
            Objects.equals(action1Level, that.action1Level) &&
            Objects.equals(action2Id, that.action2Id) &&
            Objects.equals(action2Level, that.action2Level) &&
            Objects.equals(action3Id, that.action3Id) &&
            Objects.equals(action3Level, that.action3Level) &&
            Objects.equals(action4Id, that.action4Id) &&
            Objects.equals(action4Level, that.action4Level) &&
            Objects.equals(action5Id, that.action5Id) &&
            Objects.equals(action5Level, that.action5Level) &&
            Objects.equals(stamina, that.stamina) &&
            Objects.equals(dribble, that.dribble) &&
            Objects.equals(shoot, that.shoot) &&
            Objects.equals(ballPass, that.ballPass) &&
            Objects.equals(tackle, that.tackle) &&
            Objects.equals(block, that.block) &&
            Objects.equals(intercept, that.intercept) &&
            Objects.equals(speed, that.speed) &&
            Objects.equals(power, that.power) &&
            Objects.equals(technique, that.technique) &&
            Objects.equals(punching, that.punching) &&
            Objects.equals(catching, that.catching) &&
            Objects.equals(highBallBonus, that.highBallBonus) &&
            Objects.equals(lowBallBonus, that.lowBallBonus) &&
            Objects.equals(personalityId, that.personalityId) &&
            Objects.equals(uniformNo, that.uniformNo) &&
            Objects.equals(levelGroupId, that.levelGroupId) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        characterId,
        teamId,
        nationalityId,
        rarity,
        attribute,
        modelId,
        level,
        thumbnailAssetsId,
        cardIllustAssetsId,
        playableAssetsId,
        teamEffectId,
        teamEffectLevel,
        triggerEffectId,
        action1Id,
        action1Level,
        action2Id,
        action2Level,
        action3Id,
        action3Level,
        action4Id,
        action4Level,
        action5Id,
        action5Level,
        stamina,
        dribble,
        shoot,
        ballPass,
        tackle,
        block,
        intercept,
        speed,
        power,
        technique,
        punching,
        catching,
        highBallBonus,
        lowBallBonus,
        personalityId,
        uniformNo,
        levelGroupId,
        idId
        );
    }

    @Override
    public String toString() {
        return "MNpcCardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (characterId != null ? "characterId=" + characterId + ", " : "") +
                (teamId != null ? "teamId=" + teamId + ", " : "") +
                (nationalityId != null ? "nationalityId=" + nationalityId + ", " : "") +
                (rarity != null ? "rarity=" + rarity + ", " : "") +
                (attribute != null ? "attribute=" + attribute + ", " : "") +
                (modelId != null ? "modelId=" + modelId + ", " : "") +
                (level != null ? "level=" + level + ", " : "") +
                (thumbnailAssetsId != null ? "thumbnailAssetsId=" + thumbnailAssetsId + ", " : "") +
                (cardIllustAssetsId != null ? "cardIllustAssetsId=" + cardIllustAssetsId + ", " : "") +
                (playableAssetsId != null ? "playableAssetsId=" + playableAssetsId + ", " : "") +
                (teamEffectId != null ? "teamEffectId=" + teamEffectId + ", " : "") +
                (teamEffectLevel != null ? "teamEffectLevel=" + teamEffectLevel + ", " : "") +
                (triggerEffectId != null ? "triggerEffectId=" + triggerEffectId + ", " : "") +
                (action1Id != null ? "action1Id=" + action1Id + ", " : "") +
                (action1Level != null ? "action1Level=" + action1Level + ", " : "") +
                (action2Id != null ? "action2Id=" + action2Id + ", " : "") +
                (action2Level != null ? "action2Level=" + action2Level + ", " : "") +
                (action3Id != null ? "action3Id=" + action3Id + ", " : "") +
                (action3Level != null ? "action3Level=" + action3Level + ", " : "") +
                (action4Id != null ? "action4Id=" + action4Id + ", " : "") +
                (action4Level != null ? "action4Level=" + action4Level + ", " : "") +
                (action5Id != null ? "action5Id=" + action5Id + ", " : "") +
                (action5Level != null ? "action5Level=" + action5Level + ", " : "") +
                (stamina != null ? "stamina=" + stamina + ", " : "") +
                (dribble != null ? "dribble=" + dribble + ", " : "") +
                (shoot != null ? "shoot=" + shoot + ", " : "") +
                (ballPass != null ? "ballPass=" + ballPass + ", " : "") +
                (tackle != null ? "tackle=" + tackle + ", " : "") +
                (block != null ? "block=" + block + ", " : "") +
                (intercept != null ? "intercept=" + intercept + ", " : "") +
                (speed != null ? "speed=" + speed + ", " : "") +
                (power != null ? "power=" + power + ", " : "") +
                (technique != null ? "technique=" + technique + ", " : "") +
                (punching != null ? "punching=" + punching + ", " : "") +
                (catching != null ? "catching=" + catching + ", " : "") +
                (highBallBonus != null ? "highBallBonus=" + highBallBonus + ", " : "") +
                (lowBallBonus != null ? "lowBallBonus=" + lowBallBonus + ", " : "") +
                (personalityId != null ? "personalityId=" + personalityId + ", " : "") +
                (uniformNo != null ? "uniformNo=" + uniformNo + ", " : "") +
                (levelGroupId != null ? "levelGroupId=" + levelGroupId + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
