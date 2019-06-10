import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionAfterInputCutInComponent,
  MGachaRenditionAfterInputCutInDetailComponent,
  MGachaRenditionAfterInputCutInUpdateComponent,
  MGachaRenditionAfterInputCutInDeletePopupComponent,
  MGachaRenditionAfterInputCutInDeleteDialogComponent,
  mGachaRenditionAfterInputCutInRoute,
  mGachaRenditionAfterInputCutInPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionAfterInputCutInRoute, ...mGachaRenditionAfterInputCutInPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionAfterInputCutInComponent,
    MGachaRenditionAfterInputCutInDetailComponent,
    MGachaRenditionAfterInputCutInUpdateComponent,
    MGachaRenditionAfterInputCutInDeleteDialogComponent,
    MGachaRenditionAfterInputCutInDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionAfterInputCutInComponent,
    MGachaRenditionAfterInputCutInUpdateComponent,
    MGachaRenditionAfterInputCutInDeleteDialogComponent,
    MGachaRenditionAfterInputCutInDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionAfterInputCutInModule {}
