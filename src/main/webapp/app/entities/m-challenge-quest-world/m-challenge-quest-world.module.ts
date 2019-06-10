import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MChallengeQuestWorldComponent,
  MChallengeQuestWorldDetailComponent,
  MChallengeQuestWorldUpdateComponent,
  MChallengeQuestWorldDeletePopupComponent,
  MChallengeQuestWorldDeleteDialogComponent,
  mChallengeQuestWorldRoute,
  mChallengeQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mChallengeQuestWorldRoute, ...mChallengeQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MChallengeQuestWorldComponent,
    MChallengeQuestWorldDetailComponent,
    MChallengeQuestWorldUpdateComponent,
    MChallengeQuestWorldDeleteDialogComponent,
    MChallengeQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MChallengeQuestWorldComponent,
    MChallengeQuestWorldUpdateComponent,
    MChallengeQuestWorldDeleteDialogComponent,
    MChallengeQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMChallengeQuestWorldModule {}
