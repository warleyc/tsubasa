export interface IMQuestAchievementGroup {
  id?: number;
  groupId?: number;
  achievementType?: number;
  rank?: number;
  weight?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MQuestAchievementGroup implements IMQuestAchievementGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public achievementType?: number,
    public rank?: number,
    public weight?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
