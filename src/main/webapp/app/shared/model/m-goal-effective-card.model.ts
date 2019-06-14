export interface IMGoalEffectiveCard {
  id?: number;
  eventType?: number;
  eventId?: number;
  playableCardId?: number;
  rate?: number;
}

export class MGoalEffectiveCard implements IMGoalEffectiveCard {
  constructor(
    public id?: number,
    public eventType?: number,
    public eventId?: number,
    public playableCardId?: number,
    public rate?: number
  ) {}
}
