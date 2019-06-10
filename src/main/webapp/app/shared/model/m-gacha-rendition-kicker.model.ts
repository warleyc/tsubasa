export interface IMGachaRenditionKicker {
  id?: number;
  renditionId?: number;
  isManySsr?: number;
  isSsr?: number;
  weight?: number;
  leftModelId?: number;
  leftUniformUpId?: number;
  leftUniformBottomId?: number;
  leftUniformNumber?: number;
  rightModelId?: number;
  rightUniformUpId?: number;
  rightUniformBottomId?: number;
  rightUniformNumber?: number;
  cutInSpriteName?: any;
  leftMessage?: any;
  rightMessage?: any;
  voiceTrigger?: any;
  voiceStartCutIn?: any;
  kickerId?: number;
}

export class MGachaRenditionKicker implements IMGachaRenditionKicker {
  constructor(
    public id?: number,
    public renditionId?: number,
    public isManySsr?: number,
    public isSsr?: number,
    public weight?: number,
    public leftModelId?: number,
    public leftUniformUpId?: number,
    public leftUniformBottomId?: number,
    public leftUniformNumber?: number,
    public rightModelId?: number,
    public rightUniformUpId?: number,
    public rightUniformBottomId?: number,
    public rightUniformNumber?: number,
    public cutInSpriteName?: any,
    public leftMessage?: any,
    public rightMessage?: any,
    public voiceTrigger?: any,
    public voiceStartCutIn?: any,
    public kickerId?: number
  ) {}
}
