export interface IMActionSkillHolderCardCt {
  id?: number;
  targetCharaId?: number;
  actionMasterId?: number;
  actionSkillExp?: number;
  name?: any;
  description?: any;
  idId?: number;
}

export class MActionSkillHolderCardCt implements IMActionSkillHolderCardCt {
  constructor(
    public id?: number,
    public targetCharaId?: number,
    public actionMasterId?: number,
    public actionSkillExp?: number,
    public name?: any,
    public description?: any,
    public idId?: number
  ) {}
}
