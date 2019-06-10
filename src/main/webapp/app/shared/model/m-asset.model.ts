export interface IMAsset {
  id?: number;
  assetBundleName?: any;
  tag?: any;
  dependencies?: any;
  i18n?: number;
  platform?: any;
  packName?: any;
  head?: number;
  size?: number;
  key1?: number;
  key2?: number;
}

export class MAsset implements IMAsset {
  constructor(
    public id?: number,
    public assetBundleName?: any,
    public tag?: any,
    public dependencies?: any,
    public i18n?: number,
    public platform?: any,
    public packName?: any,
    public head?: number,
    public size?: number,
    public key1?: number,
    public key2?: number
  ) {}
}
