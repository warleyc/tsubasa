export interface IMCardPowerupActionSkill {
  id?: number;
  name?: any;
  shortName?: any;
  description?: any;
  rarity?: number;
  attribute?: number;
  actionRarity?: number;
  gainActionExp?: number;
  coin?: number;
  sellMedalId?: number;
  thumbnailAssetsId?: number;
  cardIllustAssetsId?: number;
  targetActionCommandType?: number;
  targetCharacterId?: number;
  idId?: number;
}

export class MCardPowerupActionSkill implements IMCardPowerupActionSkill {
  constructor(
    public id?: number,
    public name?: any,
    public shortName?: any,
    public description?: any,
    public rarity?: number,
    public attribute?: number,
    public actionRarity?: number,
    public gainActionExp?: number,
    public coin?: number,
    public sellMedalId?: number,
    public thumbnailAssetsId?: number,
    public cardIllustAssetsId?: number,
    public targetActionCommandType?: number,
    public targetCharacterId?: number,
    public idId?: number
  ) {}
}
