import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MRivalEncountCutinComponent,
  MRivalEncountCutinDetailComponent,
  MRivalEncountCutinUpdateComponent,
  MRivalEncountCutinDeletePopupComponent,
  MRivalEncountCutinDeleteDialogComponent,
  mRivalEncountCutinRoute,
  mRivalEncountCutinPopupRoute
} from './';

const ENTITY_STATES = [...mRivalEncountCutinRoute, ...mRivalEncountCutinPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MRivalEncountCutinComponent,
    MRivalEncountCutinDetailComponent,
    MRivalEncountCutinUpdateComponent,
    MRivalEncountCutinDeleteDialogComponent,
    MRivalEncountCutinDeletePopupComponent
  ],
  entryComponents: [
    MRivalEncountCutinComponent,
    MRivalEncountCutinUpdateComponent,
    MRivalEncountCutinDeleteDialogComponent,
    MRivalEncountCutinDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMRivalEncountCutinModule {}
