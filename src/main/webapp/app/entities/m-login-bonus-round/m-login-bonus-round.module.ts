import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLoginBonusRoundComponent,
  MLoginBonusRoundDetailComponent,
  MLoginBonusRoundUpdateComponent,
  MLoginBonusRoundDeletePopupComponent,
  MLoginBonusRoundDeleteDialogComponent,
  mLoginBonusRoundRoute,
  mLoginBonusRoundPopupRoute
} from './';

const ENTITY_STATES = [...mLoginBonusRoundRoute, ...mLoginBonusRoundPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLoginBonusRoundComponent,
    MLoginBonusRoundDetailComponent,
    MLoginBonusRoundUpdateComponent,
    MLoginBonusRoundDeleteDialogComponent,
    MLoginBonusRoundDeletePopupComponent
  ],
  entryComponents: [
    MLoginBonusRoundComponent,
    MLoginBonusRoundUpdateComponent,
    MLoginBonusRoundDeleteDialogComponent,
    MLoginBonusRoundDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLoginBonusRoundModule {}
