export interface IMGachaRenditionAfterInputCutInTextColor {
  id?: number;
  isSsr?: number;
  weight?: number;
  color?: any;
}

export class MGachaRenditionAfterInputCutInTextColor implements IMGachaRenditionAfterInputCutInTextColor {
  constructor(public id?: number, public isSsr?: number, public weight?: number, public color?: any) {}
}
