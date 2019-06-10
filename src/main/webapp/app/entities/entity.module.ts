import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'm-achievement',
        loadChildren: './m-achievement/m-achievement.module#TsubasaMAchievementModule'
      },
      {
        path: 'm-action',
        loadChildren: './m-action/m-action.module#TsubasaMActionModule'
      },
      {
        path: 'm-action-level',
        loadChildren: './m-action-level/m-action-level.module#TsubasaMActionLevelModule'
      },
      {
        path: 'm-action-rarity',
        loadChildren: './m-action-rarity/m-action-rarity.module#TsubasaMActionRarityModule'
      },
      {
        path: 'm-action-skill-cutin',
        loadChildren: './m-action-skill-cutin/m-action-skill-cutin.module#TsubasaMActionSkillCutinModule'
      },
      {
        path: 'm-action-skill-holder-card-ct',
        loadChildren: './m-action-skill-holder-card-ct/m-action-skill-holder-card-ct.module#TsubasaMActionSkillHolderCardCtModule'
      },
      {
        path: 'm-advent-quest-stage',
        loadChildren: './m-advent-quest-stage/m-advent-quest-stage.module#TsubasaMAdventQuestStageModule'
      },
      {
        path: 'm-advent-quest-stage-reward',
        loadChildren: './m-advent-quest-stage-reward/m-advent-quest-stage-reward.module#TsubasaMAdventQuestStageRewardModule'
      },
      {
        path: 'm-advent-quest-tsubasa-point-reward',
        loadChildren:
          './m-advent-quest-tsubasa-point-reward/m-advent-quest-tsubasa-point-reward.module#TsubasaMAdventQuestTsubasaPointRewardModule'
      },
      {
        path: 'm-advent-quest-world',
        loadChildren: './m-advent-quest-world/m-advent-quest-world.module#TsubasaMAdventQuestWorldModule'
      },
      {
        path: 'm-announce-text',
        loadChildren: './m-announce-text/m-announce-text.module#TsubasaMAnnounceTextModule'
      },
      {
        path: 'm-ap-recovery-item',
        loadChildren: './m-ap-recovery-item/m-ap-recovery-item.module#TsubasaMApRecoveryItemModule'
      },
      {
        path: 'm-area-action-weight',
        loadChildren: './m-area-action-weight/m-area-action-weight.module#TsubasaMAreaActionWeightModule'
      },
      {
        path: 'm-arousal',
        loadChildren: './m-arousal/m-arousal.module#TsubasaMArousalModule'
      },
      {
        path: 'm-arousal-item',
        loadChildren: './m-arousal-item/m-arousal-item.module#TsubasaMArousalItemModule'
      },
      {
        path: 'm-arousal-material',
        loadChildren: './m-arousal-material/m-arousal-material.module#TsubasaMArousalMaterialModule'
      },
      {
        path: 'm-asset',
        loadChildren: './m-asset/m-asset.module#TsubasaMAssetModule'
      },
      {
        path: 'm-asset-tag-mapping',
        loadChildren: './m-asset-tag-mapping/m-asset-tag-mapping.module#TsubasaMAssetTagMappingModule'
      },
      {
        path: 'm-badge',
        loadChildren: './m-badge/m-badge.module#TsubasaMBadgeModule'
      },
      {
        path: 'm-card-illust-assets',
        loadChildren: './m-card-illust-assets/m-card-illust-assets.module#TsubasaMCardIllustAssetsModule'
      },
      {
        path: 'm-card-level',
        loadChildren: './m-card-level/m-card-level.module#TsubasaMCardLevelModule'
      },
      {
        path: 'm-card-playable-assets',
        loadChildren: './m-card-playable-assets/m-card-playable-assets.module#TsubasaMCardPlayableAssetsModule'
      },
      {
        path: 'm-card-powerup-action-skill',
        loadChildren: './m-card-powerup-action-skill/m-card-powerup-action-skill.module#TsubasaMCardPowerupActionSkillModule'
      },
      {
        path: 'm-card-rarity',
        loadChildren: './m-card-rarity/m-card-rarity.module#TsubasaMCardRarityModule'
      },
      {
        path: 'm-card-thumbnail-assets',
        loadChildren: './m-card-thumbnail-assets/m-card-thumbnail-assets.module#TsubasaMCardThumbnailAssetsModule'
      },
      {
        path: 'm-challenge-quest-achievement-reward',
        loadChildren:
          './m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward.module#TsubasaMChallengeQuestAchievementRewardModule'
      },
      {
        path: 'm-challenge-quest-achievement-reward-group',
        loadChildren:
          './m-challenge-quest-achievement-reward-group/m-challenge-quest-achievement-reward-group.module#TsubasaMChallengeQuestAchievementRewardGroupModule'
      },
      {
        path: 'm-challenge-quest-stage',
        loadChildren: './m-challenge-quest-stage/m-challenge-quest-stage.module#TsubasaMChallengeQuestStageModule'
      },
      {
        path: 'm-challenge-quest-stage-reward',
        loadChildren: './m-challenge-quest-stage-reward/m-challenge-quest-stage-reward.module#TsubasaMChallengeQuestStageRewardModule'
      },
      {
        path: 'm-challenge-quest-world',
        loadChildren: './m-challenge-quest-world/m-challenge-quest-world.module#TsubasaMChallengeQuestWorldModule'
      },
      {
        path: 'm-character',
        loadChildren: './m-character/m-character.module#TsubasaMCharacterModule'
      },
      {
        path: 'm-character-book',
        loadChildren: './m-character-book/m-character-book.module#TsubasaMCharacterBookModule'
      },
      {
        path: 'm-character-score-cut',
        loadChildren: './m-character-score-cut/m-character-score-cut.module#TsubasaMCharacterScoreCutModule'
      },
      {
        path: 'm-chat-system-message',
        loadChildren: './m-chat-system-message/m-chat-system-message.module#TsubasaMChatSystemMessageModule'
      },
      {
        path: 'm-cheat-caution',
        loadChildren: './m-cheat-caution/m-cheat-caution.module#TsubasaMCheatCautionModule'
      },
      {
        path: 'm-color-palette',
        loadChildren: './m-color-palette/m-color-palette.module#TsubasaMColorPaletteModule'
      },
      {
        path: 'm-combination-cut-position',
        loadChildren: './m-combination-cut-position/m-combination-cut-position.module#TsubasaMCombinationCutPositionModule'
      },
      {
        path: 'm-common-banner',
        loadChildren: './m-common-banner/m-common-banner.module#TsubasaMCommonBannerModule'
      },
      {
        path: 'm-constant',
        loadChildren: './m-constant/m-constant.module#TsubasaMConstantModule'
      },
      {
        path: 'm-contentable-card',
        loadChildren: './m-contentable-card/m-contentable-card.module#TsubasaMContentableCardModule'
      },
      {
        path: 'm-coop-room-stamp',
        loadChildren: './m-coop-room-stamp/m-coop-room-stamp.module#TsubasaMCoopRoomStampModule'
      },
      {
        path: 'm-cut',
        loadChildren: './m-cut/m-cut.module#TsubasaMCutModule'
      },
      {
        path: 'm-cut-action',
        loadChildren: './m-cut-action/m-cut-action.module#TsubasaMCutActionModule'
      },
      {
        path: 'm-cut-seq-group',
        loadChildren: './m-cut-seq-group/m-cut-seq-group.module#TsubasaMCutSeqGroupModule'
      },
      {
        path: 'm-cut-shoot-orbit',
        loadChildren: './m-cut-shoot-orbit/m-cut-shoot-orbit.module#TsubasaMCutShootOrbitModule'
      },
      {
        path: 'm-db-mapping',
        loadChildren: './m-db-mapping/m-db-mapping.module#TsubasaMDbMappingModule'
      },
      {
        path: 'm-deck-condition',
        loadChildren: './m-deck-condition/m-deck-condition.module#TsubasaMDeckConditionModule'
      },
      {
        path: 'm-deck-rarity-condition-description',
        loadChildren:
          './m-deck-rarity-condition-description/m-deck-rarity-condition-description.module#TsubasaMDeckRarityConditionDescriptionModule'
      },
      {
        path: 'm-define',
        loadChildren: './m-define/m-define.module#TsubasaMDefineModule'
      },
      {
        path: 'm-detach-action-skill-cost',
        loadChildren: './m-detach-action-skill-cost/m-detach-action-skill-cost.module#TsubasaMDetachActionSkillCostModule'
      },
      {
        path: 'm-dictionary-ar',
        loadChildren: './m-dictionary-ar/m-dictionary-ar.module#TsubasaMDictionaryArModule'
      },
      {
        path: 'm-dictionary-de',
        loadChildren: './m-dictionary-de/m-dictionary-de.module#TsubasaMDictionaryDeModule'
      },
      {
        path: 'm-dictionary-en',
        loadChildren: './m-dictionary-en/m-dictionary-en.module#TsubasaMDictionaryEnModule'
      },
      {
        path: 'm-dictionary-es',
        loadChildren: './m-dictionary-es/m-dictionary-es.module#TsubasaMDictionaryEsModule'
      },
      {
        path: 'm-dictionary-fr',
        loadChildren: './m-dictionary-fr/m-dictionary-fr.module#TsubasaMDictionaryFrModule'
      },
      {
        path: 'm-dictionary-it',
        loadChildren: './m-dictionary-it/m-dictionary-it.module#TsubasaMDictionaryItModule'
      },
      {
        path: 'm-dictionary-ja',
        loadChildren: './m-dictionary-ja/m-dictionary-ja.module#TsubasaMDictionaryJaModule'
      },
      {
        path: 'm-dictionary-pt',
        loadChildren: './m-dictionary-pt/m-dictionary-pt.module#TsubasaMDictionaryPtModule'
      },
      {
        path: 'm-dictionary-zh',
        loadChildren: './m-dictionary-zh/m-dictionary-zh.module#TsubasaMDictionaryZhModule'
      },
      {
        path: 'm-distribute-card-parameter',
        loadChildren: './m-distribute-card-parameter/m-distribute-card-parameter.module#TsubasaMDistributeCardParameterModule'
      },
      {
        path: 'm-distribute-card-parameter-step',
        loadChildren: './m-distribute-card-parameter-step/m-distribute-card-parameter-step.module#TsubasaMDistributeCardParameterStepModule'
      },
      {
        path: 'm-distribute-param-point',
        loadChildren: './m-distribute-param-point/m-distribute-param-point.module#TsubasaMDistributeParamPointModule'
      },
      {
        path: 'm-dummy-emblem',
        loadChildren: './m-dummy-emblem/m-dummy-emblem.module#TsubasaMDummyEmblemModule'
      },
      {
        path: 'm-dummy-opponent',
        loadChildren: './m-dummy-opponent/m-dummy-opponent.module#TsubasaMDummyOpponentModule'
      },
      {
        path: 'm-emblem-parts',
        loadChildren: './m-emblem-parts/m-emblem-parts.module#TsubasaMEmblemPartsModule'
      },
      {
        path: 'm-emblem-set',
        loadChildren: './m-emblem-set/m-emblem-set.module#TsubasaMEmblemSetModule'
      },
      {
        path: 'm-encounters-bonus',
        loadChildren: './m-encounters-bonus/m-encounters-bonus.module#TsubasaMEncountersBonusModule'
      },
      {
        path: 'm-encounters-command-branch',
        loadChildren: './m-encounters-command-branch/m-encounters-command-branch.module#TsubasaMEncountersCommandBranchModule'
      },
      {
        path: 'm-encounters-command-compatibility',
        loadChildren:
          './m-encounters-command-compatibility/m-encounters-command-compatibility.module#TsubasaMEncountersCommandCompatibilityModule'
      },
      {
        path: 'm-encounters-cut',
        loadChildren: './m-encounters-cut/m-encounters-cut.module#TsubasaMEncountersCutModule'
      },
      {
        path: 'm-event-title-effect',
        loadChildren: './m-event-title-effect/m-event-title-effect.module#TsubasaMEventTitleEffectModule'
      },
      {
        path: 'm-extension-sale',
        loadChildren: './m-extension-sale/m-extension-sale.module#TsubasaMExtensionSaleModule'
      },
      {
        path: 'm-extra-news',
        loadChildren: './m-extra-news/m-extra-news.module#TsubasaMExtraNewsModule'
      },
      {
        path: 'm-formation',
        loadChildren: './m-formation/m-formation.module#TsubasaMFormationModule'
      },
      {
        path: 'm-full-power-point',
        loadChildren: './m-full-power-point/m-full-power-point.module#TsubasaMFullPowerPointModule'
      },
      {
        path: 'm-gacha-rendition',
        loadChildren: './m-gacha-rendition/m-gacha-rendition.module#TsubasaMGachaRenditionModule'
      },
      {
        path: 'm-gacha-rendition-after-input-cut-in',
        loadChildren:
          './m-gacha-rendition-after-input-cut-in/m-gacha-rendition-after-input-cut-in.module#TsubasaMGachaRenditionAfterInputCutInModule'
      },
      {
        path: 'm-gacha-rendition-after-input-cut-in-text-color',
        loadChildren:
          './m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color.module#TsubasaMGachaRenditionAfterInputCutInTextColorModule'
      },
      {
        path: 'm-gacha-rendition-ball',
        loadChildren: './m-gacha-rendition-ball/m-gacha-rendition-ball.module#TsubasaMGachaRenditionBallModule'
      },
      {
        path: 'm-gacha-rendition-before-shoot-cut-in-character-num',
        loadChildren:
          './m-gacha-rendition-before-shoot-cut-in-character-num/m-gacha-rendition-before-shoot-cut-in-character-num.module#TsubasaMGachaRenditionBeforeShootCutInCharacterNumModule'
      },
      {
        path: 'm-gacha-rendition-before-shoot-cut-in-pattern',
        loadChildren:
          './m-gacha-rendition-before-shoot-cut-in-pattern/m-gacha-rendition-before-shoot-cut-in-pattern.module#TsubasaMGachaRenditionBeforeShootCutInPatternModule'
      },
      {
        path: 'm-gacha-rendition-extra-cutin',
        loadChildren: './m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin.module#TsubasaMGachaRenditionExtraCutinModule'
      },
      {
        path: 'm-gacha-rendition-first-gimmick',
        loadChildren: './m-gacha-rendition-first-gimmick/m-gacha-rendition-first-gimmick.module#TsubasaMGachaRenditionFirstGimmickModule'
      },
      {
        path: 'm-gacha-rendition-kicker',
        loadChildren: './m-gacha-rendition-kicker/m-gacha-rendition-kicker.module#TsubasaMGachaRenditionKickerModule'
      },
      {
        path: 'm-gacha-rendition-swipe-icon',
        loadChildren: './m-gacha-rendition-swipe-icon/m-gacha-rendition-swipe-icon.module#TsubasaMGachaRenditionSwipeIconModule'
      },
      {
        path: 'm-gacha-rendition-trade-sign',
        loadChildren: './m-gacha-rendition-trade-sign/m-gacha-rendition-trade-sign.module#TsubasaMGachaRenditionTradeSignModule'
      },
      {
        path: 'm-gacha-rendition-trajectory',
        loadChildren: './m-gacha-rendition-trajectory/m-gacha-rendition-trajectory.module#TsubasaMGachaRenditionTrajectoryModule'
      },
      {
        path: 'm-gacha-rendition-trajectory-cut-in',
        loadChildren:
          './m-gacha-rendition-trajectory-cut-in/m-gacha-rendition-trajectory-cut-in.module#TsubasaMGachaRenditionTrajectoryCutInModule'
      },
      {
        path: 'm-gacha-rendition-trajectory-phoenix',
        loadChildren:
          './m-gacha-rendition-trajectory-phoenix/m-gacha-rendition-trajectory-phoenix.module#TsubasaMGachaRenditionTrajectoryPhoenixModule'
      },
      {
        path: 'm-gacha-ticket',
        loadChildren: './m-gacha-ticket/m-gacha-ticket.module#TsubasaMGachaTicketModule'
      },
      {
        path: 'm-goal-effective-card',
        loadChildren: './m-goal-effective-card/m-goal-effective-card.module#TsubasaMGoalEffectiveCardModule'
      },
      {
        path: 'm-goal-judgement',
        loadChildren: './m-goal-judgement/m-goal-judgement.module#TsubasaMGoalJudgementModule'
      },
      {
        path: 'm-guerilla-quest-stage',
        loadChildren: './m-guerilla-quest-stage/m-guerilla-quest-stage.module#TsubasaMGuerillaQuestStageModule'
      },
      {
        path: 'm-guerilla-quest-stage-reward',
        loadChildren: './m-guerilla-quest-stage-reward/m-guerilla-quest-stage-reward.module#TsubasaMGuerillaQuestStageRewardModule'
      },
      {
        path: 'm-guerilla-quest-tsubasa-point-reward',
        loadChildren:
          './m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward.module#TsubasaMGuerillaQuestTsubasaPointRewardModule'
      },
      {
        path: 'm-guerilla-quest-world',
        loadChildren: './m-guerilla-quest-world/m-guerilla-quest-world.module#TsubasaMGuerillaQuestWorldModule'
      },
      {
        path: 'm-guild-chat-default-stamp',
        loadChildren: './m-guild-chat-default-stamp/m-guild-chat-default-stamp.module#TsubasaMGuildChatDefaultStampModule'
      },
      {
        path: 'm-guild-effect',
        loadChildren: './m-guild-effect/m-guild-effect.module#TsubasaMGuildEffectModule'
      },
      {
        path: 'm-guild-effect-level',
        loadChildren: './m-guild-effect-level/m-guild-effect-level.module#TsubasaMGuildEffectLevelModule'
      },
      {
        path: 'm-guild-level',
        loadChildren: './m-guild-level/m-guild-level.module#TsubasaMGuildLevelModule'
      },
      {
        path: 'm-guild-mission',
        loadChildren: './m-guild-mission/m-guild-mission.module#TsubasaMGuildMissionModule'
      },
      {
        path: 'm-guild-negative-word',
        loadChildren: './m-guild-negative-word/m-guild-negative-word.module#TsubasaMGuildNegativeWordModule'
      },
      {
        path: 'm-guild-roulette-prize',
        loadChildren: './m-guild-roulette-prize/m-guild-roulette-prize.module#TsubasaMGuildRoulettePrizeModule'
      },
      {
        path: 'm-guild-top-banner',
        loadChildren: './m-guild-top-banner/m-guild-top-banner.module#TsubasaMGuildTopBannerModule'
      },
      {
        path: 'm-home-banner',
        loadChildren: './m-home-banner/m-home-banner.module#TsubasaMHomeBannerModule'
      },
      {
        path: 'm-home-marquee',
        loadChildren: './m-home-marquee/m-home-marquee.module#TsubasaMHomeMarqueeModule'
      },
      {
        path: 'm-inherit-action-skill-cost',
        loadChildren: './m-inherit-action-skill-cost/m-inherit-action-skill-cost.module#TsubasaMInheritActionSkillCostModule'
      },
      {
        path: 'm-init-user-deck',
        loadChildren: './m-init-user-deck/m-init-user-deck.module#TsubasaMInitUserDeckModule'
      },
      {
        path: 'm-keeper-cut-action',
        loadChildren: './m-keeper-cut-action/m-keeper-cut-action.module#TsubasaMKeeperCutActionModule'
      },
      {
        path: 'm-league',
        loadChildren: './m-league/m-league.module#TsubasaMLeagueModule'
      },
      {
        path: 'm-league-affiliate-reward',
        loadChildren: './m-league-affiliate-reward/m-league-affiliate-reward.module#TsubasaMLeagueAffiliateRewardModule'
      },
      {
        path: 'm-league-effect',
        loadChildren: './m-league-effect/m-league-effect.module#TsubasaMLeagueEffectModule'
      },
      {
        path: 'm-league-ranking-reward',
        loadChildren: './m-league-ranking-reward/m-league-ranking-reward.module#TsubasaMLeagueRankingRewardModule'
      },
      {
        path: 'm-league-ranking-reward-group',
        loadChildren: './m-league-ranking-reward-group/m-league-ranking-reward-group.module#TsubasaMLeagueRankingRewardGroupModule'
      },
      {
        path: 'm-league-regulation',
        loadChildren: './m-league-regulation/m-league-regulation.module#TsubasaMLeagueRegulationModule'
      },
      {
        path: 'm-login-bonus-incentive',
        loadChildren: './m-login-bonus-incentive/m-login-bonus-incentive.module#TsubasaMLoginBonusIncentiveModule'
      },
      {
        path: 'm-login-bonus-round',
        loadChildren: './m-login-bonus-round/m-login-bonus-round.module#TsubasaMLoginBonusRoundModule'
      },
      {
        path: 'm-login-bonus-serif',
        loadChildren: './m-login-bonus-serif/m-login-bonus-serif.module#TsubasaMLoginBonusSerifModule'
      },
      {
        path: 'm-luck',
        loadChildren: './m-luck/m-luck.module#TsubasaMLuckModule'
      },
      {
        path: 'm-luck-rate-group',
        loadChildren: './m-luck-rate-group/m-luck-rate-group.module#TsubasaMLuckRateGroupModule'
      },
      {
        path: 'm-luck-weekly-quest-stage',
        loadChildren: './m-luck-weekly-quest-stage/m-luck-weekly-quest-stage.module#TsubasaMLuckWeeklyQuestStageModule'
      },
      {
        path: 'm-luck-weekly-quest-stage-reward',
        loadChildren: './m-luck-weekly-quest-stage-reward/m-luck-weekly-quest-stage-reward.module#TsubasaMLuckWeeklyQuestStageRewardModule'
      },
      {
        path: 'm-luck-weekly-quest-world',
        loadChildren: './m-luck-weekly-quest-world/m-luck-weekly-quest-world.module#TsubasaMLuckWeeklyQuestWorldModule'
      },
      {
        path: 'm-marathon-achievement-reward',
        loadChildren: './m-marathon-achievement-reward/m-marathon-achievement-reward.module#TsubasaMMarathonAchievementRewardModule'
      },
      {
        path: 'm-marathon-achievement-reward-group',
        loadChildren:
          './m-marathon-achievement-reward-group/m-marathon-achievement-reward-group.module#TsubasaMMarathonAchievementRewardGroupModule'
      },
      {
        path: 'm-marathon-boost-item',
        loadChildren: './m-marathon-boost-item/m-marathon-boost-item.module#TsubasaMMarathonBoostItemModule'
      },
      {
        path: 'm-marathon-boost-schedule',
        loadChildren: './m-marathon-boost-schedule/m-marathon-boost-schedule.module#TsubasaMMarathonBoostScheduleModule'
      },
      {
        path: 'm-marathon-effective-card',
        loadChildren: './m-marathon-effective-card/m-marathon-effective-card.module#TsubasaMMarathonEffectiveCardModule'
      },
      {
        path: 'm-marathon-loop-reward',
        loadChildren: './m-marathon-loop-reward/m-marathon-loop-reward.module#TsubasaMMarathonLoopRewardModule'
      },
      {
        path: 'm-marathon-loop-reward-group',
        loadChildren: './m-marathon-loop-reward-group/m-marathon-loop-reward-group.module#TsubasaMMarathonLoopRewardGroupModule'
      },
      {
        path: 'm-marathon-quest-stage',
        loadChildren: './m-marathon-quest-stage/m-marathon-quest-stage.module#TsubasaMMarathonQuestStageModule'
      },
      {
        path: 'm-marathon-quest-stage-reward',
        loadChildren: './m-marathon-quest-stage-reward/m-marathon-quest-stage-reward.module#TsubasaMMarathonQuestStageRewardModule'
      },
      {
        path: 'm-marathon-quest-tsubasa-point-reward',
        loadChildren:
          './m-marathon-quest-tsubasa-point-reward/m-marathon-quest-tsubasa-point-reward.module#TsubasaMMarathonQuestTsubasaPointRewardModule'
      },
      {
        path: 'm-marathon-quest-world',
        loadChildren: './m-marathon-quest-world/m-marathon-quest-world.module#TsubasaMMarathonQuestWorldModule'
      },
      {
        path: 'm-marathon-ranking-reward',
        loadChildren: './m-marathon-ranking-reward/m-marathon-ranking-reward.module#TsubasaMMarathonRankingRewardModule'
      },
      {
        path: 'm-marathon-ranking-reward-group',
        loadChildren: './m-marathon-ranking-reward-group/m-marathon-ranking-reward-group.module#TsubasaMMarathonRankingRewardGroupModule'
      },
      {
        path: 'm-master-version',
        loadChildren: './m-master-version/m-master-version.module#TsubasaMMasterVersionModule'
      },
      {
        path: 'm-match-environment',
        loadChildren: './m-match-environment/m-match-environment.module#TsubasaMMatchEnvironmentModule'
      },
      {
        path: 'm-match-formation',
        loadChildren: './m-match-formation/m-match-formation.module#TsubasaMMatchFormationModule'
      },
      {
        path: 'm-match-option',
        loadChildren: './m-match-option/m-match-option.module#TsubasaMMatchOptionModule'
      },
      {
        path: 'm-match-result-cutin',
        loadChildren: './m-match-result-cutin/m-match-result-cutin.module#TsubasaMMatchResultCutinModule'
      },
      {
        path: 'm-medal',
        loadChildren: './m-medal/m-medal.module#TsubasaMMedalModule'
      },
      {
        path: 'm-mission',
        loadChildren: './m-mission/m-mission.module#TsubasaMMissionModule'
      },
      {
        path: 'm-mission-reward',
        loadChildren: './m-mission-reward/m-mission-reward.module#TsubasaMMissionRewardModule'
      },
      {
        path: 'm-model-card',
        loadChildren: './m-model-card/m-model-card.module#TsubasaMModelCardModule'
      },
      {
        path: 'm-model-quest-stage',
        loadChildren: './m-model-quest-stage/m-model-quest-stage.module#TsubasaMModelQuestStageModule'
      },
      {
        path: 'm-model-uniform-bottom',
        loadChildren: './m-model-uniform-bottom/m-model-uniform-bottom.module#TsubasaMModelUniformBottomModule'
      },
      {
        path: 'm-model-uniform-bottom-resource',
        loadChildren: './m-model-uniform-bottom-resource/m-model-uniform-bottom-resource.module#TsubasaMModelUniformBottomResourceModule'
      },
      {
        path: 'm-model-uniform-up',
        loadChildren: './m-model-uniform-up/m-model-uniform-up.module#TsubasaMModelUniformUpModule'
      },
      {
        path: 'm-model-uniform-up-resource',
        loadChildren: './m-model-uniform-up-resource/m-model-uniform-up-resource.module#TsubasaMModelUniformUpResourceModule'
      },
      {
        path: 'm-movie-asset',
        loadChildren: './m-movie-asset/m-movie-asset.module#TsubasaMMovieAssetModule'
      },
      {
        path: 'm-nationality',
        loadChildren: './m-nationality/m-nationality.module#TsubasaMNationalityModule'
      },
      {
        path: 'm-ng-word',
        loadChildren: './m-ng-word/m-ng-word.module#TsubasaMNgWordModule'
      },
      {
        path: 'm-npc-card',
        loadChildren: './m-npc-card/m-npc-card.module#TsubasaMNpcCardModule'
      },
      {
        path: 'm-npc-deck',
        loadChildren: './m-npc-deck/m-npc-deck.module#TsubasaMNpcDeckModule'
      },
      {
        path: 'm-npc-personality',
        loadChildren: './m-npc-personality/m-npc-personality.module#TsubasaMNpcPersonalityModule'
      },
      {
        path: 'm-passive-effect-range',
        loadChildren: './m-passive-effect-range/m-passive-effect-range.module#TsubasaMPassiveEffectRangeModule'
      },
      {
        path: 'm-penalty-kick-cut',
        loadChildren: './m-penalty-kick-cut/m-penalty-kick-cut.module#TsubasaMPenaltyKickCutModule'
      },
      {
        path: 'm-playable-card',
        loadChildren: './m-playable-card/m-playable-card.module#TsubasaMPlayableCardModule'
      },
      {
        path: 'm-powerup-action-skill-cost',
        loadChildren: './m-powerup-action-skill-cost/m-powerup-action-skill-cost.module#TsubasaMPowerupActionSkillCostModule'
      },
      {
        path: 'm-pvp-grade',
        loadChildren: './m-pvp-grade/m-pvp-grade.module#TsubasaMPvpGradeModule'
      },
      {
        path: 'm-pvp-grade-requirement',
        loadChildren: './m-pvp-grade-requirement/m-pvp-grade-requirement.module#TsubasaMPvpGradeRequirementModule'
      },
      {
        path: 'm-pvp-player-stamp',
        loadChildren: './m-pvp-player-stamp/m-pvp-player-stamp.module#TsubasaMPvpPlayerStampModule'
      },
      {
        path: 'm-pvp-ranking-reward',
        loadChildren: './m-pvp-ranking-reward/m-pvp-ranking-reward.module#TsubasaMPvpRankingRewardModule'
      },
      {
        path: 'm-pvp-ranking-reward-group',
        loadChildren: './m-pvp-ranking-reward-group/m-pvp-ranking-reward-group.module#TsubasaMPvpRankingRewardGroupModule'
      },
      {
        path: 'm-pvp-regulation',
        loadChildren: './m-pvp-regulation/m-pvp-regulation.module#TsubasaMPvpRegulationModule'
      },
      {
        path: 'm-pvp-watcher-stamp',
        loadChildren: './m-pvp-watcher-stamp/m-pvp-watcher-stamp.module#TsubasaMPvpWatcherStampModule'
      },
      {
        path: 'm-pvp-wave',
        loadChildren: './m-pvp-wave/m-pvp-wave.module#TsubasaMPvpWaveModule'
      },
      {
        path: 'm-quest-achievement-group',
        loadChildren: './m-quest-achievement-group/m-quest-achievement-group.module#TsubasaMQuestAchievementGroupModule'
      },
      {
        path: 'm-quest-clear-reward-weight',
        loadChildren: './m-quest-clear-reward-weight/m-quest-clear-reward-weight.module#TsubasaMQuestClearRewardWeightModule'
      },
      {
        path: 'm-quest-coop',
        loadChildren: './m-quest-coop/m-quest-coop.module#TsubasaMQuestCoopModule'
      },
      {
        path: 'm-quest-coop-reward',
        loadChildren: './m-quest-coop-reward/m-quest-coop-reward.module#TsubasaMQuestCoopRewardModule'
      },
      {
        path: 'm-quest-drop-rate-campaign-content',
        loadChildren:
          './m-quest-drop-rate-campaign-content/m-quest-drop-rate-campaign-content.module#TsubasaMQuestDropRateCampaignContentModule'
      },
      {
        path: 'm-quest-goal-reward',
        loadChildren: './m-quest-goal-reward/m-quest-goal-reward.module#TsubasaMQuestGoalRewardModule'
      },
      {
        path: 'm-quest-reward-group',
        loadChildren: './m-quest-reward-group/m-quest-reward-group.module#TsubasaMQuestRewardGroupModule'
      },
      {
        path: 'm-quest-special-reward',
        loadChildren: './m-quest-special-reward/m-quest-special-reward.module#TsubasaMQuestSpecialRewardModule'
      },
      {
        path: 'm-quest-stage',
        loadChildren: './m-quest-stage/m-quest-stage.module#TsubasaMQuestStageModule'
      },
      {
        path: 'm-quest-stage-condition',
        loadChildren: './m-quest-stage-condition/m-quest-stage-condition.module#TsubasaMQuestStageConditionModule'
      },
      {
        path: 'm-quest-stage-condition-description',
        loadChildren:
          './m-quest-stage-condition-description/m-quest-stage-condition-description.module#TsubasaMQuestStageConditionDescriptionModule'
      },
      {
        path: 'm-quest-stage-reward',
        loadChildren: './m-quest-stage-reward/m-quest-stage-reward.module#TsubasaMQuestStageRewardModule'
      },
      {
        path: 'm-quest-ticket',
        loadChildren: './m-quest-ticket/m-quest-ticket.module#TsubasaMQuestTicketModule'
      },
      {
        path: 'm-quest-tsubasa-point-reward',
        loadChildren: './m-quest-tsubasa-point-reward/m-quest-tsubasa-point-reward.module#TsubasaMQuestTsubasaPointRewardModule'
      },
      {
        path: 'm-quest-world',
        loadChildren: './m-quest-world/m-quest-world.module#TsubasaMQuestWorldModule'
      },
      {
        path: 'm-recommend-formation-filter',
        loadChildren: './m-recommend-formation-filter/m-recommend-formation-filter.module#TsubasaMRecommendFormationFilterModule'
      },
      {
        path: 'm-recommend-formation-filter-element',
        loadChildren:
          './m-recommend-formation-filter-element/m-recommend-formation-filter-element.module#TsubasaMRecommendFormationFilterElementModule'
      },
      {
        path: 'm-recommend-shop-message',
        loadChildren: './m-recommend-shop-message/m-recommend-shop-message.module#TsubasaMRecommendShopMessageModule'
      },
      {
        path: 'm-regulated-league-ranking-reward',
        loadChildren:
          './m-regulated-league-ranking-reward/m-regulated-league-ranking-reward.module#TsubasaMRegulatedLeagueRankingRewardModule'
      },
      {
        path: 'm-regulation-match-tutorial-message',
        loadChildren:
          './m-regulation-match-tutorial-message/m-regulation-match-tutorial-message.module#TsubasaMRegulationMatchTutorialMessageModule'
      },
      {
        path: 'm-rival-encount-cutin',
        loadChildren: './m-rival-encount-cutin/m-rival-encount-cutin.module#TsubasaMRivalEncountCutinModule'
      },
      {
        path: 'm-scene-tutorial-message',
        loadChildren: './m-scene-tutorial-message/m-scene-tutorial-message.module#TsubasaMSceneTutorialMessageModule'
      },
      {
        path: 'm-sell-card-coin',
        loadChildren: './m-sell-card-coin/m-sell-card-coin.module#TsubasaMSellCardCoinModule'
      },
      {
        path: 'm-sell-card-medal',
        loadChildren: './m-sell-card-medal/m-sell-card-medal.module#TsubasaMSellCardMedalModule'
      },
      {
        path: 'm-shoot',
        loadChildren: './m-shoot/m-shoot.module#TsubasaMShootModule'
      },
      {
        path: 'm-situation',
        loadChildren: './m-situation/m-situation.module#TsubasaMSituationModule'
      },
      {
        path: 'm-situation-announce',
        loadChildren: './m-situation-announce/m-situation-announce.module#TsubasaMSituationAnnounceModule'
      },
      {
        path: 'm-sound-bank',
        loadChildren: './m-sound-bank/m-sound-bank.module#TsubasaMSoundBankModule'
      },
      {
        path: 'm-sound-bank-event',
        loadChildren: './m-sound-bank-event/m-sound-bank-event.module#TsubasaMSoundBankEventModule'
      },
      {
        path: 'm-stage-story',
        loadChildren: './m-stage-story/m-stage-story.module#TsubasaMStageStoryModule'
      },
      {
        path: 'm-stamp',
        loadChildren: './m-stamp/m-stamp.module#TsubasaMStampModule'
      },
      {
        path: 'm-store-review-url',
        loadChildren: './m-store-review-url/m-store-review-url.module#TsubasaMStoreReviewUrlModule'
      },
      {
        path: 'm-story-resource-mapping',
        loadChildren: './m-story-resource-mapping/m-story-resource-mapping.module#TsubasaMStoryResourceMappingModule'
      },
      {
        path: 'm-target-action-group',
        loadChildren: './m-target-action-group/m-target-action-group.module#TsubasaMTargetActionGroupModule'
      },
      {
        path: 'm-target-action-type-group',
        loadChildren: './m-target-action-type-group/m-target-action-type-group.module#TsubasaMTargetActionTypeGroupModule'
      },
      {
        path: 'm-target-character-group',
        loadChildren: './m-target-character-group/m-target-character-group.module#TsubasaMTargetCharacterGroupModule'
      },
      {
        path: 'm-target-formation-group',
        loadChildren: './m-target-formation-group/m-target-formation-group.module#TsubasaMTargetFormationGroupModule'
      },
      {
        path: 'm-target-nationality-group',
        loadChildren: './m-target-nationality-group/m-target-nationality-group.module#TsubasaMTargetNationalityGroupModule'
      },
      {
        path: 'm-target-playable-card-group',
        loadChildren: './m-target-playable-card-group/m-target-playable-card-group.module#TsubasaMTargetPlayableCardGroupModule'
      },
      {
        path: 'm-target-rarity-group',
        loadChildren: './m-target-rarity-group/m-target-rarity-group.module#TsubasaMTargetRarityGroupModule'
      },
      {
        path: 'm-target-team-group',
        loadChildren: './m-target-team-group/m-target-team-group.module#TsubasaMTargetTeamGroupModule'
      },
      {
        path: 'm-target-trigger-effect-group',
        loadChildren: './m-target-trigger-effect-group/m-target-trigger-effect-group.module#TsubasaMTargetTriggerEffectGroupModule'
      },
      {
        path: 'm-team-effect-base',
        loadChildren: './m-team-effect-base/m-team-effect-base.module#TsubasaMTeamEffectBaseModule'
      },
      {
        path: 'm-team-effect-level',
        loadChildren: './m-team-effect-level/m-team-effect-level.module#TsubasaMTeamEffectLevelModule'
      },
      {
        path: 'm-team-effect-rarity',
        loadChildren: './m-team-effect-rarity/m-team-effect-rarity.module#TsubasaMTeamEffectRarityModule'
      },
      {
        path: 'm-ticket-quest-stage',
        loadChildren: './m-ticket-quest-stage/m-ticket-quest-stage.module#TsubasaMTicketQuestStageModule'
      },
      {
        path: 'm-ticket-quest-stage-reward',
        loadChildren: './m-ticket-quest-stage-reward/m-ticket-quest-stage-reward.module#TsubasaMTicketQuestStageRewardModule'
      },
      {
        path: 'm-ticket-quest-tsubasa-point-reward',
        loadChildren:
          './m-ticket-quest-tsubasa-point-reward/m-ticket-quest-tsubasa-point-reward.module#TsubasaMTicketQuestTsubasaPointRewardModule'
      },
      {
        path: 'm-ticket-quest-world',
        loadChildren: './m-ticket-quest-world/m-ticket-quest-world.module#TsubasaMTicketQuestWorldModule'
      },
      {
        path: 'm-timeattack-quest-stage',
        loadChildren: './m-timeattack-quest-stage/m-timeattack-quest-stage.module#TsubasaMTimeattackQuestStageModule'
      },
      {
        path: 'm-timeattack-quest-stage-reward',
        loadChildren: './m-timeattack-quest-stage-reward/m-timeattack-quest-stage-reward.module#TsubasaMTimeattackQuestStageRewardModule'
      },
      {
        path: 'm-timeattack-quest-world',
        loadChildren: './m-timeattack-quest-world/m-timeattack-quest-world.module#TsubasaMTimeattackQuestWorldModule'
      },
      {
        path: 'm-timeattack-ranking-reward',
        loadChildren: './m-timeattack-ranking-reward/m-timeattack-ranking-reward.module#TsubasaMTimeattackRankingRewardModule'
      },
      {
        path: 'm-timeattack-ranking-reward-group',
        loadChildren:
          './m-timeattack-ranking-reward-group/m-timeattack-ranking-reward-group.module#TsubasaMTimeattackRankingRewardGroupModule'
      },
      {
        path: 'm-tips',
        loadChildren: './m-tips/m-tips.module#TsubasaMTipsModule'
      },
      {
        path: 'm-training-card',
        loadChildren: './m-training-card/m-training-card.module#TsubasaMTrainingCardModule'
      },
      {
        path: 'm-training-cost',
        loadChildren: './m-training-cost/m-training-cost.module#TsubasaMTrainingCostModule'
      },
      {
        path: 'm-trigger-effect-base',
        loadChildren: './m-trigger-effect-base/m-trigger-effect-base.module#TsubasaMTriggerEffectBaseModule'
      },
      {
        path: 'm-trigger-effect-range',
        loadChildren: './m-trigger-effect-range/m-trigger-effect-range.module#TsubasaMTriggerEffectRangeModule'
      },
      {
        path: 'm-tsubasa-point',
        loadChildren: './m-tsubasa-point/m-tsubasa-point.module#TsubasaMTsubasaPointModule'
      },
      {
        path: 'm-tutorial-message',
        loadChildren: './m-tutorial-message/m-tutorial-message.module#TsubasaMTutorialMessageModule'
      },
      {
        path: 'm-uniform-bottom',
        loadChildren: './m-uniform-bottom/m-uniform-bottom.module#TsubasaMUniformBottomModule'
      },
      {
        path: 'm-uniform-original-set',
        loadChildren: './m-uniform-original-set/m-uniform-original-set.module#TsubasaMUniformOriginalSetModule'
      },
      {
        path: 'm-uniform-up',
        loadChildren: './m-uniform-up/m-uniform-up.module#TsubasaMUniformUpModule'
      },
      {
        path: 'm-user-policy-update-date',
        loadChildren: './m-user-policy-update-date/m-user-policy-update-date.module#TsubasaMUserPolicyUpdateDateModule'
      },
      {
        path: 'm-user-rank',
        loadChildren: './m-user-rank/m-user-rank.module#TsubasaMUserRankModule'
      },
      {
        path: 'm-weekly-quest-stage',
        loadChildren: './m-weekly-quest-stage/m-weekly-quest-stage.module#TsubasaMWeeklyQuestStageModule'
      },
      {
        path: 'm-weekly-quest-stage-reward',
        loadChildren: './m-weekly-quest-stage-reward/m-weekly-quest-stage-reward.module#TsubasaMWeeklyQuestStageRewardModule'
      },
      {
        path: 'm-weekly-quest-tsubasa-point-reward',
        loadChildren:
          './m-weekly-quest-tsubasa-point-reward/m-weekly-quest-tsubasa-point-reward.module#TsubasaMWeeklyQuestTsubasaPointRewardModule'
      },
      {
        path: 'm-weekly-quest-world',
        loadChildren: './m-weekly-quest-world/m-weekly-quest-world.module#TsubasaMWeeklyQuestWorldModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaEntityModule {}
