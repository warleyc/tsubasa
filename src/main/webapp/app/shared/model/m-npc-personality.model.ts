export interface IMNpcPersonality {
  id?: number;
  passingTargetRate?: number;
  actionSkillRate?: number;
  dribbleMagnification?: number;
  passingMagnification?: number;
  onetwoMagnification?: number;
  shootMagnification?: number;
  volleyShootMagnification?: number;
  headingShootMagnification?: number;
  tackleMagnification?: number;
  blockMagnification?: number;
  passCutMagnification?: number;
  clearMagnification?: number;
  competeMagnification?: number;
  trapMagnification?: number;
}

export class MNpcPersonality implements IMNpcPersonality {
  constructor(
    public id?: number,
    public passingTargetRate?: number,
    public actionSkillRate?: number,
    public dribbleMagnification?: number,
    public passingMagnification?: number,
    public onetwoMagnification?: number,
    public shootMagnification?: number,
    public volleyShootMagnification?: number,
    public headingShootMagnification?: number,
    public tackleMagnification?: number,
    public blockMagnification?: number,
    public passCutMagnification?: number,
    public clearMagnification?: number,
    public competeMagnification?: number,
    public trapMagnification?: number
  ) {}
}
