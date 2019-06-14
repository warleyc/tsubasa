export interface IMMarathonRankingReward {
  id?: number;
  eventId?: number;
  rankingFrom?: number;
  rankingTo?: number;
  rewardGroupId?: number;
}

export class MMarathonRankingReward implements IMMarathonRankingReward {
  constructor(
    public id?: number,
    public eventId?: number,
    public rankingFrom?: number,
    public rankingTo?: number,
    public rewardGroupId?: number
  ) {}
}
