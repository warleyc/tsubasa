import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAreaActionWeightComponent,
  MAreaActionWeightDetailComponent,
  MAreaActionWeightUpdateComponent,
  MAreaActionWeightDeletePopupComponent,
  MAreaActionWeightDeleteDialogComponent,
  mAreaActionWeightRoute,
  mAreaActionWeightPopupRoute
} from './';

const ENTITY_STATES = [...mAreaActionWeightRoute, ...mAreaActionWeightPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAreaActionWeightComponent,
    MAreaActionWeightDetailComponent,
    MAreaActionWeightUpdateComponent,
    MAreaActionWeightDeleteDialogComponent,
    MAreaActionWeightDeletePopupComponent
  ],
  entryComponents: [
    MAreaActionWeightComponent,
    MAreaActionWeightUpdateComponent,
    MAreaActionWeightDeleteDialogComponent,
    MAreaActionWeightDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAreaActionWeightModule {}
