export interface IMHomeMarquee {
  id?: number;
  priority?: number;
  marqueeText?: any;
  startAt?: number;
  endAt?: number;
}

export class MHomeMarquee implements IMHomeMarquee {
  constructor(public id?: number, public priority?: number, public marqueeText?: any, public startAt?: number, public endAt?: number) {}
}
