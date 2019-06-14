import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';

export interface IMModelCard {
  id?: number;
  hairModel?: number;
  hairTexture?: number;
  headModel?: number;
  headTexture?: number;
  skinTexture?: number;
  shoesModel?: number;
  shoesTexture?: number;
  globeTexture?: number;
  uniqueModel?: number;
  uniqueTexture?: number;
  dressingTypeUp?: number;
  dressingTypeBottom?: number;
  height?: number;
  width?: number;
  mPlayableCards?: IMPlayableCard[];
}

export class MModelCard implements IMModelCard {
  constructor(
    public id?: number,
    public hairModel?: number,
    public hairTexture?: number,
    public headModel?: number,
    public headTexture?: number,
    public skinTexture?: number,
    public shoesModel?: number,
    public shoesTexture?: number,
    public globeTexture?: number,
    public uniqueModel?: number,
    public uniqueTexture?: number,
    public dressingTypeUp?: number,
    public dressingTypeBottom?: number,
    public height?: number,
    public width?: number,
    public mPlayableCards?: IMPlayableCard[]
  ) {}
}
