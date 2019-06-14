export interface IMStageStory {
  id?: number;
  stageId?: number;
  eventType?: number;
  mainStoryAsset?: any;
  kickoffStoryAsset?: any;
  halftimeStoryAsset?: any;
  resultStoryAsset?: any;
}

export class MStageStory implements IMStageStory {
  constructor(
    public id?: number,
    public stageId?: number,
    public eventType?: number,
    public mainStoryAsset?: any,
    public kickoffStoryAsset?: any,
    public halftimeStoryAsset?: any,
    public resultStoryAsset?: any
  ) {}
}
