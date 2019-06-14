import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionTrajectoryComponent,
  MGachaRenditionTrajectoryDetailComponent,
  MGachaRenditionTrajectoryUpdateComponent,
  MGachaRenditionTrajectoryDeletePopupComponent,
  MGachaRenditionTrajectoryDeleteDialogComponent,
  mGachaRenditionTrajectoryRoute,
  mGachaRenditionTrajectoryPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionTrajectoryRoute, ...mGachaRenditionTrajectoryPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionTrajectoryComponent,
    MGachaRenditionTrajectoryDetailComponent,
    MGachaRenditionTrajectoryUpdateComponent,
    MGachaRenditionTrajectoryDeleteDialogComponent,
    MGachaRenditionTrajectoryDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionTrajectoryComponent,
    MGachaRenditionTrajectoryUpdateComponent,
    MGachaRenditionTrajectoryDeleteDialogComponent,
    MGachaRenditionTrajectoryDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionTrajectoryModule {}
