package io.shm.tsubasa.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, io.shm.tsubasa.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, io.shm.tsubasa.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, io.shm.tsubasa.domain.User.class.getName());
            createCache(cm, io.shm.tsubasa.domain.Authority.class.getName());
            createCache(cm, io.shm.tsubasa.domain.User.class.getName() + ".authorities");
            createCache(cm, io.shm.tsubasa.domain.MAchievement.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAction.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAction.class.getName() + ".mTargetActionGroups");
            createCache(cm, io.shm.tsubasa.domain.MActionLevel.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MActionRarity.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MActionSkillCutin.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MActionSkillHolderCardCt.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAdventQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAdventQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAdventQuestTsubasaPointReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAdventQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAdventQuestWorld.class.getName() + ".mAdventQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MAnnounceText.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MApRecoveryItem.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAreaActionWeight.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MArousal.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MArousalItem.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MArousalMaterial.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAsset.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MAssetTagMapping.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MBadge.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardIllustAssets.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardLevel.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardPlayableAssets.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardPowerupActionSkill.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardRarity.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardThumbnailAssets.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCardThumbnailAssets.class.getName() + ".mCardPowerupActionSkills");
            createCache(cm, io.shm.tsubasa.domain.MCardThumbnailAssets.class.getName() + ".mTrainingCards");
            createCache(cm, io.shm.tsubasa.domain.MChallengeQuestAchievementReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MChallengeQuestAchievementRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MChallengeQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MChallengeQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MChallengeQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MChallengeQuestWorld.class.getName() + ".mChallengeQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName() + ".mActionSkillHolderCardCts");
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName() + ".mCombinationCutPositions");
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName() + ".mMatchResultCutins");
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName() + ".mNpcCards");
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName() + ".mTargetCharacterGroups");
            createCache(cm, io.shm.tsubasa.domain.MCharacterBook.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCharacterScoreCut.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MChatSystemMessage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCheatCaution.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MColorPalette.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCombinationCutPosition.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCommonBanner.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MConstant.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MContentableCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCoopRoomStamp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCut.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCutAction.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCutSeqGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCutShootOrbit.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDbMapping.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDeckCondition.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDeckRarityConditionDescription.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDefine.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDetachActionSkillCost.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryAr.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryDe.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryEn.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryEs.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryFr.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryIt.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryJa.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryPt.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDictionaryZh.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDistributeCardParameter.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDistributeCardParameterStep.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDistributeParamPoint.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDummyEmblem.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MDummyOpponent.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEmblemParts.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEmblemParts.class.getName() + ".mDummyEmblems");
            createCache(cm, io.shm.tsubasa.domain.MEmblemSet.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEncountersBonus.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEncountersCommandBranch.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEncountersCommandCompatibility.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEncountersCut.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MEventTitleEffect.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MExtensionSale.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MExtraNews.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MFormation.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MFormation.class.getName() + ".mNpcDecks");
            createCache(cm, io.shm.tsubasa.domain.MFormation.class.getName() + ".mTargetFormationGroups");
            createCache(cm, io.shm.tsubasa.domain.MFullPowerPoint.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRendition.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionAfterInputCutIn.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionAfterInputCutInTextColor.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionBall.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionExtraCutin.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionFirstGimmick.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionKicker.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionSwipeIcon.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionTradeSign.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionTrajectory.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionTrajectoryCutIn.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGachaTicket.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGoalEffectiveCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGoalJudgement.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuerillaQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuerillaQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuerillaQuestTsubasaPointReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuerillaQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuerillaQuestWorld.class.getName() + ".mGuerillaQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MGuildChatDefaultStamp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildEffect.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildEffectLevel.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildLevel.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildMission.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildNegativeWord.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildRoulettePrize.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MGuildTopBanner.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MHomeBanner.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MHomeMarquee.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MInheritActionSkillCost.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MInitUserDeck.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MKeeperCutAction.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLeague.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLeagueAffiliateReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLeagueEffect.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLeagueRankingReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLeagueRankingRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLeagueRegulation.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLoginBonusIncentive.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLoginBonusRound.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLoginBonusSerif.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLuck.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLuckRateGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLuckWeeklyQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLuckWeeklyQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLuckWeeklyQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MLuckWeeklyQuestWorld.class.getName() + ".mLuckWeeklyQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MMarathonAchievementReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonAchievementRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonBoostItem.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonBoostSchedule.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonEffectiveCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonLoopReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonLoopRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonQuestTsubasaPointReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonQuestWorld.class.getName() + ".mMarathonQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MMarathonRankingReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMarathonRankingRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMasterVersion.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMatchEnvironment.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMatchFormation.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMatchOption.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMatchOption.class.getName() + ".mLeagueRegulations");
            createCache(cm, io.shm.tsubasa.domain.MMatchOption.class.getName() + ".mPvpRegulations");
            createCache(cm, io.shm.tsubasa.domain.MMatchResultCutin.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMedal.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMission.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMission.class.getName() + ".mAchievements");
            createCache(cm, io.shm.tsubasa.domain.MMissionReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMissionReward.class.getName() + ".mGuildMissions");
            createCache(cm, io.shm.tsubasa.domain.MMissionReward.class.getName() + ".mMissions");
            createCache(cm, io.shm.tsubasa.domain.MModelCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MModelCard.class.getName() + ".mPlayableCards");
            createCache(cm, io.shm.tsubasa.domain.MModelQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MModelUniformBottom.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MModelUniformBottomResource.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MModelUniformUp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MModelUniformUpResource.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MMovieAsset.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MNationality.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MNationality.class.getName() + ".mTargetNationalityGroups");
            createCache(cm, io.shm.tsubasa.domain.MNgWord.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MNpcCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MNpcDeck.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MNpcDeck.class.getName() + ".mDummyOpponents");
            createCache(cm, io.shm.tsubasa.domain.MNpcPersonality.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPassiveEffectRange.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPassiveEffectRange.class.getName() + ".mFormations");
            createCache(cm, io.shm.tsubasa.domain.MPassiveEffectRange.class.getName() + ".mMatchOptions");
            createCache(cm, io.shm.tsubasa.domain.MPassiveEffectRange.class.getName() + ".mTeamEffectBases");
            createCache(cm, io.shm.tsubasa.domain.MPenaltyKickCut.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPlayableCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPlayableCard.class.getName() + ".mArousals");
            createCache(cm, io.shm.tsubasa.domain.MPlayableCard.class.getName() + ".mTargetPlayableCardGroups");
            createCache(cm, io.shm.tsubasa.domain.MPowerupActionSkillCost.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpGrade.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpGradeRequirement.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpPlayerStamp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpRankingReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpRankingRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpRegulation.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpWatcherStamp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpWave.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MPvpWave.class.getName() + ".mPvpRankingRewards");
            createCache(cm, io.shm.tsubasa.domain.MQuestAchievementGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestClearRewardWeight.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestCoop.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestCoopReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestDropRateCampaignContent.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestGoalReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestSpecialReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestStageCondition.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestStageConditionDescription.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestTicket.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestTsubasaPointReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MQuestWorld.class.getName() + ".mQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MRecommendFormationFilter.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MRecommendFormationFilterElement.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MRecommendShopMessage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MRegulatedLeagueRankingReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MRegulationMatchTutorialMessage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MRivalEncountCutin.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSceneTutorialMessage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSellCardCoin.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSellCardMedal.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MShoot.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSituation.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSituationAnnounce.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSoundBank.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MSoundBankEvent.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MStageStory.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MStamp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MStoreReviewUrl.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MStoryResourceMapping.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetActionGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetActionTypeGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetCharacterGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetFormationGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetNationalityGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetPlayableCardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetRarityGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetTeamGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTargetTriggerEffectGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTeamEffectBase.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTeamEffectLevel.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTeamEffectRarity.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTicketQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTicketQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTicketQuestTsubasaPointReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTicketQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTicketQuestWorld.class.getName() + ".mTicketQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MTimeattackQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTimeattackQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTimeattackQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTimeattackQuestWorld.class.getName() + ".mTimeattackQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MTimeattackRankingReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTimeattackRankingRewardGroup.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTips.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTrainingCard.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTrainingCost.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTriggerEffectBase.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTriggerEffectBase.class.getName() + ".mTargetTriggerEffectGroups");
            createCache(cm, io.shm.tsubasa.domain.MTriggerEffectRange.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTriggerEffectRange.class.getName() + ".mTriggerEffectBases");
            createCache(cm, io.shm.tsubasa.domain.MTsubasaPoint.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTutorialMessage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MUniformBottom.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MUniformOriginalSet.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MUniformUp.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MUserPolicyUpdateDate.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MUserRank.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MWeeklyQuestStage.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MWeeklyQuestStageReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MWeeklyQuestTsubasaPointReward.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MWeeklyQuestWorld.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MWeeklyQuestWorld.class.getName() + ".mWeeklyQuestStages");
            createCache(cm, io.shm.tsubasa.domain.MActionSkillHolderCardContent.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MCharacter.class.getName() + ".mActionSkillHolderCardContents");
            createCache(cm, io.shm.tsubasa.domain.MTeam.class.getName());
            createCache(cm, io.shm.tsubasa.domain.MTeam.class.getName() + ".mTargetTeamGroups");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }
}
