export interface IMQuestTsubasaPointReward {
  id?: number;
  stageId?: number;
  tsubasaPoint?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MQuestTsubasaPointReward implements IMQuestTsubasaPointReward {
  constructor(
    public id?: number,
    public stageId?: number,
    public tsubasaPoint?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
