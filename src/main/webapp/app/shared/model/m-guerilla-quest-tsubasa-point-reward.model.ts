export interface IMGuerillaQuestTsubasaPointReward {
  id?: number;
  stageId?: number;
  tsubasaPoint?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MGuerillaQuestTsubasaPointReward implements IMGuerillaQuestTsubasaPointReward {
  constructor(
    public id?: number,
    public stageId?: number,
    public tsubasaPoint?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
