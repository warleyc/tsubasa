import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMatchResultCutinComponent,
  MMatchResultCutinDetailComponent,
  MMatchResultCutinUpdateComponent,
  MMatchResultCutinDeletePopupComponent,
  MMatchResultCutinDeleteDialogComponent,
  mMatchResultCutinRoute,
  mMatchResultCutinPopupRoute
} from './';

const ENTITY_STATES = [...mMatchResultCutinRoute, ...mMatchResultCutinPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMatchResultCutinComponent,
    MMatchResultCutinDetailComponent,
    MMatchResultCutinUpdateComponent,
    MMatchResultCutinDeleteDialogComponent,
    MMatchResultCutinDeletePopupComponent
  ],
  entryComponents: [
    MMatchResultCutinComponent,
    MMatchResultCutinUpdateComponent,
    MMatchResultCutinDeleteDialogComponent,
    MMatchResultCutinDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMatchResultCutinModule {}
