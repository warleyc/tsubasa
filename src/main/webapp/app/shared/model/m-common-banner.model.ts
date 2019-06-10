export interface IMCommonBanner {
  id?: number;
  assetName?: any;
  startAt?: number;
  endAt?: number;
  labelEndAt?: number;
  sceneTransition?: number;
  sceneTransitionParam?: any;
  orderNum?: number;
  appealType?: number;
  appealContentId?: number;
}

export class MCommonBanner implements IMCommonBanner {
  constructor(
    public id?: number,
    public assetName?: any,
    public startAt?: number,
    public endAt?: number,
    public labelEndAt?: number,
    public sceneTransition?: number,
    public sceneTransitionParam?: any,
    public orderNum?: number,
    public appealType?: number,
    public appealContentId?: number
  ) {}
}
