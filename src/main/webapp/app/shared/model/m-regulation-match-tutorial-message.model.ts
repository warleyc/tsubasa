export interface IMRegulationMatchTutorialMessage {
  id?: number;
  ruleId?: number;
  orderNum?: number;
  position?: number;
  message?: any;
  assetName?: any;
  title?: any;
}

export class MRegulationMatchTutorialMessage implements IMRegulationMatchTutorialMessage {
  constructor(
    public id?: number,
    public ruleId?: number,
    public orderNum?: number,
    public position?: number,
    public message?: any,
    public assetName?: any,
    public title?: any
  ) {}
}
