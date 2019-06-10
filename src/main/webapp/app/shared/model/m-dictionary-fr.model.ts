export interface IMDictionaryFr {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryFr implements IMDictionaryFr {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
