export interface IMGuildTopBanner {
  id?: number;
  assetPath?: any;
  guildBannerType?: number;
  startAt?: number;
  endAt?: number;
}

export class MGuildTopBanner implements IMGuildTopBanner {
  constructor(
    public id?: number,
    public assetPath?: any,
    public guildBannerType?: number,
    public startAt?: number,
    public endAt?: number
  ) {}
}
