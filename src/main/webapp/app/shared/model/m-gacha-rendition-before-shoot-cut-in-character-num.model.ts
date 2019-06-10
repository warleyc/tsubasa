export interface IMGachaRenditionBeforeShootCutInCharacterNum {
  id?: number;
  isManySsr?: number;
  isSsr?: number;
  pattern?: number;
  weight?: number;
  num?: number;
}

export class MGachaRenditionBeforeShootCutInCharacterNum implements IMGachaRenditionBeforeShootCutInCharacterNum {
  constructor(
    public id?: number,
    public isManySsr?: number,
    public isSsr?: number,
    public pattern?: number,
    public weight?: number,
    public num?: number
  ) {}
}
