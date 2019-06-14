export interface IMGuildLevel {
  id?: number;
  level?: number;
  exp?: number;
  memberCount?: number;
  recommendMemberCount?: number;
  guildMedal?: number;
}

export class MGuildLevel implements IMGuildLevel {
  constructor(
    public id?: number,
    public level?: number,
    public exp?: number,
    public memberCount?: number,
    public recommendMemberCount?: number,
    public guildMedal?: number
  ) {}
}
