export interface IMPvpRankingReward {
  id?: number;
  waveId?: number;
  difficulty?: number;
  rankingFrom?: number;
  rankingTo?: number;
  rewardGroupId?: number;
  mpvpwaveId?: number;
}

export class MPvpRankingReward implements IMPvpRankingReward {
  constructor(
    public id?: number,
    public waveId?: number,
    public difficulty?: number,
    public rankingFrom?: number,
    public rankingTo?: number,
    public rewardGroupId?: number,
    public mpvpwaveId?: number
  ) {}
}
