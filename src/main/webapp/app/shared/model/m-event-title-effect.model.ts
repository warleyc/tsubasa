export interface IMEventTitleEffect {
  id?: number;
  effectPath?: any;
  bgmSoundEvent?: any;
  seSoundEvent?: any;
  voiceSoundEventSuffixes?: any;
  copyrightText?: any;
  startAt?: number;
  endAt?: number;
}

export class MEventTitleEffect implements IMEventTitleEffect {
  constructor(
    public id?: number,
    public effectPath?: any,
    public bgmSoundEvent?: any,
    public seSoundEvent?: any,
    public voiceSoundEventSuffixes?: any,
    public copyrightText?: any,
    public startAt?: number,
    public endAt?: number
  ) {}
}
