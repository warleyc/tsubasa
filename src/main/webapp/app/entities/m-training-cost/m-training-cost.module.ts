import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTrainingCostComponent,
  MTrainingCostDetailComponent,
  MTrainingCostUpdateComponent,
  MTrainingCostDeletePopupComponent,
  MTrainingCostDeleteDialogComponent,
  mTrainingCostRoute,
  mTrainingCostPopupRoute
} from './';

const ENTITY_STATES = [...mTrainingCostRoute, ...mTrainingCostPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTrainingCostComponent,
    MTrainingCostDetailComponent,
    MTrainingCostUpdateComponent,
    MTrainingCostDeleteDialogComponent,
    MTrainingCostDeletePopupComponent
  ],
  entryComponents: [
    MTrainingCostComponent,
    MTrainingCostUpdateComponent,
    MTrainingCostDeleteDialogComponent,
    MTrainingCostDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTrainingCostModule {}
