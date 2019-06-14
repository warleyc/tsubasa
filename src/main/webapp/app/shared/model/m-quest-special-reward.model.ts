export interface IMQuestSpecialReward {
  id?: number;
  groupId?: number;
  weight?: number;
  rank?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MQuestSpecialReward implements IMQuestSpecialReward {
  constructor(
    public id?: number,
    public groupId?: number,
    public weight?: number,
    public rank?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
