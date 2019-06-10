export interface IMChallengeQuestAchievementReward {
  id?: number;
  worldId?: number;
  stageId?: number;
  rewardGroupId?: number;
}

export class MChallengeQuestAchievementReward implements IMChallengeQuestAchievementReward {
  constructor(public id?: number, public worldId?: number, public stageId?: number, public rewardGroupId?: number) {}
}
