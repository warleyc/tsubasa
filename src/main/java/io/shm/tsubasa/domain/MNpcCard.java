package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MNpcCard.
 */
@Entity
@Table(name = "m_npc_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MNpcCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private Integer characterId;

    
    @Lob
    @Column(name = "short_name", nullable = false)
    private String shortName;

    
    @Lob
    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @NotNull
    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    @NotNull
    @Column(name = "nationality_id", nullable = false)
    private Integer nationalityId;

    @NotNull
    @Column(name = "rarity", nullable = false)
    private Integer rarity;

    @NotNull
    @Column(name = "attribute", nullable = false)
    private Integer attribute;

    @NotNull
    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "thumbnail_assets_id", nullable = false)
    private Integer thumbnailAssetsId;

    @NotNull
    @Column(name = "card_illust_assets_id", nullable = false)
    private Integer cardIllustAssetsId;

    @NotNull
    @Column(name = "playable_assets_id", nullable = false)
    private Integer playableAssetsId;

    @Column(name = "team_effect_id")
    private Integer teamEffectId;

    @NotNull
    @Column(name = "team_effect_level", nullable = false)
    private Integer teamEffectLevel;

    @Column(name = "trigger_effect_id")
    private Integer triggerEffectId;

    @Column(name = "action_1_id")
    private Integer action1Id;

    @Column(name = "action_1_level")
    private Integer action1Level;

    @Column(name = "action_2_id")
    private Integer action2Id;

    @Column(name = "action_2_level")
    private Integer action2Level;

    @Column(name = "action_3_id")
    private Integer action3Id;

    @Column(name = "action_3_level")
    private Integer action3Level;

    @Column(name = "action_4_id")
    private Integer action4Id;

    @Column(name = "action_4_level")
    private Integer action4Level;

    @Column(name = "action_5_id")
    private Integer action5Id;

    @Column(name = "action_5_level")
    private Integer action5Level;

    @NotNull
    @Column(name = "stamina", nullable = false)
    private Integer stamina;

    @NotNull
    @Column(name = "dribble", nullable = false)
    private Integer dribble;

    @NotNull
    @Column(name = "shoot", nullable = false)
    private Integer shoot;

    @NotNull
    @Column(name = "ball_pass", nullable = false)
    private Integer ballPass;

    @NotNull
    @Column(name = "tackle", nullable = false)
    private Integer tackle;

    @NotNull
    @Column(name = "jhi_block", nullable = false)
    private Integer block;

    @NotNull
    @Column(name = "intercept", nullable = false)
    private Integer intercept;

    @NotNull
    @Column(name = "speed", nullable = false)
    private Integer speed;

    @NotNull
    @Column(name = "power", nullable = false)
    private Integer power;

    @NotNull
    @Column(name = "technique", nullable = false)
    private Integer technique;

    @NotNull
    @Column(name = "punching", nullable = false)
    private Integer punching;

    @NotNull
    @Column(name = "catching", nullable = false)
    private Integer catching;

    @NotNull
    @Column(name = "high_ball_bonus", nullable = false)
    private Integer highBallBonus;

    @NotNull
    @Column(name = "low_ball_bonus", nullable = false)
    private Integer lowBallBonus;

    @NotNull
    @Column(name = "personality_id", nullable = false)
    private Integer personalityId;

    @NotNull
    @Column(name = "uniform_no", nullable = false)
    private Integer uniformNo;

    @NotNull
    @Column(name = "level_group_id", nullable = false)
    private Integer levelGroupId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mNpcCards")
    private MCharacter mcharacter;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public MNpcCard characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getShortName() {
        return shortName;
    }

    public MNpcCard shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNickName() {
        return nickName;
    }

    public MNpcCard nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public MNpcCard teamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public MNpcCard nationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
        return this;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MNpcCard rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public MNpcCard attribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getModelId() {
        return modelId;
    }

    public MNpcCard modelId(Integer modelId) {
        this.modelId = modelId;
        return this;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getLevel() {
        return level;
    }

    public MNpcCard level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public MNpcCard thumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
        return this;
    }

    public void setThumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public Integer getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public MNpcCard cardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
        return this;
    }

    public void setCardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public Integer getPlayableAssetsId() {
        return playableAssetsId;
    }

    public MNpcCard playableAssetsId(Integer playableAssetsId) {
        this.playableAssetsId = playableAssetsId;
        return this;
    }

    public void setPlayableAssetsId(Integer playableAssetsId) {
        this.playableAssetsId = playableAssetsId;
    }

    public Integer getTeamEffectId() {
        return teamEffectId;
    }

    public MNpcCard teamEffectId(Integer teamEffectId) {
        this.teamEffectId = teamEffectId;
        return this;
    }

    public void setTeamEffectId(Integer teamEffectId) {
        this.teamEffectId = teamEffectId;
    }

    public Integer getTeamEffectLevel() {
        return teamEffectLevel;
    }

    public MNpcCard teamEffectLevel(Integer teamEffectLevel) {
        this.teamEffectLevel = teamEffectLevel;
        return this;
    }

    public void setTeamEffectLevel(Integer teamEffectLevel) {
        this.teamEffectLevel = teamEffectLevel;
    }

    public Integer getTriggerEffectId() {
        return triggerEffectId;
    }

    public MNpcCard triggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
        return this;
    }

    public void setTriggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public Integer getAction1Id() {
        return action1Id;
    }

    public MNpcCard action1Id(Integer action1Id) {
        this.action1Id = action1Id;
        return this;
    }

    public void setAction1Id(Integer action1Id) {
        this.action1Id = action1Id;
    }

    public Integer getAction1Level() {
        return action1Level;
    }

    public MNpcCard action1Level(Integer action1Level) {
        this.action1Level = action1Level;
        return this;
    }

    public void setAction1Level(Integer action1Level) {
        this.action1Level = action1Level;
    }

    public Integer getAction2Id() {
        return action2Id;
    }

    public MNpcCard action2Id(Integer action2Id) {
        this.action2Id = action2Id;
        return this;
    }

    public void setAction2Id(Integer action2Id) {
        this.action2Id = action2Id;
    }

    public Integer getAction2Level() {
        return action2Level;
    }

    public MNpcCard action2Level(Integer action2Level) {
        this.action2Level = action2Level;
        return this;
    }

    public void setAction2Level(Integer action2Level) {
        this.action2Level = action2Level;
    }

    public Integer getAction3Id() {
        return action3Id;
    }

    public MNpcCard action3Id(Integer action3Id) {
        this.action3Id = action3Id;
        return this;
    }

    public void setAction3Id(Integer action3Id) {
        this.action3Id = action3Id;
    }

    public Integer getAction3Level() {
        return action3Level;
    }

    public MNpcCard action3Level(Integer action3Level) {
        this.action3Level = action3Level;
        return this;
    }

    public void setAction3Level(Integer action3Level) {
        this.action3Level = action3Level;
    }

    public Integer getAction4Id() {
        return action4Id;
    }

    public MNpcCard action4Id(Integer action4Id) {
        this.action4Id = action4Id;
        return this;
    }

    public void setAction4Id(Integer action4Id) {
        this.action4Id = action4Id;
    }

    public Integer getAction4Level() {
        return action4Level;
    }

    public MNpcCard action4Level(Integer action4Level) {
        this.action4Level = action4Level;
        return this;
    }

    public void setAction4Level(Integer action4Level) {
        this.action4Level = action4Level;
    }

    public Integer getAction5Id() {
        return action5Id;
    }

    public MNpcCard action5Id(Integer action5Id) {
        this.action5Id = action5Id;
        return this;
    }

    public void setAction5Id(Integer action5Id) {
        this.action5Id = action5Id;
    }

    public Integer getAction5Level() {
        return action5Level;
    }

    public MNpcCard action5Level(Integer action5Level) {
        this.action5Level = action5Level;
        return this;
    }

    public void setAction5Level(Integer action5Level) {
        this.action5Level = action5Level;
    }

    public Integer getStamina() {
        return stamina;
    }

    public MNpcCard stamina(Integer stamina) {
        this.stamina = stamina;
        return this;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public Integer getDribble() {
        return dribble;
    }

    public MNpcCard dribble(Integer dribble) {
        this.dribble = dribble;
        return this;
    }

    public void setDribble(Integer dribble) {
        this.dribble = dribble;
    }

    public Integer getShoot() {
        return shoot;
    }

    public MNpcCard shoot(Integer shoot) {
        this.shoot = shoot;
        return this;
    }

    public void setShoot(Integer shoot) {
        this.shoot = shoot;
    }

    public Integer getBallPass() {
        return ballPass;
    }

    public MNpcCard ballPass(Integer ballPass) {
        this.ballPass = ballPass;
        return this;
    }

    public void setBallPass(Integer ballPass) {
        this.ballPass = ballPass;
    }

    public Integer getTackle() {
        return tackle;
    }

    public MNpcCard tackle(Integer tackle) {
        this.tackle = tackle;
        return this;
    }

    public void setTackle(Integer tackle) {
        this.tackle = tackle;
    }

    public Integer getBlock() {
        return block;
    }

    public MNpcCard block(Integer block) {
        this.block = block;
        return this;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getIntercept() {
        return intercept;
    }

    public MNpcCard intercept(Integer intercept) {
        this.intercept = intercept;
        return this;
    }

    public void setIntercept(Integer intercept) {
        this.intercept = intercept;
    }

    public Integer getSpeed() {
        return speed;
    }

    public MNpcCard speed(Integer speed) {
        this.speed = speed;
        return this;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPower() {
        return power;
    }

    public MNpcCard power(Integer power) {
        this.power = power;
        return this;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getTechnique() {
        return technique;
    }

    public MNpcCard technique(Integer technique) {
        this.technique = technique;
        return this;
    }

    public void setTechnique(Integer technique) {
        this.technique = technique;
    }

    public Integer getPunching() {
        return punching;
    }

    public MNpcCard punching(Integer punching) {
        this.punching = punching;
        return this;
    }

    public void setPunching(Integer punching) {
        this.punching = punching;
    }

    public Integer getCatching() {
        return catching;
    }

    public MNpcCard catching(Integer catching) {
        this.catching = catching;
        return this;
    }

    public void setCatching(Integer catching) {
        this.catching = catching;
    }

    public Integer getHighBallBonus() {
        return highBallBonus;
    }

    public MNpcCard highBallBonus(Integer highBallBonus) {
        this.highBallBonus = highBallBonus;
        return this;
    }

    public void setHighBallBonus(Integer highBallBonus) {
        this.highBallBonus = highBallBonus;
    }

    public Integer getLowBallBonus() {
        return lowBallBonus;
    }

    public MNpcCard lowBallBonus(Integer lowBallBonus) {
        this.lowBallBonus = lowBallBonus;
        return this;
    }

    public void setLowBallBonus(Integer lowBallBonus) {
        this.lowBallBonus = lowBallBonus;
    }

    public Integer getPersonalityId() {
        return personalityId;
    }

    public MNpcCard personalityId(Integer personalityId) {
        this.personalityId = personalityId;
        return this;
    }

    public void setPersonalityId(Integer personalityId) {
        this.personalityId = personalityId;
    }

    public Integer getUniformNo() {
        return uniformNo;
    }

    public MNpcCard uniformNo(Integer uniformNo) {
        this.uniformNo = uniformNo;
        return this;
    }

    public void setUniformNo(Integer uniformNo) {
        this.uniformNo = uniformNo;
    }

    public Integer getLevelGroupId() {
        return levelGroupId;
    }

    public MNpcCard levelGroupId(Integer levelGroupId) {
        this.levelGroupId = levelGroupId;
        return this;
    }

    public void setLevelGroupId(Integer levelGroupId) {
        this.levelGroupId = levelGroupId;
    }

    public MCharacter getMcharacter() {
        return mcharacter;
    }

    public MNpcCard mcharacter(MCharacter mCharacter) {
        this.mcharacter = mCharacter;
        return this;
    }

    public void setMcharacter(MCharacter mCharacter) {
        this.mcharacter = mCharacter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MNpcCard)) {
            return false;
        }
        return id != null && id.equals(((MNpcCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MNpcCard{" +
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
            "}";
    }
}
