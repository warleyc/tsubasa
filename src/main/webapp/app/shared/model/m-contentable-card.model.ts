export interface IMContentableCard {
  id?: number;
  playableCardId?: number;
  level?: number;
  actionMainLevel?: number;
  actionSub1Level?: number;
  actionSub2Level?: number;
  actionSub3Level?: number;
  actionSub4Level?: number;
  plusRate?: number;
}

export class MContentableCard implements IMContentableCard {
  constructor(
    public id?: number,
    public playableCardId?: number,
    public level?: number,
    public actionMainLevel?: number,
    public actionSub1Level?: number,
    public actionSub2Level?: number,
    public actionSub3Level?: number,
    public actionSub4Level?: number,
    public plusRate?: number
  ) {}
}
