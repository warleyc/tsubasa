export interface IMMarathonLoopReward {
  id?: number;
  eventId?: number;
  loopPoint?: number;
}

export class MMarathonLoopReward implements IMMarathonLoopReward {
  constructor(public id?: number, public eventId?: number, public loopPoint?: number) {}
}
