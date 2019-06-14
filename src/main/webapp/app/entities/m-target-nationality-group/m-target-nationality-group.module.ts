import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetNationalityGroupComponent,
  MTargetNationalityGroupDetailComponent,
  MTargetNationalityGroupUpdateComponent,
  MTargetNationalityGroupDeletePopupComponent,
  MTargetNationalityGroupDeleteDialogComponent,
  mTargetNationalityGroupRoute,
  mTargetNationalityGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetNationalityGroupRoute, ...mTargetNationalityGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetNationalityGroupComponent,
    MTargetNationalityGroupDetailComponent,
    MTargetNationalityGroupUpdateComponent,
    MTargetNationalityGroupDeleteDialogComponent,
    MTargetNationalityGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetNationalityGroupComponent,
    MTargetNationalityGroupUpdateComponent,
    MTargetNationalityGroupDeleteDialogComponent,
    MTargetNationalityGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetNationalityGroupModule {}
