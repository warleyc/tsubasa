import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MWeeklyQuestWorldComponent,
  MWeeklyQuestWorldDetailComponent,
  MWeeklyQuestWorldUpdateComponent,
  MWeeklyQuestWorldDeletePopupComponent,
  MWeeklyQuestWorldDeleteDialogComponent,
  mWeeklyQuestWorldRoute,
  mWeeklyQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mWeeklyQuestWorldRoute, ...mWeeklyQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MWeeklyQuestWorldComponent,
    MWeeklyQuestWorldDetailComponent,
    MWeeklyQuestWorldUpdateComponent,
    MWeeklyQuestWorldDeleteDialogComponent,
    MWeeklyQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MWeeklyQuestWorldComponent,
    MWeeklyQuestWorldUpdateComponent,
    MWeeklyQuestWorldDeleteDialogComponent,
    MWeeklyQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMWeeklyQuestWorldModule {}
