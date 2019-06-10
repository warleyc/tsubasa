import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionComponent,
  MGachaRenditionDetailComponent,
  MGachaRenditionUpdateComponent,
  MGachaRenditionDeletePopupComponent,
  MGachaRenditionDeleteDialogComponent,
  mGachaRenditionRoute,
  mGachaRenditionPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionRoute, ...mGachaRenditionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionComponent,
    MGachaRenditionDetailComponent,
    MGachaRenditionUpdateComponent,
    MGachaRenditionDeleteDialogComponent,
    MGachaRenditionDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionComponent,
    MGachaRenditionUpdateComponent,
    MGachaRenditionDeleteDialogComponent,
    MGachaRenditionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionModule {}
