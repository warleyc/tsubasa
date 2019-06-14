import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTrainingCardComponent,
  MTrainingCardDetailComponent,
  MTrainingCardUpdateComponent,
  MTrainingCardDeletePopupComponent,
  MTrainingCardDeleteDialogComponent,
  mTrainingCardRoute,
  mTrainingCardPopupRoute
} from './';

const ENTITY_STATES = [...mTrainingCardRoute, ...mTrainingCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTrainingCardComponent,
    MTrainingCardDetailComponent,
    MTrainingCardUpdateComponent,
    MTrainingCardDeleteDialogComponent,
    MTrainingCardDeletePopupComponent
  ],
  entryComponents: [
    MTrainingCardComponent,
    MTrainingCardUpdateComponent,
    MTrainingCardDeleteDialogComponent,
    MTrainingCardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTrainingCardModule {}
