import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTimeattackQuestWorldComponent,
  MTimeattackQuestWorldDetailComponent,
  MTimeattackQuestWorldUpdateComponent,
  MTimeattackQuestWorldDeletePopupComponent,
  MTimeattackQuestWorldDeleteDialogComponent,
  mTimeattackQuestWorldRoute,
  mTimeattackQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mTimeattackQuestWorldRoute, ...mTimeattackQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTimeattackQuestWorldComponent,
    MTimeattackQuestWorldDetailComponent,
    MTimeattackQuestWorldUpdateComponent,
    MTimeattackQuestWorldDeleteDialogComponent,
    MTimeattackQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MTimeattackQuestWorldComponent,
    MTimeattackQuestWorldUpdateComponent,
    MTimeattackQuestWorldDeleteDialogComponent,
    MTimeattackQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTimeattackQuestWorldModule {}
