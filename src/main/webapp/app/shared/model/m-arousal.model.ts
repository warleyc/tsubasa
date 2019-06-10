export interface IMArousal {
  id?: number;
  beforeId?: number;
  afterId?: number;
  cost?: number;
  materialGroupId?: number;
  idId?: number;
}

export class MArousal implements IMArousal {
  constructor(
    public id?: number,
    public beforeId?: number,
    public afterId?: number,
    public cost?: number,
    public materialGroupId?: number,
    public idId?: number
  ) {}
}
