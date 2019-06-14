export interface IMTargetNationalityGroup {
  id?: number;
  groupId?: number;
  nationalityId?: number;
  mnationalityId?: number;
}

export class MTargetNationalityGroup implements IMTargetNationalityGroup {
  constructor(public id?: number, public groupId?: number, public nationalityId?: number, public mnationalityId?: number) {}
}
