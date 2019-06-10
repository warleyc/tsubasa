import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionSwipeIconComponent,
  MGachaRenditionSwipeIconDetailComponent,
  MGachaRenditionSwipeIconUpdateComponent,
  MGachaRenditionSwipeIconDeletePopupComponent,
  MGachaRenditionSwipeIconDeleteDialogComponent,
  mGachaRenditionSwipeIconRoute,
  mGachaRenditionSwipeIconPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionSwipeIconRoute, ...mGachaRenditionSwipeIconPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionSwipeIconComponent,
    MGachaRenditionSwipeIconDetailComponent,
    MGachaRenditionSwipeIconUpdateComponent,
    MGachaRenditionSwipeIconDeleteDialogComponent,
    MGachaRenditionSwipeIconDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionSwipeIconComponent,
    MGachaRenditionSwipeIconUpdateComponent,
    MGachaRenditionSwipeIconDeleteDialogComponent,
    MGachaRenditionSwipeIconDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionSwipeIconModule {}
