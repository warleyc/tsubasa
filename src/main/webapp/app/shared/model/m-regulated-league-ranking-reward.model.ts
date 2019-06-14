export interface IMRegulatedLeagueRankingReward {
  id?: number;
  regulatedLeagueId?: number;
  leagueHierarchy?: number;
  rankTo?: number;
  rewardGroupId?: number;
}

export class MRegulatedLeagueRankingReward implements IMRegulatedLeagueRankingReward {
  constructor(
    public id?: number,
    public regulatedLeagueId?: number,
    public leagueHierarchy?: number,
    public rankTo?: number,
    public rewardGroupId?: number
  ) {}
}
