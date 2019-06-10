export interface IMDictionaryJa {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryJa implements IMDictionaryJa {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
