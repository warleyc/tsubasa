import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMissionRewardComponent,
  MMissionRewardDetailComponent,
  MMissionRewardUpdateComponent,
  MMissionRewardDeletePopupComponent,
  MMissionRewardDeleteDialogComponent,
  mMissionRewardRoute,
  mMissionRewardPopupRoute
} from './';

const ENTITY_STATES = [...mMissionRewardRoute, ...mMissionRewardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMissionRewardComponent,
    MMissionRewardDetailComponent,
    MMissionRewardUpdateComponent,
    MMissionRewardDeleteDialogComponent,
    MMissionRewardDeletePopupComponent
  ],
  entryComponents: [
    MMissionRewardComponent,
    MMissionRewardUpdateComponent,
    MMissionRewardDeleteDialogComponent,
    MMissionRewardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMissionRewardModule {}
