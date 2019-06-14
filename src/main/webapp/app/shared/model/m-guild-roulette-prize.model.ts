export interface IMGuildRoulettePrize {
  id?: number;
  rank?: number;
  contentType?: number;
  contentId?: number;
  contentAmount?: number;
}

export class MGuildRoulettePrize implements IMGuildRoulettePrize {
  constructor(
    public id?: number,
    public rank?: number,
    public contentType?: number,
    public contentId?: number,
    public contentAmount?: number
  ) {}
}
