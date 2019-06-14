import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetActionGroupComponent,
  MTargetActionGroupDetailComponent,
  MTargetActionGroupUpdateComponent,
  MTargetActionGroupDeletePopupComponent,
  MTargetActionGroupDeleteDialogComponent,
  mTargetActionGroupRoute,
  mTargetActionGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetActionGroupRoute, ...mTargetActionGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetActionGroupComponent,
    MTargetActionGroupDetailComponent,
    MTargetActionGroupUpdateComponent,
    MTargetActionGroupDeleteDialogComponent,
    MTargetActionGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetActionGroupComponent,
    MTargetActionGroupUpdateComponent,
    MTargetActionGroupDeleteDialogComponent,
    MTargetActionGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetActionGroupModule {}
