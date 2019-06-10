import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAdventQuestStageComponent,
  MAdventQuestStageDetailComponent,
  MAdventQuestStageUpdateComponent,
  MAdventQuestStageDeletePopupComponent,
  MAdventQuestStageDeleteDialogComponent,
  mAdventQuestStageRoute,
  mAdventQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mAdventQuestStageRoute, ...mAdventQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAdventQuestStageComponent,
    MAdventQuestStageDetailComponent,
    MAdventQuestStageUpdateComponent,
    MAdventQuestStageDeleteDialogComponent,
    MAdventQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MAdventQuestStageComponent,
    MAdventQuestStageUpdateComponent,
    MAdventQuestStageDeleteDialogComponent,
    MAdventQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAdventQuestStageModule {}
