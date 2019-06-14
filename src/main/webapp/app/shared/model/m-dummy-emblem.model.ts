export interface IMDummyEmblem {
  id?: number;
  backgroundId?: number;
  backgroundColor?: any;
  upperId?: number;
  upperColor?: any;
  middleId?: number;
  middleColor?: any;
  lowerId?: number;
  lowerColor?: any;
  memblempartsId?: number;
}

export class MDummyEmblem implements IMDummyEmblem {
  constructor(
    public id?: number,
    public backgroundId?: number,
    public backgroundColor?: any,
    public upperId?: number,
    public upperColor?: any,
    public middleId?: number,
    public middleColor?: any,
    public lowerId?: number,
    public lowerColor?: any,
    public memblempartsId?: number
  ) {}
}
