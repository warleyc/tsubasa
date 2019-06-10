export interface IMDictionaryPt {
  id?: number;
  key?: any;
  message?: any;
}

export class MDictionaryPt implements IMDictionaryPt {
  constructor(public id?: number, public key?: any, public message?: any) {}
}
