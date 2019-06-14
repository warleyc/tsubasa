export interface IMMatchResultCutin {
  id?: number;
  characterId?: number;
  teamId?: number;
  isWin?: number;
  text?: any;
  soundEvent?: any;
  mcharacterId?: number;
}

export class MMatchResultCutin implements IMMatchResultCutin {
  constructor(
    public id?: number,
    public characterId?: number,
    public teamId?: number,
    public isWin?: number,
    public text?: any,
    public soundEvent?: any,
    public mcharacterId?: number
  ) {}
}
