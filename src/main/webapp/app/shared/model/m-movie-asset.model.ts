export interface IMMovieAsset {
  id?: number;
  lang?: number;
  name?: any;
  size?: number;
  version?: number;
  type?: number;
}

export class MMovieAsset implements IMMovieAsset {
  constructor(
    public id?: number,
    public lang?: number,
    public name?: any,
    public size?: number,
    public version?: number,
    public type?: number
  ) {}
}
