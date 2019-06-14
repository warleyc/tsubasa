export interface IMModelUniformBottomResource {
  id?: number;
  uniformTypeId?: number;
  dressingType?: number;
  width?: number;
}

export class MModelUniformBottomResource implements IMModelUniformBottomResource {
  constructor(public id?: number, public uniformTypeId?: number, public dressingType?: number, public width?: number) {}
}
