import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCutSeqGroupComponent,
  MCutSeqGroupDetailComponent,
  MCutSeqGroupUpdateComponent,
  MCutSeqGroupDeletePopupComponent,
  MCutSeqGroupDeleteDialogComponent,
  mCutSeqGroupRoute,
  mCutSeqGroupPopupRoute
} from './';

const ENTITY_STATES = [...mCutSeqGroupRoute, ...mCutSeqGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCutSeqGroupComponent,
    MCutSeqGroupDetailComponent,
    MCutSeqGroupUpdateComponent,
    MCutSeqGroupDeleteDialogComponent,
    MCutSeqGroupDeletePopupComponent
  ],
  entryComponents: [
    MCutSeqGroupComponent,
    MCutSeqGroupUpdateComponent,
    MCutSeqGroupDeleteDialogComponent,
    MCutSeqGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCutSeqGroupModule {}
