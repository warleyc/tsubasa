export interface IMUserPolicyUpdateDate {
  id?: number;
  updateDate?: number;
}

export class MUserPolicyUpdateDate implements IMUserPolicyUpdateDate {
  constructor(public id?: number, public updateDate?: number) {}
}
