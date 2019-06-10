export interface IMExtensionSale {
  id?: number;
  menuMessage?: any;
  startAt?: number;
  endAt?: number;
  type?: number;
  addExtension?: number;
}

export class MExtensionSale implements IMExtensionSale {
  constructor(
    public id?: number,
    public menuMessage?: any,
    public startAt?: number,
    public endAt?: number,
    public type?: number,
    public addExtension?: number
  ) {}
}
