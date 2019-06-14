import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTimeattackQuestStageComponent,
  MTimeattackQuestStageDetailComponent,
  MTimeattackQuestStageUpdateComponent,
  MTimeattackQuestStageDeletePopupComponent,
  MTimeattackQuestStageDeleteDialogComponent,
  mTimeattackQuestStageRoute,
  mTimeattackQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mTimeattackQuestStageRoute, ...mTimeattackQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTimeattackQuestStageComponent,
    MTimeattackQuestStageDetailComponent,
    MTimeattackQuestStageUpdateComponent,
    MTimeattackQuestStageDeleteDialogComponent,
    MTimeattackQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MTimeattackQuestStageComponent,
    MTimeattackQuestStageUpdateComponent,
    MTimeattackQuestStageDeleteDialogComponent,
    MTimeattackQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTimeattackQuestStageModule {}
