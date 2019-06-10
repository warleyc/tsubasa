export interface IMDictionaryDe {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryDe implements IMDictionaryDe {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
