export interface IMEncountersCommandCompatibility {
  id?: number;
  encountersType?: number;
  offenseCommandType?: number;
  defenseCommandType?: number;
  offenseMagnification?: number;
  defenseMagnification?: number;
}

export class MEncountersCommandCompatibility implements IMEncountersCommandCompatibility {
  constructor(
    public id?: number,
    public encountersType?: number,
    public offenseCommandType?: number,
    public defenseCommandType?: number,
    public offenseMagnification?: number,
    public defenseMagnification?: number
  ) {}
}
