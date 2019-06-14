export interface IMMarathonRankingRewardGroup {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MMarathonRankingRewardGroup implements IMMarathonRankingRewardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
