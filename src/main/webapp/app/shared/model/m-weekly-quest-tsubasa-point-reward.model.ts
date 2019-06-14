export interface IMWeeklyQuestTsubasaPointReward {
  id?: number;
  stageId?: number;
  tsubasaPoint?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MWeeklyQuestTsubasaPointReward implements IMWeeklyQuestTsubasaPointReward {
  constructor(
    public id?: number,
    public stageId?: number,
    public tsubasaPoint?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
