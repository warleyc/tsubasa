import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCutComponent,
  MCutDetailComponent,
  MCutUpdateComponent,
  MCutDeletePopupComponent,
  MCutDeleteDialogComponent,
  mCutRoute,
  mCutPopupRoute
} from './';

const ENTITY_STATES = [...mCutRoute, ...mCutPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MCutComponent, MCutDetailComponent, MCutUpdateComponent, MCutDeleteDialogComponent, MCutDeletePopupComponent],
  entryComponents: [MCutComponent, MCutUpdateComponent, MCutDeleteDialogComponent, MCutDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCutModule {}
