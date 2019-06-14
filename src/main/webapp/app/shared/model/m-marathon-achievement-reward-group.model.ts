export interface IMMarathonAchievementRewardGroup {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MMarathonAchievementRewardGroup implements IMMarathonAchievementRewardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
