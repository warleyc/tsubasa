import { IMPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';

export interface IMPvpWave {
  id?: number;
  startAt?: number;
  endAt?: number;
  isRanking?: number;
  mPvpRankingRewards?: IMPvpRankingReward[];
}

export class MPvpWave implements IMPvpWave {
  constructor(
    public id?: number,
    public startAt?: number,
    public endAt?: number,
    public isRanking?: number,
    public mPvpRankingRewards?: IMPvpRankingReward[]
  ) {}
}
