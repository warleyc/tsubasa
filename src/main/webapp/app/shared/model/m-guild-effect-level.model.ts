export interface IMGuildEffectLevel {
  id?: number;
  effectType?: number;
  level?: number;
  rate?: number;
  rateStr?: any;
  coin?: number;
  guildLevel?: number;
  guildMedal?: number;
}

export class MGuildEffectLevel implements IMGuildEffectLevel {
  constructor(
    public id?: number,
    public effectType?: number,
    public level?: number,
    public rate?: number,
    public rateStr?: any,
    public coin?: number,
    public guildLevel?: number,
    public guildMedal?: number
  ) {}
}
