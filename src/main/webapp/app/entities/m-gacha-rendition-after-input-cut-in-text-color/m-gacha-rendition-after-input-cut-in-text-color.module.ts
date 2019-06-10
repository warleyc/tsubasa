import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionAfterInputCutInTextColorComponent,
  MGachaRenditionAfterInputCutInTextColorDetailComponent,
  MGachaRenditionAfterInputCutInTextColorUpdateComponent,
  MGachaRenditionAfterInputCutInTextColorDeletePopupComponent,
  MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent,
  mGachaRenditionAfterInputCutInTextColorRoute,
  mGachaRenditionAfterInputCutInTextColorPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionAfterInputCutInTextColorRoute, ...mGachaRenditionAfterInputCutInTextColorPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionAfterInputCutInTextColorComponent,
    MGachaRenditionAfterInputCutInTextColorDetailComponent,
    MGachaRenditionAfterInputCutInTextColorUpdateComponent,
    MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent,
    MGachaRenditionAfterInputCutInTextColorDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionAfterInputCutInTextColorComponent,
    MGachaRenditionAfterInputCutInTextColorUpdateComponent,
    MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent,
    MGachaRenditionAfterInputCutInTextColorDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionAfterInputCutInTextColorModule {}
