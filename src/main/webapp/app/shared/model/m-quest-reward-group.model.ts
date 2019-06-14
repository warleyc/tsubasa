export interface IMQuestRewardGroup {
  id?: number;
  groupId?: number;
  rate?: number;
  rank?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MQuestRewardGroup implements IMQuestRewardGroup {
  constructor(
    public id?: number,
    public groupId?: number,
    public rate?: number,
    public rank?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
