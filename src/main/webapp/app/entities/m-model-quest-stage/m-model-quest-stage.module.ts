import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MModelQuestStageComponent,
  MModelQuestStageDetailComponent,
  MModelQuestStageUpdateComponent,
  MModelQuestStageDeletePopupComponent,
  MModelQuestStageDeleteDialogComponent,
  mModelQuestStageRoute,
  mModelQuestStagePopupRoute
} from './';

const ENTITY_STATES = [...mModelQuestStageRoute, ...mModelQuestStagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MModelQuestStageComponent,
    MModelQuestStageDetailComponent,
    MModelQuestStageUpdateComponent,
    MModelQuestStageDeleteDialogComponent,
    MModelQuestStageDeletePopupComponent
  ],
  entryComponents: [
    MModelQuestStageComponent,
    MModelQuestStageUpdateComponent,
    MModelQuestStageDeleteDialogComponent,
    MModelQuestStageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMModelQuestStageModule {}
