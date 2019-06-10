export interface IMCutSeqGroup {
  id?: number;
  key?: any;
  param?: any;
  cutSequenceText?: any;
}

export class MCutSeqGroup implements IMCutSeqGroup {
  constructor(public id?: number, public key?: any, public param?: any, public cutSequenceText?: any) {}
}
