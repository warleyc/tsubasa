import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetRarityGroupComponent,
  MTargetRarityGroupDetailComponent,
  MTargetRarityGroupUpdateComponent,
  MTargetRarityGroupDeletePopupComponent,
  MTargetRarityGroupDeleteDialogComponent,
  mTargetRarityGroupRoute,
  mTargetRarityGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetRarityGroupRoute, ...mTargetRarityGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetRarityGroupComponent,
    MTargetRarityGroupDetailComponent,
    MTargetRarityGroupUpdateComponent,
    MTargetRarityGroupDeleteDialogComponent,
    MTargetRarityGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetRarityGroupComponent,
    MTargetRarityGroupUpdateComponent,
    MTargetRarityGroupDeleteDialogComponent,
    MTargetRarityGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetRarityGroupModule {}
