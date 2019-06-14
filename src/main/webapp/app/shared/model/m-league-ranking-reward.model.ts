export interface IMLeagueRankingReward {
  id?: number;
  leagueHierarchy?: number;
  rankTo?: number;
  rewardGroupId?: number;
  startAt?: number;
  endAt?: number;
}

export class MLeagueRankingReward implements IMLeagueRankingReward {
  constructor(
    public id?: number,
    public leagueHierarchy?: number,
    public rankTo?: number,
    public rewardGroupId?: number,
    public startAt?: number,
    public endAt?: number
  ) {}
}
