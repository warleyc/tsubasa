import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonQuestWorldComponent,
  MMarathonQuestWorldDetailComponent,
  MMarathonQuestWorldUpdateComponent,
  MMarathonQuestWorldDeletePopupComponent,
  MMarathonQuestWorldDeleteDialogComponent,
  mMarathonQuestWorldRoute,
  mMarathonQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonQuestWorldRoute, ...mMarathonQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonQuestWorldComponent,
    MMarathonQuestWorldDetailComponent,
    MMarathonQuestWorldUpdateComponent,
    MMarathonQuestWorldDeleteDialogComponent,
    MMarathonQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MMarathonQuestWorldComponent,
    MMarathonQuestWorldUpdateComponent,
    MMarathonQuestWorldDeleteDialogComponent,
    MMarathonQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonQuestWorldModule {}
