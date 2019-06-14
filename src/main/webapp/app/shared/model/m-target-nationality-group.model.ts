export interface IMTargetNationalityGroup {
  id?: number;
  groupId?: number;
  nationalityId?: number;
  idId?: number;
}

export class MTargetNationalityGroup implements IMTargetNationalityGroup {
  constructor(public id?: number, public groupId?: number, public nationalityId?: number, public idId?: number) {}
}
