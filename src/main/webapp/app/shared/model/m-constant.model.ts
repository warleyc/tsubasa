export interface IMConstant {
  id?: number;
  key?: any;
  value?: number;
}

export class MConstant implements IMConstant {
  constructor(public id?: number, public key?: any, public value?: number) {}
}
