export interface IMCardPlayableAssets {
  id?: number;
  cutinImageAssetName?: any;
  soundEventSuffix?: any;
}

export class MCardPlayableAssets implements IMCardPlayableAssets {
  constructor(public id?: number, public cutinImageAssetName?: any, public soundEventSuffix?: any) {}
}
