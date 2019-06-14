export interface IMStoryResourceMapping {
  id?: number;
  lang?: number;
  scriptName?: any;
  ids?: any;
}

export class MStoryResourceMapping implements IMStoryResourceMapping {
  constructor(public id?: number, public lang?: number, public scriptName?: any, public ids?: any) {}
}
