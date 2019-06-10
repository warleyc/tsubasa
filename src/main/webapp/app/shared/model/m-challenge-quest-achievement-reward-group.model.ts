export interface IMChallengeQuestAchievementRewardGroup {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MChallengeQuestAchievementRewardGroup implements IMChallengeQuestAchievementRewardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
