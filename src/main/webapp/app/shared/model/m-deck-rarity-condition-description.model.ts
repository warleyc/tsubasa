export interface IMDeckRarityConditionDescription {
  id?: number;
  rarityGroupId?: number;
  countType?: number;
  isStarting?: number;
  description?: any;
  iconName?: any;
  smallIconName?: any;
}

export class MDeckRarityConditionDescription implements IMDeckRarityConditionDescription {
  constructor(
    public id?: number,
    public rarityGroupId?: number,
    public countType?: number,
    public isStarting?: number,
    public description?: any,
    public iconName?: any,
    public smallIconName?: any
  ) {}
}
