export interface IMTimeattackRankingReward {
  id?: number;
  eventId?: number;
  rankingFrom?: number;
  rankingTo?: number;
  rewardGroupId?: number;
}

export class MTimeattackRankingReward implements IMTimeattackRankingReward {
  constructor(
    public id?: number,
    public eventId?: number,
    public rankingFrom?: number,
    public rankingTo?: number,
    public rewardGroupId?: number
  ) {}
}
