export interface IMDummyOpponent {
  id?: number;
  npcDeckId?: number;
  totalParameter?: number;
  name?: any;
  rank?: number;
  emblemId?: number;
  fpUniformUpId?: number;
  fpUniformUpColor?: any;
  fpUniformBottomId?: number;
  fpUniformBottomColor?: any;
  gkUniformUpId?: number;
  gkUniformUpColor?: any;
  gkUniformBottomId?: number;
  gkUniformBottomColor?: any;
  idId?: number;
}

export class MDummyOpponent implements IMDummyOpponent {
  constructor(
    public id?: number,
    public npcDeckId?: number,
    public totalParameter?: number,
    public name?: any,
    public rank?: number,
    public emblemId?: number,
    public fpUniformUpId?: number,
    public fpUniformUpColor?: any,
    public fpUniformBottomId?: number,
    public fpUniformBottomColor?: any,
    public gkUniformUpId?: number,
    public gkUniformUpColor?: any,
    public gkUniformBottomId?: number,
    public gkUniformBottomColor?: any,
    public idId?: number
  ) {}
}
