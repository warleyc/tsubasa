export interface IMQuestStageReward {
  id?: number;
  stageId?: number;
  exp?: number;
  coin?: number;
  guildPoint?: number;
  clearRewardGroupId?: number;
  clearRewardWeightId?: number;
  achievementRewardGroupId?: number;
  coopGroupId?: number;
  specialRewardGroupId?: number;
  specialRewardAmount?: number;
}

export class MQuestStageReward implements IMQuestStageReward {
  constructor(
    public id?: number,
    public stageId?: number,
    public exp?: number,
    public coin?: number,
    public guildPoint?: number,
    public clearRewardGroupId?: number,
    public clearRewardWeightId?: number,
    public achievementRewardGroupId?: number,
    public coopGroupId?: number,
    public specialRewardGroupId?: number,
    public specialRewardAmount?: number
  ) {}
}
