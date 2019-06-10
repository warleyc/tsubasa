import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDeckConditionComponent,
  MDeckConditionDetailComponent,
  MDeckConditionUpdateComponent,
  MDeckConditionDeletePopupComponent,
  MDeckConditionDeleteDialogComponent,
  mDeckConditionRoute,
  mDeckConditionPopupRoute
} from './';

const ENTITY_STATES = [...mDeckConditionRoute, ...mDeckConditionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDeckConditionComponent,
    MDeckConditionDetailComponent,
    MDeckConditionUpdateComponent,
    MDeckConditionDeleteDialogComponent,
    MDeckConditionDeletePopupComponent
  ],
  entryComponents: [
    MDeckConditionComponent,
    MDeckConditionUpdateComponent,
    MDeckConditionDeleteDialogComponent,
    MDeckConditionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDeckConditionModule {}
