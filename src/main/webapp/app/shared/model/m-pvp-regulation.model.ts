export interface IMPvpRegulation {
  id?: number;
  startAt?: number;
  endAt?: number;
  matchOptionId?: number;
  deckConditionId?: number;
  ruleTutorialId?: number;
  mmatchoptionId?: number;
}

export class MPvpRegulation implements IMPvpRegulation {
  constructor(
    public id?: number,
    public startAt?: number,
    public endAt?: number,
    public matchOptionId?: number,
    public deckConditionId?: number,
    public ruleTutorialId?: number,
    public mmatchoptionId?: number
  ) {}
}
