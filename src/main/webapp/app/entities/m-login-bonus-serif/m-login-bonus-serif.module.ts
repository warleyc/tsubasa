import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLoginBonusSerifComponent,
  MLoginBonusSerifDetailComponent,
  MLoginBonusSerifUpdateComponent,
  MLoginBonusSerifDeletePopupComponent,
  MLoginBonusSerifDeleteDialogComponent,
  mLoginBonusSerifRoute,
  mLoginBonusSerifPopupRoute
} from './';

const ENTITY_STATES = [...mLoginBonusSerifRoute, ...mLoginBonusSerifPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLoginBonusSerifComponent,
    MLoginBonusSerifDetailComponent,
    MLoginBonusSerifUpdateComponent,
    MLoginBonusSerifDeleteDialogComponent,
    MLoginBonusSerifDeletePopupComponent
  ],
  entryComponents: [
    MLoginBonusSerifComponent,
    MLoginBonusSerifUpdateComponent,
    MLoginBonusSerifDeleteDialogComponent,
    MLoginBonusSerifDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLoginBonusSerifModule {}
