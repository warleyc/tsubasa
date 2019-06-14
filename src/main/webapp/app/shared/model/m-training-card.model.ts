export interface IMTrainingCard {
  id?: number;
  name?: any;
  shortName?: any;
  description?: any;
  rarity?: number;
  attribute?: number;
  gainExp?: number;
  coin?: number;
  sellMedalId?: number;
  plusDribble?: number;
  plusShoot?: number;
  plusPass?: number;
  plusTackle?: number;
  plusBlock?: number;
  plusIntercept?: number;
  plusSpeed?: number;
  plusPower?: number;
  plusTechnique?: number;
  plusPunching?: number;
  plusCatching?: number;
  thumbnailAssetsId?: number;
  cardIllustAssetsId?: number;
  idId?: number;
}

export class MTrainingCard implements IMTrainingCard {
  constructor(
    public id?: number,
    public name?: any,
    public shortName?: any,
    public description?: any,
    public rarity?: number,
    public attribute?: number,
    public gainExp?: number,
    public coin?: number,
    public sellMedalId?: number,
    public plusDribble?: number,
    public plusShoot?: number,
    public plusPass?: number,
    public plusTackle?: number,
    public plusBlock?: number,
    public plusIntercept?: number,
    public plusSpeed?: number,
    public plusPower?: number,
    public plusTechnique?: number,
    public plusPunching?: number,
    public plusCatching?: number,
    public thumbnailAssetsId?: number,
    public cardIllustAssetsId?: number,
    public idId?: number
  ) {}
}
