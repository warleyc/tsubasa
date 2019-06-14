import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestWorldComponent,
  MQuestWorldDetailComponent,
  MQuestWorldUpdateComponent,
  MQuestWorldDeletePopupComponent,
  MQuestWorldDeleteDialogComponent,
  mQuestWorldRoute,
  mQuestWorldPopupRoute
} from './';

const ENTITY_STATES = [...mQuestWorldRoute, ...mQuestWorldPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestWorldComponent,
    MQuestWorldDetailComponent,
    MQuestWorldUpdateComponent,
    MQuestWorldDeleteDialogComponent,
    MQuestWorldDeletePopupComponent
  ],
  entryComponents: [MQuestWorldComponent, MQuestWorldUpdateComponent, MQuestWorldDeleteDialogComponent, MQuestWorldDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestWorldModule {}
