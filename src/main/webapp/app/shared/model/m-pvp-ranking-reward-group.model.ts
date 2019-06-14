export interface IMPvpRankingRewardGroup {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MPvpRankingRewardGroup implements IMPvpRankingRewardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
