import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAdventQuestWorldComponent,
  MAdventQuestWorldDetailComponent,
  MAdventQuestWorldUpdateComponent,
  MAdventQuestWorldDeletePopupComponent,
  MAdventQuestWorldDeleteDialogComponent,
  mAdventQuestWorldRoute,
  mAdventQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mAdventQuestWorldRoute, ...mAdventQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAdventQuestWorldComponent,
    MAdventQuestWorldDetailComponent,
    MAdventQuestWorldUpdateComponent,
    MAdventQuestWorldDeleteDialogComponent,
    MAdventQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MAdventQuestWorldComponent,
    MAdventQuestWorldUpdateComponent,
    MAdventQuestWorldDeleteDialogComponent,
    MAdventQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAdventQuestWorldModule {}
