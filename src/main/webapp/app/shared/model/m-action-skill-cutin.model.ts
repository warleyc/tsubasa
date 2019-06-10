export interface IMActionSkillCutin {
  id?: number;
  actionCutId?: number;
  characterId?: number;
  cutName?: any;
  type?: number;
  startSynchronize?: number;
  finishSynchronize?: number;
  startDelay?: number;
  chapter1Character?: number;
  chapter1Text?: any;
  chapter1SoundEvent?: any;
  chapter2Character?: number;
  chapter2Text?: any;
  chapter2SoundEvent?: any;
  chapter3Character?: number;
  chapter3Text?: any;
  chapter3SoundEvent?: any;
  chapter4Character?: number;
  chapter4Text?: any;
  chapter4SoundEvent?: any;
  chapter5Character?: number;
  chapter5Text?: any;
  chapter5SoundEvent?: any;
  synchronizeText?: any;
  synchronizeSoundEvent?: any;
}

export class MActionSkillCutin implements IMActionSkillCutin {
  constructor(
    public id?: number,
    public actionCutId?: number,
    public characterId?: number,
    public cutName?: any,
    public type?: number,
    public startSynchronize?: number,
    public finishSynchronize?: number,
    public startDelay?: number,
    public chapter1Character?: number,
    public chapter1Text?: any,
    public chapter1SoundEvent?: any,
    public chapter2Character?: number,
    public chapter2Text?: any,
    public chapter2SoundEvent?: any,
    public chapter3Character?: number,
    public chapter3Text?: any,
    public chapter3SoundEvent?: any,
    public chapter4Character?: number,
    public chapter4Text?: any,
    public chapter4SoundEvent?: any,
    public chapter5Character?: number,
    public chapter5Text?: any,
    public chapter5SoundEvent?: any,
    public synchronizeText?: any,
    public synchronizeSoundEvent?: any
  ) {}
}
