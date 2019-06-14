export interface IMMarathonAchievementReward {
  id?: number;
  eventId?: number;
  eventPoint?: number;
  rewardGroupId?: number;
}

export class MMarathonAchievementReward implements IMMarathonAchievementReward {
  constructor(public id?: number, public eventId?: number, public eventPoint?: number, public rewardGroupId?: number) {}
}
