export interface IMGachaRenditionSwipeIcon {
  id?: number;
  isSsr?: number;
  isROrLess?: number;
  weight?: number;
  swipeIconPrefabName?: any;
}

export class MGachaRenditionSwipeIcon implements IMGachaRenditionSwipeIcon {
  constructor(
    public id?: number,
    public isSsr?: number,
    public isROrLess?: number,
    public weight?: number,
    public swipeIconPrefabName?: any
  ) {}
}
