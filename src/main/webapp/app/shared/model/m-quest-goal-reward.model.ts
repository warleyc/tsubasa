export interface IMQuestGoalReward {
  id?: number;
  groupId?: number;
  weight?: number;
  assetName?: any;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MQuestGoalReward implements IMQuestGoalReward {
  constructor(
    public id?: number,
    public groupId?: number,
    public weight?: number,
    public assetName?: any,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
