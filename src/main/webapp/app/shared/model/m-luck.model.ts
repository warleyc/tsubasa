export interface IMLuck {
  id?: number;
  targetAttribute?: number;
  targetCharacterGroupId?: number;
  targetTeamGroupId?: number;
  targetNationalityGroupId?: number;
  luckRateGroupId?: number;
  description?: any;
}

export class MLuck implements IMLuck {
  constructor(
    public id?: number,
    public targetAttribute?: number,
    public targetCharacterGroupId?: number,
    public targetTeamGroupId?: number,
    public targetNationalityGroupId?: number,
    public luckRateGroupId?: number,
    public description?: any
  ) {}
}
