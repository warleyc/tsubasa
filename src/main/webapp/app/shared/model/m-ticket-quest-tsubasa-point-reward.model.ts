export interface IMTicketQuestTsubasaPointReward {
  id?: number;
  stageId?: number;
  tsubasaPoint?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MTicketQuestTsubasaPointReward implements IMTicketQuestTsubasaPointReward {
  constructor(
    public id?: number,
    public stageId?: number,
    public tsubasaPoint?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
