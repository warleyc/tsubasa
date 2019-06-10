import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionBallComponent,
  MGachaRenditionBallDetailComponent,
  MGachaRenditionBallUpdateComponent,
  MGachaRenditionBallDeletePopupComponent,
  MGachaRenditionBallDeleteDialogComponent,
  mGachaRenditionBallRoute,
  mGachaRenditionBallPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionBallRoute, ...mGachaRenditionBallPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionBallComponent,
    MGachaRenditionBallDetailComponent,
    MGachaRenditionBallUpdateComponent,
    MGachaRenditionBallDeleteDialogComponent,
    MGachaRenditionBallDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionBallComponent,
    MGachaRenditionBallUpdateComponent,
    MGachaRenditionBallDeleteDialogComponent,
    MGachaRenditionBallDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionBallModule {}
