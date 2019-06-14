import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestCoopComponent,
  MQuestCoopDetailComponent,
  MQuestCoopUpdateComponent,
  MQuestCoopDeletePopupComponent,
  MQuestCoopDeleteDialogComponent,
  mQuestCoopRoute,
  mQuestCoopPopupRoute
} from './';

const ENTITY_STATES = [...mQuestCoopRoute, ...mQuestCoopPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestCoopComponent,
    MQuestCoopDetailComponent,
    MQuestCoopUpdateComponent,
    MQuestCoopDeleteDialogComponent,
    MQuestCoopDeletePopupComponent
  ],
  entryComponents: [MQuestCoopComponent, MQuestCoopUpdateComponent, MQuestCoopDeleteDialogComponent, MQuestCoopDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestCoopModule {}
