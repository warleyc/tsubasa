export interface IMMarathonEffectiveCard {
  id?: number;
  eventId?: number;
  playableCardId?: number;
  rate?: number;
}

export class MMarathonEffectiveCard implements IMMarathonEffectiveCard {
  constructor(public id?: number, public eventId?: number, public playableCardId?: number, public rate?: number) {}
}
