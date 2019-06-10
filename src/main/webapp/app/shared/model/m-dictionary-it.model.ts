export interface IMDictionaryIt {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryIt implements IMDictionaryIt {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
