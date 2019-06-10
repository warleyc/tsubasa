export interface IMColorPalette {
  id?: number;
  color?: any;
  orderNum?: number;
}

export class MColorPalette implements IMColorPalette {
  constructor(public id?: number, public color?: any, public orderNum?: number) {}
}
