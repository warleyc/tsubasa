export interface IMUserRank {
  id?: number;
  rank?: number;
  exp?: number;
  questAp?: number;
  maxFriendCount?: number;
  rankupSerif?: any;
  charaAssetName?: any;
  voiceCharaId?: any;
}

export class MUserRank implements IMUserRank {
  constructor(
    public id?: number,
    public rank?: number,
    public exp?: number,
    public questAp?: number,
    public maxFriendCount?: number,
    public rankupSerif?: any,
    public charaAssetName?: any,
    public voiceCharaId?: any
  ) {}
}
