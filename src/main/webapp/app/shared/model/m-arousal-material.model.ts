export interface IMArousalMaterial {
  id?: number;
  groupId?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
  mainActionLevel?: number;
  description?: any;
}

export class MArousalMaterial implements IMArousalMaterial {
  constructor(
    public id?: number,
    public groupId?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number,
    public mainActionLevel?: number,
    public description?: any
  ) {}
}
