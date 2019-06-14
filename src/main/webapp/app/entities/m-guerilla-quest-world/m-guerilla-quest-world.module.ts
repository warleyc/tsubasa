import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuerillaQuestWorldComponent,
  MGuerillaQuestWorldDetailComponent,
  MGuerillaQuestWorldUpdateComponent,
  MGuerillaQuestWorldDeletePopupComponent,
  MGuerillaQuestWorldDeleteDialogComponent,
  mGuerillaQuestWorldRoute,
  mGuerillaQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mGuerillaQuestWorldRoute, ...mGuerillaQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuerillaQuestWorldComponent,
    MGuerillaQuestWorldDetailComponent,
    MGuerillaQuestWorldUpdateComponent,
    MGuerillaQuestWorldDeleteDialogComponent,
    MGuerillaQuestWorldDeletePopupComponent
  ],
  entryComponents: [
    MGuerillaQuestWorldComponent,
    MGuerillaQuestWorldUpdateComponent,
    MGuerillaQuestWorldDeleteDialogComponent,
    MGuerillaQuestWorldDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuerillaQuestWorldModule {}
