import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDeckRarityConditionDescriptionComponent,
  MDeckRarityConditionDescriptionDetailComponent,
  MDeckRarityConditionDescriptionUpdateComponent,
  MDeckRarityConditionDescriptionDeletePopupComponent,
  MDeckRarityConditionDescriptionDeleteDialogComponent,
  mDeckRarityConditionDescriptionRoute,
  mDeckRarityConditionDescriptionPopupRoute
} from './';

const ENTITY_STATES = [...mDeckRarityConditionDescriptionRoute, ...mDeckRarityConditionDescriptionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDeckRarityConditionDescriptionComponent,
    MDeckRarityConditionDescriptionDetailComponent,
    MDeckRarityConditionDescriptionUpdateComponent,
    MDeckRarityConditionDescriptionDeleteDialogComponent,
    MDeckRarityConditionDescriptionDeletePopupComponent
  ],
  entryComponents: [
    MDeckRarityConditionDescriptionComponent,
    MDeckRarityConditionDescriptionUpdateComponent,
    MDeckRarityConditionDescriptionDeleteDialogComponent,
    MDeckRarityConditionDescriptionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDeckRarityConditionDescriptionModule {}
