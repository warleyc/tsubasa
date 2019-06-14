import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPenaltyKickCutComponent,
  MPenaltyKickCutDetailComponent,
  MPenaltyKickCutUpdateComponent,
  MPenaltyKickCutDeletePopupComponent,
  MPenaltyKickCutDeleteDialogComponent,
  mPenaltyKickCutRoute,
  mPenaltyKickCutPopupRoute
} from './';

const ENTITY_STATES = [...mPenaltyKickCutRoute, ...mPenaltyKickCutPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPenaltyKickCutComponent,
    MPenaltyKickCutDetailComponent,
    MPenaltyKickCutUpdateComponent,
    MPenaltyKickCutDeleteDialogComponent,
    MPenaltyKickCutDeletePopupComponent
  ],
  entryComponents: [
    MPenaltyKickCutComponent,
    MPenaltyKickCutUpdateComponent,
    MPenaltyKickCutDeleteDialogComponent,
    MPenaltyKickCutDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPenaltyKickCutModule {}
