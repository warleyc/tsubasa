export interface IMRivalEncountCutin {
  id?: number;
  offenseCharacterId?: number;
  offenseTeamId?: number;
  defenseCharacterId?: number;
  defenseTeamId?: number;
  cutinType?: number;
  chapter1Text?: any;
  chapter1SoundEvent?: any;
  chapter2Text?: any;
  chapter2SoundEvent?: any;
}

export class MRivalEncountCutin implements IMRivalEncountCutin {
  constructor(
    public id?: number,
    public offenseCharacterId?: number,
    public offenseTeamId?: number,
    public defenseCharacterId?: number,
    public defenseTeamId?: number,
    public cutinType?: number,
    public chapter1Text?: any,
    public chapter1SoundEvent?: any,
    public chapter2Text?: any,
    public chapter2SoundEvent?: any
  ) {}
}
