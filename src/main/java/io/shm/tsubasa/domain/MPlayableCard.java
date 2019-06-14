package io.shm.tsubasa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MPlayableCard.
 */
@Entity
@Table(name = "m_playable_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MPlayableCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "model_id", nullable = false)
    private Integer modelId;

    @NotNull
    @Column(name = "proper_position_gk", nullable = false)
    private Integer properPositionGk;

    @NotNull
    @Column(name = "proper_position_fw", nullable = false)
    private Integer properPositionFw;

    @NotNull
    @Column(name = "proper_position_omf", nullable = false)
    private Integer properPositionOmf;

    @NotNull
    @Column(name = "proper_position_dmf", nullable = false)
    private Integer properPositionDmf;

    @NotNull
    @Column(name = "proper_position_df", nullable = false)
    private Integer properPositionDf;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private Integer characterId;

    
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

    @Column(name = "trigger_effect_id")
    private Integer triggerEffectId;

    @NotNull
    @Column(name = "max_action_slot", nullable = false)
    private Integer maxActionSlot;

    @Column(name = "initial_action_id_1")
    private Integer initialActionId1;

    @Column(name = "initial_action_id_2")
    private Integer initialActionId2;

    @Column(name = "initial_action_id_3")
    private Integer initialActionId3;

    @Column(name = "initial_action_id_4")
    private Integer initialActionId4;

    @Column(name = "initial_action_id_5")
    private Integer initialActionId5;

    @NotNull
    @Column(name = "initial_stamina", nullable = false)
    private Integer initialStamina;

    @NotNull
    @Column(name = "initial_dribble", nullable = false)
    private Integer initialDribble;

    @NotNull
    @Column(name = "initial_shoot", nullable = false)
    private Integer initialShoot;

    @NotNull
    @Column(name = "initial_pass", nullable = false)
    private Integer initialPass;

    @NotNull
    @Column(name = "initial_tackle", nullable = false)
    private Integer initialTackle;

    @NotNull
    @Column(name = "initial_block", nullable = false)
    private Integer initialBlock;

    @NotNull
    @Column(name = "initial_intercept", nullable = false)
    private Integer initialIntercept;

    @NotNull
    @Column(name = "initial_speed", nullable = false)
    private Integer initialSpeed;

    @NotNull
    @Column(name = "initial_power", nullable = false)
    private Integer initialPower;

    @NotNull
    @Column(name = "initial_technique", nullable = false)
    private Integer initialTechnique;

    @NotNull
    @Column(name = "initial_punching", nullable = false)
    private Integer initialPunching;

    @NotNull
    @Column(name = "initial_catching", nullable = false)
    private Integer initialCatching;

    @NotNull
    @Column(name = "max_stamina", nullable = false)
    private Integer maxStamina;

    @NotNull
    @Column(name = "max_dribble", nullable = false)
    private Integer maxDribble;

    @NotNull
    @Column(name = "max_shoot", nullable = false)
    private Integer maxShoot;

    @NotNull
    @Column(name = "max_pass", nullable = false)
    private Integer maxPass;

    @NotNull
    @Column(name = "max_tackle", nullable = false)
    private Integer maxTackle;

    @NotNull
    @Column(name = "max_block", nullable = false)
    private Integer maxBlock;

    @NotNull
    @Column(name = "max_intercept", nullable = false)
    private Integer maxIntercept;

    @NotNull
    @Column(name = "max_speed", nullable = false)
    private Integer maxSpeed;

    @NotNull
    @Column(name = "max_power", nullable = false)
    private Integer maxPower;

    @NotNull
    @Column(name = "max_technique", nullable = false)
    private Integer maxTechnique;

    @NotNull
    @Column(name = "max_punching", nullable = false)
    private Integer maxPunching;

    @NotNull
    @Column(name = "max_catching", nullable = false)
    private Integer maxCatching;

    @NotNull
    @Column(name = "max_plus_dribble", nullable = false)
    private Integer maxPlusDribble;

    @NotNull
    @Column(name = "max_plus_shoot", nullable = false)
    private Integer maxPlusShoot;

    @NotNull
    @Column(name = "max_plus_pass", nullable = false)
    private Integer maxPlusPass;

    @NotNull
    @Column(name = "max_plus_tackle", nullable = false)
    private Integer maxPlusTackle;

    @NotNull
    @Column(name = "max_plus_block", nullable = false)
    private Integer maxPlusBlock;

    @NotNull
    @Column(name = "max_plus_intercept", nullable = false)
    private Integer maxPlusIntercept;

    @NotNull
    @Column(name = "max_plus_speed", nullable = false)
    private Integer maxPlusSpeed;

    @NotNull
    @Column(name = "max_plus_power", nullable = false)
    private Integer maxPlusPower;

    @NotNull
    @Column(name = "max_plus_technique", nullable = false)
    private Integer maxPlusTechnique;

    @NotNull
    @Column(name = "max_plus_punching", nullable = false)
    private Integer maxPlusPunching;

    @NotNull
    @Column(name = "max_plus_catching", nullable = false)
    private Integer maxPlusCatching;

    @NotNull
    @Column(name = "high_ball_bonus", nullable = false)
    private Integer highBallBonus;

    @NotNull
    @Column(name = "low_ball_bonus", nullable = false)
    private Integer lowBallBonus;

    @NotNull
    @Column(name = "is_drop_only", nullable = false)
    private Integer isDropOnly;

    @NotNull
    @Column(name = "sell_coin_group_num", nullable = false)
    private Integer sellCoinGroupNum;

    @Column(name = "sell_medal_id")
    private Integer sellMedalId;

    @NotNull
    @Column(name = "character_book_id", nullable = false)
    private Integer characterBookId;

    @NotNull
    @Column(name = "book_no", nullable = false)
    private Integer bookNo;

    @NotNull
    @Column(name = "is_show_book", nullable = false)
    private Integer isShowBook;

    @NotNull
    @Column(name = "level_group_id", nullable = false)
    private Integer levelGroupId;

    @Column(name = "start_at")
    private Integer startAt;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("mPlayableCards")
    private MModelCard mmodelcard;

    @OneToMany(mappedBy = "mplayablecard")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MArousal> mArousals = new HashSet<>();

    @OneToMany(mappedBy = "mplayablecard")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MTargetPlayableCardGroup> mTargetPlayableCardGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public MPlayableCard modelId(Integer modelId) {
        this.modelId = modelId;
        return this;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getProperPositionGk() {
        return properPositionGk;
    }

    public MPlayableCard properPositionGk(Integer properPositionGk) {
        this.properPositionGk = properPositionGk;
        return this;
    }

    public void setProperPositionGk(Integer properPositionGk) {
        this.properPositionGk = properPositionGk;
    }

    public Integer getProperPositionFw() {
        return properPositionFw;
    }

    public MPlayableCard properPositionFw(Integer properPositionFw) {
        this.properPositionFw = properPositionFw;
        return this;
    }

    public void setProperPositionFw(Integer properPositionFw) {
        this.properPositionFw = properPositionFw;
    }

    public Integer getProperPositionOmf() {
        return properPositionOmf;
    }

    public MPlayableCard properPositionOmf(Integer properPositionOmf) {
        this.properPositionOmf = properPositionOmf;
        return this;
    }

    public void setProperPositionOmf(Integer properPositionOmf) {
        this.properPositionOmf = properPositionOmf;
    }

    public Integer getProperPositionDmf() {
        return properPositionDmf;
    }

    public MPlayableCard properPositionDmf(Integer properPositionDmf) {
        this.properPositionDmf = properPositionDmf;
        return this;
    }

    public void setProperPositionDmf(Integer properPositionDmf) {
        this.properPositionDmf = properPositionDmf;
    }

    public Integer getProperPositionDf() {
        return properPositionDf;
    }

    public MPlayableCard properPositionDf(Integer properPositionDf) {
        this.properPositionDf = properPositionDf;
        return this;
    }

    public void setProperPositionDf(Integer properPositionDf) {
        this.properPositionDf = properPositionDf;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public MPlayableCard characterId(Integer characterId) {
        this.characterId = characterId;
        return this;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getNickName() {
        return nickName;
    }

    public MPlayableCard nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public MPlayableCard teamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public MPlayableCard nationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
        return this;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Integer getRarity() {
        return rarity;
    }

    public MPlayableCard rarity(Integer rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public MPlayableCard attribute(Integer attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public Integer getThumbnailAssetsId() {
        return thumbnailAssetsId;
    }

    public MPlayableCard thumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
        return this;
    }

    public void setThumbnailAssetsId(Integer thumbnailAssetsId) {
        this.thumbnailAssetsId = thumbnailAssetsId;
    }

    public Integer getCardIllustAssetsId() {
        return cardIllustAssetsId;
    }

    public MPlayableCard cardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
        return this;
    }

    public void setCardIllustAssetsId(Integer cardIllustAssetsId) {
        this.cardIllustAssetsId = cardIllustAssetsId;
    }

    public Integer getPlayableAssetsId() {
        return playableAssetsId;
    }

    public MPlayableCard playableAssetsId(Integer playableAssetsId) {
        this.playableAssetsId = playableAssetsId;
        return this;
    }

    public void setPlayableAssetsId(Integer playableAssetsId) {
        this.playableAssetsId = playableAssetsId;
    }

    public Integer getTeamEffectId() {
        return teamEffectId;
    }

    public MPlayableCard teamEffectId(Integer teamEffectId) {
        this.teamEffectId = teamEffectId;
        return this;
    }

    public void setTeamEffectId(Integer teamEffectId) {
        this.teamEffectId = teamEffectId;
    }

    public Integer getTriggerEffectId() {
        return triggerEffectId;
    }

    public MPlayableCard triggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
        return this;
    }

    public void setTriggerEffectId(Integer triggerEffectId) {
        this.triggerEffectId = triggerEffectId;
    }

    public Integer getMaxActionSlot() {
        return maxActionSlot;
    }

    public MPlayableCard maxActionSlot(Integer maxActionSlot) {
        this.maxActionSlot = maxActionSlot;
        return this;
    }

    public void setMaxActionSlot(Integer maxActionSlot) {
        this.maxActionSlot = maxActionSlot;
    }

    public Integer getInitialActionId1() {
        return initialActionId1;
    }

    public MPlayableCard initialActionId1(Integer initialActionId1) {
        this.initialActionId1 = initialActionId1;
        return this;
    }

    public void setInitialActionId1(Integer initialActionId1) {
        this.initialActionId1 = initialActionId1;
    }

    public Integer getInitialActionId2() {
        return initialActionId2;
    }

    public MPlayableCard initialActionId2(Integer initialActionId2) {
        this.initialActionId2 = initialActionId2;
        return this;
    }

    public void setInitialActionId2(Integer initialActionId2) {
        this.initialActionId2 = initialActionId2;
    }

    public Integer getInitialActionId3() {
        return initialActionId3;
    }

    public MPlayableCard initialActionId3(Integer initialActionId3) {
        this.initialActionId3 = initialActionId3;
        return this;
    }

    public void setInitialActionId3(Integer initialActionId3) {
        this.initialActionId3 = initialActionId3;
    }

    public Integer getInitialActionId4() {
        return initialActionId4;
    }

    public MPlayableCard initialActionId4(Integer initialActionId4) {
        this.initialActionId4 = initialActionId4;
        return this;
    }

    public void setInitialActionId4(Integer initialActionId4) {
        this.initialActionId4 = initialActionId4;
    }

    public Integer getInitialActionId5() {
        return initialActionId5;
    }

    public MPlayableCard initialActionId5(Integer initialActionId5) {
        this.initialActionId5 = initialActionId5;
        return this;
    }

    public void setInitialActionId5(Integer initialActionId5) {
        this.initialActionId5 = initialActionId5;
    }

    public Integer getInitialStamina() {
        return initialStamina;
    }

    public MPlayableCard initialStamina(Integer initialStamina) {
        this.initialStamina = initialStamina;
        return this;
    }

    public void setInitialStamina(Integer initialStamina) {
        this.initialStamina = initialStamina;
    }

    public Integer getInitialDribble() {
        return initialDribble;
    }

    public MPlayableCard initialDribble(Integer initialDribble) {
        this.initialDribble = initialDribble;
        return this;
    }

    public void setInitialDribble(Integer initialDribble) {
        this.initialDribble = initialDribble;
    }

    public Integer getInitialShoot() {
        return initialShoot;
    }

    public MPlayableCard initialShoot(Integer initialShoot) {
        this.initialShoot = initialShoot;
        return this;
    }

    public void setInitialShoot(Integer initialShoot) {
        this.initialShoot = initialShoot;
    }

    public Integer getInitialPass() {
        return initialPass;
    }

    public MPlayableCard initialPass(Integer initialPass) {
        this.initialPass = initialPass;
        return this;
    }

    public void setInitialPass(Integer initialPass) {
        this.initialPass = initialPass;
    }

    public Integer getInitialTackle() {
        return initialTackle;
    }

    public MPlayableCard initialTackle(Integer initialTackle) {
        this.initialTackle = initialTackle;
        return this;
    }

    public void setInitialTackle(Integer initialTackle) {
        this.initialTackle = initialTackle;
    }

    public Integer getInitialBlock() {
        return initialBlock;
    }

    public MPlayableCard initialBlock(Integer initialBlock) {
        this.initialBlock = initialBlock;
        return this;
    }

    public void setInitialBlock(Integer initialBlock) {
        this.initialBlock = initialBlock;
    }

    public Integer getInitialIntercept() {
        return initialIntercept;
    }

    public MPlayableCard initialIntercept(Integer initialIntercept) {
        this.initialIntercept = initialIntercept;
        return this;
    }

    public void setInitialIntercept(Integer initialIntercept) {
        this.initialIntercept = initialIntercept;
    }

    public Integer getInitialSpeed() {
        return initialSpeed;
    }

    public MPlayableCard initialSpeed(Integer initialSpeed) {
        this.initialSpeed = initialSpeed;
        return this;
    }

    public void setInitialSpeed(Integer initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public Integer getInitialPower() {
        return initialPower;
    }

    public MPlayableCard initialPower(Integer initialPower) {
        this.initialPower = initialPower;
        return this;
    }

    public void setInitialPower(Integer initialPower) {
        this.initialPower = initialPower;
    }

    public Integer getInitialTechnique() {
        return initialTechnique;
    }

    public MPlayableCard initialTechnique(Integer initialTechnique) {
        this.initialTechnique = initialTechnique;
        return this;
    }

    public void setInitialTechnique(Integer initialTechnique) {
        this.initialTechnique = initialTechnique;
    }

    public Integer getInitialPunching() {
        return initialPunching;
    }

    public MPlayableCard initialPunching(Integer initialPunching) {
        this.initialPunching = initialPunching;
        return this;
    }

    public void setInitialPunching(Integer initialPunching) {
        this.initialPunching = initialPunching;
    }

    public Integer getInitialCatching() {
        return initialCatching;
    }

    public MPlayableCard initialCatching(Integer initialCatching) {
        this.initialCatching = initialCatching;
        return this;
    }

    public void setInitialCatching(Integer initialCatching) {
        this.initialCatching = initialCatching;
    }

    public Integer getMaxStamina() {
        return maxStamina;
    }

    public MPlayableCard maxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public void setMaxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Integer getMaxDribble() {
        return maxDribble;
    }

    public MPlayableCard maxDribble(Integer maxDribble) {
        this.maxDribble = maxDribble;
        return this;
    }

    public void setMaxDribble(Integer maxDribble) {
        this.maxDribble = maxDribble;
    }

    public Integer getMaxShoot() {
        return maxShoot;
    }

    public MPlayableCard maxShoot(Integer maxShoot) {
        this.maxShoot = maxShoot;
        return this;
    }

    public void setMaxShoot(Integer maxShoot) {
        this.maxShoot = maxShoot;
    }

    public Integer getMaxPass() {
        return maxPass;
    }

    public MPlayableCard maxPass(Integer maxPass) {
        this.maxPass = maxPass;
        return this;
    }

    public void setMaxPass(Integer maxPass) {
        this.maxPass = maxPass;
    }

    public Integer getMaxTackle() {
        return maxTackle;
    }

    public MPlayableCard maxTackle(Integer maxTackle) {
        this.maxTackle = maxTackle;
        return this;
    }

    public void setMaxTackle(Integer maxTackle) {
        this.maxTackle = maxTackle;
    }

    public Integer getMaxBlock() {
        return maxBlock;
    }

    public MPlayableCard maxBlock(Integer maxBlock) {
        this.maxBlock = maxBlock;
        return this;
    }

    public void setMaxBlock(Integer maxBlock) {
        this.maxBlock = maxBlock;
    }

    public Integer getMaxIntercept() {
        return maxIntercept;
    }

    public MPlayableCard maxIntercept(Integer maxIntercept) {
        this.maxIntercept = maxIntercept;
        return this;
    }

    public void setMaxIntercept(Integer maxIntercept) {
        this.maxIntercept = maxIntercept;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public MPlayableCard maxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public MPlayableCard maxPower(Integer maxPower) {
        this.maxPower = maxPower;
        return this;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getMaxTechnique() {
        return maxTechnique;
    }

    public MPlayableCard maxTechnique(Integer maxTechnique) {
        this.maxTechnique = maxTechnique;
        return this;
    }

    public void setMaxTechnique(Integer maxTechnique) {
        this.maxTechnique = maxTechnique;
    }

    public Integer getMaxPunching() {
        return maxPunching;
    }

    public MPlayableCard maxPunching(Integer maxPunching) {
        this.maxPunching = maxPunching;
        return this;
    }

    public void setMaxPunching(Integer maxPunching) {
        this.maxPunching = maxPunching;
    }

    public Integer getMaxCatching() {
        return maxCatching;
    }

    public MPlayableCard maxCatching(Integer maxCatching) {
        this.maxCatching = maxCatching;
        return this;
    }

    public void setMaxCatching(Integer maxCatching) {
        this.maxCatching = maxCatching;
    }

    public Integer getMaxPlusDribble() {
        return maxPlusDribble;
    }

    public MPlayableCard maxPlusDribble(Integer maxPlusDribble) {
        this.maxPlusDribble = maxPlusDribble;
        return this;
    }

    public void setMaxPlusDribble(Integer maxPlusDribble) {
        this.maxPlusDribble = maxPlusDribble;
    }

    public Integer getMaxPlusShoot() {
        return maxPlusShoot;
    }

    public MPlayableCard maxPlusShoot(Integer maxPlusShoot) {
        this.maxPlusShoot = maxPlusShoot;
        return this;
    }

    public void setMaxPlusShoot(Integer maxPlusShoot) {
        this.maxPlusShoot = maxPlusShoot;
    }

    public Integer getMaxPlusPass() {
        return maxPlusPass;
    }

    public MPlayableCard maxPlusPass(Integer maxPlusPass) {
        this.maxPlusPass = maxPlusPass;
        return this;
    }

    public void setMaxPlusPass(Integer maxPlusPass) {
        this.maxPlusPass = maxPlusPass;
    }

    public Integer getMaxPlusTackle() {
        return maxPlusTackle;
    }

    public MPlayableCard maxPlusTackle(Integer maxPlusTackle) {
        this.maxPlusTackle = maxPlusTackle;
        return this;
    }

    public void setMaxPlusTackle(Integer maxPlusTackle) {
        this.maxPlusTackle = maxPlusTackle;
    }

    public Integer getMaxPlusBlock() {
        return maxPlusBlock;
    }

    public MPlayableCard maxPlusBlock(Integer maxPlusBlock) {
        this.maxPlusBlock = maxPlusBlock;
        return this;
    }

    public void setMaxPlusBlock(Integer maxPlusBlock) {
        this.maxPlusBlock = maxPlusBlock;
    }

    public Integer getMaxPlusIntercept() {
        return maxPlusIntercept;
    }

    public MPlayableCard maxPlusIntercept(Integer maxPlusIntercept) {
        this.maxPlusIntercept = maxPlusIntercept;
        return this;
    }

    public void setMaxPlusIntercept(Integer maxPlusIntercept) {
        this.maxPlusIntercept = maxPlusIntercept;
    }

    public Integer getMaxPlusSpeed() {
        return maxPlusSpeed;
    }

    public MPlayableCard maxPlusSpeed(Integer maxPlusSpeed) {
        this.maxPlusSpeed = maxPlusSpeed;
        return this;
    }

    public void setMaxPlusSpeed(Integer maxPlusSpeed) {
        this.maxPlusSpeed = maxPlusSpeed;
    }

    public Integer getMaxPlusPower() {
        return maxPlusPower;
    }

    public MPlayableCard maxPlusPower(Integer maxPlusPower) {
        this.maxPlusPower = maxPlusPower;
        return this;
    }

    public void setMaxPlusPower(Integer maxPlusPower) {
        this.maxPlusPower = maxPlusPower;
    }

    public Integer getMaxPlusTechnique() {
        return maxPlusTechnique;
    }

    public MPlayableCard maxPlusTechnique(Integer maxPlusTechnique) {
        this.maxPlusTechnique = maxPlusTechnique;
        return this;
    }

    public void setMaxPlusTechnique(Integer maxPlusTechnique) {
        this.maxPlusTechnique = maxPlusTechnique;
    }

    public Integer getMaxPlusPunching() {
        return maxPlusPunching;
    }

    public MPlayableCard maxPlusPunching(Integer maxPlusPunching) {
        this.maxPlusPunching = maxPlusPunching;
        return this;
    }

    public void setMaxPlusPunching(Integer maxPlusPunching) {
        this.maxPlusPunching = maxPlusPunching;
    }

    public Integer getMaxPlusCatching() {
        return maxPlusCatching;
    }

    public MPlayableCard maxPlusCatching(Integer maxPlusCatching) {
        this.maxPlusCatching = maxPlusCatching;
        return this;
    }

    public void setMaxPlusCatching(Integer maxPlusCatching) {
        this.maxPlusCatching = maxPlusCatching;
    }

    public Integer getHighBallBonus() {
        return highBallBonus;
    }

    public MPlayableCard highBallBonus(Integer highBallBonus) {
        this.highBallBonus = highBallBonus;
        return this;
    }

    public void setHighBallBonus(Integer highBallBonus) {
        this.highBallBonus = highBallBonus;
    }

    public Integer getLowBallBonus() {
        return lowBallBonus;
    }

    public MPlayableCard lowBallBonus(Integer lowBallBonus) {
        this.lowBallBonus = lowBallBonus;
        return this;
    }

    public void setLowBallBonus(Integer lowBallBonus) {
        this.lowBallBonus = lowBallBonus;
    }

    public Integer getIsDropOnly() {
        return isDropOnly;
    }

    public MPlayableCard isDropOnly(Integer isDropOnly) {
        this.isDropOnly = isDropOnly;
        return this;
    }

    public void setIsDropOnly(Integer isDropOnly) {
        this.isDropOnly = isDropOnly;
    }

    public Integer getSellCoinGroupNum() {
        return sellCoinGroupNum;
    }

    public MPlayableCard sellCoinGroupNum(Integer sellCoinGroupNum) {
        this.sellCoinGroupNum = sellCoinGroupNum;
        return this;
    }

    public void setSellCoinGroupNum(Integer sellCoinGroupNum) {
        this.sellCoinGroupNum = sellCoinGroupNum;
    }

    public Integer getSellMedalId() {
        return sellMedalId;
    }

    public MPlayableCard sellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
        return this;
    }

    public void setSellMedalId(Integer sellMedalId) {
        this.sellMedalId = sellMedalId;
    }

    public Integer getCharacterBookId() {
        return characterBookId;
    }

    public MPlayableCard characterBookId(Integer characterBookId) {
        this.characterBookId = characterBookId;
        return this;
    }

    public void setCharacterBookId(Integer characterBookId) {
        this.characterBookId = characterBookId;
    }

    public Integer getBookNo() {
        return bookNo;
    }

    public MPlayableCard bookNo(Integer bookNo) {
        this.bookNo = bookNo;
        return this;
    }

    public void setBookNo(Integer bookNo) {
        this.bookNo = bookNo;
    }

    public Integer getIsShowBook() {
        return isShowBook;
    }

    public MPlayableCard isShowBook(Integer isShowBook) {
        this.isShowBook = isShowBook;
        return this;
    }

    public void setIsShowBook(Integer isShowBook) {
        this.isShowBook = isShowBook;
    }

    public Integer getLevelGroupId() {
        return levelGroupId;
    }

    public MPlayableCard levelGroupId(Integer levelGroupId) {
        this.levelGroupId = levelGroupId;
        return this;
    }

    public void setLevelGroupId(Integer levelGroupId) {
        this.levelGroupId = levelGroupId;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public MPlayableCard startAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public MModelCard getMmodelcard() {
        return mmodelcard;
    }

    public MPlayableCard mmodelcard(MModelCard mModelCard) {
        this.mmodelcard = mModelCard;
        return this;
    }

    public void setMmodelcard(MModelCard mModelCard) {
        this.mmodelcard = mModelCard;
    }

    public Set<MArousal> getMArousals() {
        return mArousals;
    }

    public MPlayableCard mArousals(Set<MArousal> mArousals) {
        this.mArousals = mArousals;
        return this;
    }

    public MPlayableCard addMArousal(MArousal mArousal) {
        this.mArousals.add(mArousal);
        mArousal.setMplayablecard(this);
        return this;
    }

    public MPlayableCard removeMArousal(MArousal mArousal) {
        this.mArousals.remove(mArousal);
        mArousal.setMplayablecard(null);
        return this;
    }

    public void setMArousals(Set<MArousal> mArousals) {
        this.mArousals = mArousals;
    }

    public Set<MTargetPlayableCardGroup> getMTargetPlayableCardGroups() {
        return mTargetPlayableCardGroups;
    }

    public MPlayableCard mTargetPlayableCardGroups(Set<MTargetPlayableCardGroup> mTargetPlayableCardGroups) {
        this.mTargetPlayableCardGroups = mTargetPlayableCardGroups;
        return this;
    }

    public MPlayableCard addMTargetPlayableCardGroup(MTargetPlayableCardGroup mTargetPlayableCardGroup) {
        this.mTargetPlayableCardGroups.add(mTargetPlayableCardGroup);
        mTargetPlayableCardGroup.setMplayablecard(this);
        return this;
    }

    public MPlayableCard removeMTargetPlayableCardGroup(MTargetPlayableCardGroup mTargetPlayableCardGroup) {
        this.mTargetPlayableCardGroups.remove(mTargetPlayableCardGroup);
        mTargetPlayableCardGroup.setMplayablecard(null);
        return this;
    }

    public void setMTargetPlayableCardGroups(Set<MTargetPlayableCardGroup> mTargetPlayableCardGroups) {
        this.mTargetPlayableCardGroups = mTargetPlayableCardGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MPlayableCard)) {
            return false;
        }
        return id != null && id.equals(((MPlayableCard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MPlayableCard{" +
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
            "}";
    }
}
