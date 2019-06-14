export interface IMMarathonLoopRewardGroup {
  id?: number;
  eventId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
  weight?: number;
}

export class MMarathonLoopRewardGroup implements IMMarathonLoopRewardGroup {
  constructor(
    public id?: number,
    public eventId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number,
    public weight?: number
  ) {}
}
