import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MUserPolicyUpdateDateComponent,
  MUserPolicyUpdateDateDetailComponent,
  MUserPolicyUpdateDateUpdateComponent,
  MUserPolicyUpdateDateDeletePopupComponent,
  MUserPolicyUpdateDateDeleteDialogComponent,
  mUserPolicyUpdateDateRoute,
  mUserPolicyUpdateDatePopupRoute
} from './';

const ENTITY_STATES = [...mUserPolicyUpdateDateRoute, ...mUserPolicyUpdateDatePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MUserPolicyUpdateDateComponent,
    MUserPolicyUpdateDateDetailComponent,
    MUserPolicyUpdateDateUpdateComponent,
    MUserPolicyUpdateDateDeleteDialogComponent,
    MUserPolicyUpdateDateDeletePopupComponent
  ],
  entryComponents: [
    MUserPolicyUpdateDateComponent,
    MUserPolicyUpdateDateUpdateComponent,
    MUserPolicyUpdateDateDeleteDialogComponent,
    MUserPolicyUpdateDateDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMUserPolicyUpdateDateModule {}
