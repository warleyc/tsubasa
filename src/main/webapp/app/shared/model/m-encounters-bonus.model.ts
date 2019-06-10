export interface IMEncountersBonus {
  id?: number;
  encountersType?: number;
  isSkillSuccess?: number;
  threshold?: number;
  probabilityBonusValue?: number;
}

export class MEncountersBonus implements IMEncountersBonus {
  constructor(
    public id?: number,
    public encountersType?: number,
    public isSkillSuccess?: number,
    public threshold?: number,
    public probabilityBonusValue?: number
  ) {}
}
