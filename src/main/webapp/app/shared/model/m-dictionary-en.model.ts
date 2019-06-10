export interface IMDictionaryEn {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryEn implements IMDictionaryEn {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
