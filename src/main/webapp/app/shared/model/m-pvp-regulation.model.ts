export interface IMPvpRegulation {
  id?: number;
  startAt?: number;
  endAt?: number;
  matchOptionId?: number;
  deckConditionId?: number;
  ruleTutorialId?: number;
  idId?: number;
}

export class MPvpRegulation implements IMPvpRegulation {
  constructor(
    public id?: number,
    public startAt?: number,
    public endAt?: number,
    public matchOptionId?: number,
    public deckConditionId?: number,
    public ruleTutorialId?: number,
    public idId?: number
  ) {}
}
