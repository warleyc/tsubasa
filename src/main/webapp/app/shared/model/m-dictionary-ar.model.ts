export interface IMDictionaryAr {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryAr implements IMDictionaryAr {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
