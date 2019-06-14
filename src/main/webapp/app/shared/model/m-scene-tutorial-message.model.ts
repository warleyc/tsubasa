export interface IMSceneTutorialMessage {
  id?: number;
  target?: number;
  orderNum?: number;
  position?: number;
  message?: any;
  assetName?: any;
  title?: any;
}

export class MSceneTutorialMessage implements IMSceneTutorialMessage {
  constructor(
    public id?: number,
    public target?: number,
    public orderNum?: number,
    public position?: number,
    public message?: any,
    public assetName?: any,
    public title?: any
  ) {}
}
