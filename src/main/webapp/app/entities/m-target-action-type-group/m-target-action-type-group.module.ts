import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetActionTypeGroupComponent,
  MTargetActionTypeGroupDetailComponent,
  MTargetActionTypeGroupUpdateComponent,
  MTargetActionTypeGroupDeletePopupComponent,
  MTargetActionTypeGroupDeleteDialogComponent,
  mTargetActionTypeGroupRoute,
  mTargetActionTypeGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetActionTypeGroupRoute, ...mTargetActionTypeGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetActionTypeGroupComponent,
    MTargetActionTypeGroupDetailComponent,
    MTargetActionTypeGroupUpdateComponent,
    MTargetActionTypeGroupDeleteDialogComponent,
    MTargetActionTypeGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetActionTypeGroupComponent,
    MTargetActionTypeGroupUpdateComponent,
    MTargetActionTypeGroupDeleteDialogComponent,
    MTargetActionTypeGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetActionTypeGroupModule {}
