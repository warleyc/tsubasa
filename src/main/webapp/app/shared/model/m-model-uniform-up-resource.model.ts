export interface IMModelUniformUpResource {
  id?: number;
  uniformTypeId?: number;
  dressingType?: number;
  width?: number;
}

export class MModelUniformUpResource implements IMModelUniformUpResource {
  constructor(public id?: number, public uniformTypeId?: number, public dressingType?: number, public width?: number) {}
}
