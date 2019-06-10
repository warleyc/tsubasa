export interface IMGachaRenditionFirstGimmick {
  id?: number;
  isSsr?: number;
  weight?: number;
  birdNum?: number;
  isTickerTape?: number;
}

export class MGachaRenditionFirstGimmick implements IMGachaRenditionFirstGimmick {
  constructor(public id?: number, public isSsr?: number, public weight?: number, public birdNum?: number, public isTickerTape?: number) {}
}
