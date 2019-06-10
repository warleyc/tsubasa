export interface IMEncountersCommandBranch {
  id?: number;
  ballFloatCondition?: number;
  condition?: number;
  encountersType?: number;
  isSuccess?: number;
  commandType?: number;
  successRate?: number;
  looseBallRate?: number;
  touchLightlyRate?: number;
  postRate?: number;
}

export class MEncountersCommandBranch implements IMEncountersCommandBranch {
  constructor(
    public id?: number,
    public ballFloatCondition?: number,
    public condition?: number,
    public encountersType?: number,
    public isSuccess?: number,
    public commandType?: number,
    public successRate?: number,
    public looseBallRate?: number,
    public touchLightlyRate?: number,
    public postRate?: number
  ) {}
}
