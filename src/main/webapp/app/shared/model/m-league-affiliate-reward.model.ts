export interface IMLeagueAffiliateReward {
  id?: number;
  hierarchy?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MLeagueAffiliateReward implements IMLeagueAffiliateReward {
  constructor(
    public id?: number,
    public hierarchy?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
