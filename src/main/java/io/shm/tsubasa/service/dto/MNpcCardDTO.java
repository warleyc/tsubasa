package io.shm.tsubasa.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.shm.tsubasa.domain.MNpcCard} entity.
 */
public class MNpcCardDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer characterId;

    
    @Lob
    private String shortName;

    
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
    private Integer modelId;

    @NotNull
    private Integer level;

    @NotNull
    private Integer thumbnailAssetsId;

    @NotNull
    private Integer cardIllustAssetsId;

    @NotNull
    private Integer playableAssetsId;

    private Integer teamEffectId;

    @NotNull
    private Integer teamEffectLevel;

    private Integer triggerEffectId;

    private Integer action1Id;

    private Integer action1Level;

    private Integer action2Id;

    private Integer action2Level;

    private Integer action3Id;

    private Integer action3Level;

    private Integer action4Id;

    private Integer action4Level;

    private Integer action5Id;

    private Integer action5Level;

    @NotNull
    private Integer stamina;

    @NotNull
    private Integer dribble;

    @NotNull
    private Integer shoot;

    @NotNull
    private Integer ballPass;

    @NotNull
    private Integer tackle;

    @NotNull
    private Integer block;

    @NotNull
    private Integer intercept;

    @NotNull
    private Integer speed;

    @NotNull
    private Integer power;

    @NotNull
    private Integer technique;

    @NotNull
    private Integer punching;

    @NotNull
    private Integer catching;

    @NotNull
    private Integer highBallBonus;

    @NotNull
    private Integer lowBallBonus;

    @NotNull
    private Integer personalityId;

    @NotNull
    private Integer uniformNo;

    @NotNull
    private Integer levelGroupId;


    private Long mcharacterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getTeamEffectLevel() {
        return teamEffectLevel;
    }

    public void setTeamEffectLevel(Integer teamEffectLevel) {
        this.teamEffectLevel = teamEffectLevel;
    }

    public Integer getTriggerEffectId() {
        return triggerEffectId;
    }

    public void setTriggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public Integer getAction1Id() {
        return action1Id;
    }

    public void setAction1Id(Integer action1Id) {
        this.action1Id = action1Id;
    }

    public Integer getAction1Level() {
        return action1Level;
    }

    public void setAction1Level(Integer action1Level) {
        this.action1Level = action1Level;
    }

    public Integer getAction2Id() {
        return action2Id;
    }

    public void setAction2Id(Integer action2Id) {
        this.action2Id = action2Id;
    }

    public Integer getAction2Level() {
        return action2Level;
    }

    public void setAction2Level(Integer action2Level) {
        this.action2Level = action2Level;
    }

    public Integer getAction3Id() {
        return action3Id;
    }

    public void setAction3Id(Integer action3Id) {
        this.action3Id = action3Id;
    }

    public Integer getAction3Level() {
        return action3Level;
    }

    public void setAction3Level(Integer action3Level) {
        this.action3Level = action3Level;
    }

    public Integer getAction4Id() {
        return action4Id;
    }

    public void setAction4Id(Integer action4Id) {
        this.action4Id = action4Id;
    }

    public Integer getAction4Level() {
        return action4Level;
    }

    public void setAction4Level(Integer action4Level) {
        this.action4Level = action4Level;
    }

    public Integer getAction5Id() {
        return action5Id;
    }

    public void setAction5Id(Integer action5Id) {
        this.action5Id = action5Id;
    }

    public Integer getAction5Level() {
        return action5Level;
    }

    public void setAction5Level(Integer action5Level) {
        this.action5Level = action5Level;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public Integer getDribble() {
        return dribble;
    }

    public void setDribble(Integer dribble) {
        this.dribble = dribble;
    }

    public Integer getShoot() {
        return shoot;
    }

    public void setShoot(Integer shoot) {
        this.shoot = shoot;
    }

    public Integer getBallPass() {
        return ballPass;
    }

    public void setBallPass(Integer ballPass) {
        this.ballPass = ballPass;
    }

    public Integer getTackle() {
        return tackle;
    }

    public void setTackle(Integer tackle) {
        this.tackle = tackle;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getIntercept() {
        return intercept;
    }

    public void setIntercept(Integer intercept) {
        this.intercept = intercept;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getTechnique() {
        return technique;
    }

    public void setTechnique(Integer technique) {
        this.technique = technique;
    }

    public Integer getPunching() {
        return punching;
    }

    public void setPunching(Integer punching) {
        this.punching = punching;
    }

    public Integer getCatching() {
        return catching;
    }

    public void setCatching(Integer catching) {
        this.catching = catching;
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

    public Integer getPersonalityId() {
        return personalityId;
    }

    public void setPersonalityId(Integer personalityId) {
        this.personalityId = personalityId;
    }

    public Integer getUniformNo() {
        return uniformNo;
    }

    public void setUniformNo(Integer uniformNo) {
        this.uniformNo = uniformNo;
    }

    public Integer getLevelGroupId() {
        return levelGroupId;
    }

    public void setLevelGroupId(Integer levelGroupId) {
        this.levelGroupId = levelGroupId;
    }

    public Long getMcharacterId() {
        return mcharacterId;
    }

    public void setMcharacterId(Long mCharacterId) {
        this.mcharacterId = mCharacterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MNpcCardDTO mNpcCardDTO = (MNpcCardDTO) o;
        if (mNpcCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mNpcCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MNpcCardDTO{" +
            "id=" + getId() +
            ", characterId=" + getCharacterId() +
            ", shortName='" + getShortName() + "'" +
            ", nickName='" + getNickName() + "'" +
            ", teamId=" + getTeamId() +
            ", nationalityId=" + getNationalityId() +
            ", rarity=" + getRarity() +
            ", attribute=" + getAttribute() +
            ", modelId=" + getModelId() +
            ", level=" + getLevel() +
            ", thumbnailAssetsId=" + getThumbnailAssetsId() +
            ", cardIllustAssetsId=" + getCardIllustAssetsId() +
            ", playableAssetsId=" + getPlayableAssetsId() +
            ", teamEffectId=" + getTeamEffectId() +
            ", teamEffectLevel=" + getTeamEffectLevel() +
            ", triggerEffectId=" + getTriggerEffectId() +
            ", action1Id=" + getAction1Id() +
            ", action1Level=" + getAction1Level() +
            ", action2Id=" + getAction2Id() +
            ", action2Level=" + getAction2Level() +
            ", action3Id=" + getAction3Id() +
            ", action3Level=" + getAction3Level() +
            ", action4Id=" + getAction4Id() +
            ", action4Level=" + getAction4Level() +
            ", action5Id=" + getAction5Id() +
            ", action5Level=" + getAction5Level() +
            ", stamina=" + getStamina() +
            ", dribble=" + getDribble() +
            ", shoot=" + getShoot() +
            ", ballPass=" + getBallPass() +
            ", tackle=" + getTackle() +
            ", block=" + getBlock() +
            ", intercept=" + getIntercept() +
            ", speed=" + getSpeed() +
            ", power=" + getPower() +
            ", technique=" + getTechnique() +
            ", punching=" + getPunching() +
            ", catching=" + getCatching() +
            ", highBallBonus=" + getHighBallBonus() +
            ", lowBallBonus=" + getLowBallBonus() +
            ", personalityId=" + getPersonalityId() +
            ", uniformNo=" + getUniformNo() +
            ", levelGroupId=" + getLevelGroupId() +
            ", mcharacter=" + getMcharacterId() +
            "}";
    }
}
