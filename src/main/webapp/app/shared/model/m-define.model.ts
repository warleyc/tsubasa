export interface IMDefine {
  id?: number;
  key?: any;
  value?: number;
}

export class MDefine implements IMDefine {
  constructor(public id?: number, public key?: any, public value?: number) {}
}
