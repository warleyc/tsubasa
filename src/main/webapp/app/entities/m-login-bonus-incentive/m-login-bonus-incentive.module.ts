import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLoginBonusIncentiveComponent,
  MLoginBonusIncentiveDetailComponent,
  MLoginBonusIncentiveUpdateComponent,
  MLoginBonusIncentiveDeletePopupComponent,
  MLoginBonusIncentiveDeleteDialogComponent,
  mLoginBonusIncentiveRoute,
  mLoginBonusIncentivePopupRoute
} from './';

const ENTITY_STATES = [...mLoginBonusIncentiveRoute, ...mLoginBonusIncentivePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MLoginBonusIncentiveComponent,
    MLoginBonusIncentiveDetailComponent,
    MLoginBonusIncentiveUpdateComponent,
    MLoginBonusIncentiveDeleteDialogComponent,
    MLoginBonusIncentiveDeletePopupComponent
  ],
  entryComponents: [
    MLoginBonusIncentiveComponent,
    MLoginBonusIncentiveUpdateComponent,
    MLoginBonusIncentiveDeleteDialogComponent,
    MLoginBonusIncentiveDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLoginBonusIncentiveModule {}
