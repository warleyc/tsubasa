export interface IMMarathonQuestTsubasaPointReward {
  id?: number;
  stageId?: number;
  tsubasaPoint?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MMarathonQuestTsubasaPointReward implements IMMarathonQuestTsubasaPointReward {
  constructor(
    public id?: number,
    public stageId?: number,
    public tsubasaPoint?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
