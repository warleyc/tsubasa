import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCombinationCutPositionComponent,
  MCombinationCutPositionDetailComponent,
  MCombinationCutPositionUpdateComponent,
  MCombinationCutPositionDeletePopupComponent,
  MCombinationCutPositionDeleteDialogComponent,
  mCombinationCutPositionRoute,
  mCombinationCutPositionPopupRoute
} from './';

const ENTITY_STATES = [...mCombinationCutPositionRoute, ...mCombinationCutPositionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCombinationCutPositionComponent,
    MCombinationCutPositionDetailComponent,
    MCombinationCutPositionUpdateComponent,
    MCombinationCutPositionDeleteDialogComponent,
    MCombinationCutPositionDeletePopupComponent
  ],
  entryComponents: [
    MCombinationCutPositionComponent,
    MCombinationCutPositionUpdateComponent,
    MCombinationCutPositionDeleteDialogComponent,
    MCombinationCutPositionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCombinationCutPositionModule {}
