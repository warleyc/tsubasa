export interface IMCharacterBook {
  id?: number;
  cvName?: any;
  series?: number;
  height?: number;
  weight?: number;
  introduction?: any;
  firstAppearedComic?: any;
  firstAppearedComicLink?: any;
  skill1?: any;
  skill1Comic?: any;
  skill1ComicLink?: any;
  skill2?: any;
  skill2Comic?: any;
  skill2ComicLink?: any;
  skill3?: any;
  skill3Comic?: any;
  skill3ComicLink?: any;
}

export class MCharacterBook implements IMCharacterBook {
  constructor(
    public id?: number,
    public cvName?: any,
    public series?: number,
    public height?: number,
    public weight?: number,
    public introduction?: any,
    public firstAppearedComic?: any,
    public firstAppearedComicLink?: any,
    public skill1?: any,
    public skill1Comic?: any,
    public skill1ComicLink?: any,
    public skill2?: any,
    public skill2Comic?: any,
    public skill2ComicLink?: any,
    public skill3?: any,
    public skill3Comic?: any,
    public skill3ComicLink?: any
  ) {}
}
