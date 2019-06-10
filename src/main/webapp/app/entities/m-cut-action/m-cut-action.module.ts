import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCutActionComponent,
  MCutActionDetailComponent,
  MCutActionUpdateComponent,
  MCutActionDeletePopupComponent,
  MCutActionDeleteDialogComponent,
  mCutActionRoute,
  mCutActionPopupRoute
} from './';

const ENTITY_STATES = [...mCutActionRoute, ...mCutActionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCutActionComponent,
    MCutActionDetailComponent,
    MCutActionUpdateComponent,
    MCutActionDeleteDialogComponent,
    MCutActionDeletePopupComponent
  ],
  entryComponents: [MCutActionComponent, MCutActionUpdateComponent, MCutActionDeleteDialogComponent, MCutActionDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCutActionModule {}
