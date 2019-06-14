export interface IMTicketQuestStageReward {
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
  goalRewardGroupId?: number;
}

export class MTicketQuestStageReward implements IMTicketQuestStageReward {
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
    public specialRewardAmount?: number,
    public goalRewardGroupId?: number
  ) {}
}
