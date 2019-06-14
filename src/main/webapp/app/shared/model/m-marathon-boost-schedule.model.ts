export interface IMMarathonBoostSchedule {
  id?: number;
  eventId?: number;
  boostRatio?: number;
  startAt?: number;
  endAt?: number;
}

export class MMarathonBoostSchedule implements IMMarathonBoostSchedule {
  constructor(public id?: number, public eventId?: number, public boostRatio?: number, public startAt?: number, public endAt?: number) {}
}
