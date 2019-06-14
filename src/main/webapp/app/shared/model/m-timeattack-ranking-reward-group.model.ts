export interface IMTimeattackRankingRewardGroup {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MTimeattackRankingRewardGroup implements IMTimeattackRankingRewardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
