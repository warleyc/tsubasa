export interface IMModelQuestStage {
  id?: number;
  stageId?: number;
  image?: any;
  modelName?: any;
  bgmOffencing?: any;
  bgmDefencing?: any;
  bgmHurrying?: any;
}

export class MModelQuestStage implements IMModelQuestStage {
  constructor(
    public id?: number,
    public stageId?: number,
    public image?: any,
    public modelName?: any,
    public bgmOffencing?: any,
    public bgmDefencing?: any,
    public bgmHurrying?: any
  ) {}
}
