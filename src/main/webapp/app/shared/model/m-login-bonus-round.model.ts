export interface IMLoginBonusRound {
  id?: number;
  roundId?: number;
  bonusType?: number;
  startAt?: number;
  endAt?: number;
  serifSanae?: any;
  serifYayoi?: any;
  serifYoshiko?: any;
  serifMaki?: any;
  sanaeImage?: any;
  yayoiImage?: any;
  yoshikoImage?: any;
  makiImage?: any;
  soundSwitchEventName?: any;
  nextId?: number;
  lastDayAppealComment?: any;
  backgroundImage?: any;
}

export class MLoginBonusRound implements IMLoginBonusRound {
  constructor(
    public id?: number,
    public roundId?: number,
    public bonusType?: number,
    public startAt?: number,
    public endAt?: number,
    public serifSanae?: any,
    public serifYayoi?: any,
    public serifYoshiko?: any,
    public serifMaki?: any,
    public sanaeImage?: any,
    public yayoiImage?: any,
    public yoshikoImage?: any,
    public makiImage?: any,
    public soundSwitchEventName?: any,
    public nextId?: number,
    public lastDayAppealComment?: any,
    public backgroundImage?: any
  ) {}
}
