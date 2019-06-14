export interface IMLoginBonusIncentive {
  id?: number;
  roundId?: number;
  day?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
  presentDetail?: any;
  isDecorated?: number;
}

export class MLoginBonusIncentive implements IMLoginBonusIncentive {
  constructor(
    public id?: number,
    public roundId?: number,
    public day?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number,
    public presentDetail?: any,
    public isDecorated?: number
  ) {}
}
