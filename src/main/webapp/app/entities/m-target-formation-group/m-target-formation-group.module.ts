import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetFormationGroupComponent,
  MTargetFormationGroupDetailComponent,
  MTargetFormationGroupUpdateComponent,
  MTargetFormationGroupDeletePopupComponent,
  MTargetFormationGroupDeleteDialogComponent,
  mTargetFormationGroupRoute,
  mTargetFormationGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetFormationGroupRoute, ...mTargetFormationGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetFormationGroupComponent,
    MTargetFormationGroupDetailComponent,
    MTargetFormationGroupUpdateComponent,
    MTargetFormationGroupDeleteDialogComponent,
    MTargetFormationGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetFormationGroupComponent,
    MTargetFormationGroupUpdateComponent,
    MTargetFormationGroupDeleteDialogComponent,
    MTargetFormationGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetFormationGroupModule {}
