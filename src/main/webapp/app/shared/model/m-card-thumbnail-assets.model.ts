import { IMCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';
import { IMTrainingCard } from 'app/shared/model/m-training-card.model';

export interface IMCardThumbnailAssets {
  id?: number;
  thumbnailAssetName?: any;
  thumbnailFrameName?: any;
  thumbnailBackgoundAssetName?: any;
  thumbnailEffectAssetName?: any;
  mCardPowerupActionSkills?: IMCardPowerupActionSkill[];
  mTrainingCards?: IMTrainingCard[];
}

export class MCardThumbnailAssets implements IMCardThumbnailAssets {
  constructor(
    public id?: number,
    public thumbnailAssetName?: any,
    public thumbnailFrameName?: any,
    public thumbnailBackgoundAssetName?: any,
    public thumbnailEffectAssetName?: any,
    public mCardPowerupActionSkills?: IMCardPowerupActionSkill[],
    public mTrainingCards?: IMTrainingCard[]
  ) {}
}
