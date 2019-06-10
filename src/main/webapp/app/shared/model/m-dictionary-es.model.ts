export interface IMDictionaryEs {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryEs implements IMDictionaryEs {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
