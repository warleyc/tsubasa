import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetTeamGroupComponent,
  MTargetTeamGroupDetailComponent,
  MTargetTeamGroupUpdateComponent,
  MTargetTeamGroupDeletePopupComponent,
  MTargetTeamGroupDeleteDialogComponent,
  mTargetTeamGroupRoute,
  mTargetTeamGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetTeamGroupRoute, ...mTargetTeamGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetTeamGroupComponent,
    MTargetTeamGroupDetailComponent,
    MTargetTeamGroupUpdateComponent,
    MTargetTeamGroupDeleteDialogComponent,
    MTargetTeamGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetTeamGroupComponent,
    MTargetTeamGroupUpdateComponent,
    MTargetTeamGroupDeleteDialogComponent,
    MTargetTeamGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetTeamGroupModule {}
