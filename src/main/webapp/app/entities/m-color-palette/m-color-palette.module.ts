import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MColorPaletteComponent,
  MColorPaletteDetailComponent,
  MColorPaletteUpdateComponent,
  MColorPaletteDeletePopupComponent,
  MColorPaletteDeleteDialogComponent,
  mColorPaletteRoute,
  mColorPalettePopupRoute
} from './';

const ENTITY_STATES = [...mColorPaletteRoute, ...mColorPalettePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MColorPaletteComponent,
    MColorPaletteDetailComponent,
    MColorPaletteUpdateComponent,
    MColorPaletteDeleteDialogComponent,
    MColorPaletteDeletePopupComponent
  ],
  entryComponents: [
    MColorPaletteComponent,
    MColorPaletteUpdateComponent,
    MColorPaletteDeleteDialogComponent,
    MColorPaletteDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMColorPaletteModule {}
