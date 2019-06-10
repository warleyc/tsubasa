import { IMDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

export interface IMEmblemParts {
  id?: number;
  assetName?: any;
  partsType?: number;
  mDummyEmblems?: IMDummyEmblem[];
}

export class MEmblemParts implements IMEmblemParts {
  constructor(public id?: number, public assetName?: any, public partsType?: number, public mDummyEmblems?: IMDummyEmblem[]) {}
}
