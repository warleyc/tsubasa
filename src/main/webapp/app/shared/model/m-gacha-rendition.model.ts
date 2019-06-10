export interface IMGachaRendition {
  id?: number;
  mainPrefabName?: any;
  resultExpectedUpPrefabName?: any;
  resultQuestionPrefabName?: any;
  soundSwitchEventName?: any;
}

export class MGachaRendition implements IMGachaRendition {
  constructor(
    public id?: number,
    public mainPrefabName?: any,
    public resultExpectedUpPrefabName?: any,
    public resultQuestionPrefabName?: any,
    public soundSwitchEventName?: any
  ) {}
}
