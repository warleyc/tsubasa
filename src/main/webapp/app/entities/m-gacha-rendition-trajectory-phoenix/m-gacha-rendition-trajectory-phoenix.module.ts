import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionTrajectoryPhoenixComponent,
  MGachaRenditionTrajectoryPhoenixDetailComponent,
  MGachaRenditionTrajectoryPhoenixUpdateComponent,
  MGachaRenditionTrajectoryPhoenixDeletePopupComponent,
  MGachaRenditionTrajectoryPhoenixDeleteDialogComponent,
  mGachaRenditionTrajectoryPhoenixRoute,
  mGachaRenditionTrajectoryPhoenixPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionTrajectoryPhoenixRoute, ...mGachaRenditionTrajectoryPhoenixPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionTrajectoryPhoenixComponent,
    MGachaRenditionTrajectoryPhoenixDetailComponent,
    MGachaRenditionTrajectoryPhoenixUpdateComponent,
    MGachaRenditionTrajectoryPhoenixDeleteDialogComponent,
    MGachaRenditionTrajectoryPhoenixDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionTrajectoryPhoenixComponent,
    MGachaRenditionTrajectoryPhoenixUpdateComponent,
    MGachaRenditionTrajectoryPhoenixDeleteDialogComponent,
    MGachaRenditionTrajectoryPhoenixDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionTrajectoryPhoenixModule {}
