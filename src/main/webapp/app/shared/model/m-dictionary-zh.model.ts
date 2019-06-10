export interface IMDictionaryZh {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryZh implements IMDictionaryZh {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
