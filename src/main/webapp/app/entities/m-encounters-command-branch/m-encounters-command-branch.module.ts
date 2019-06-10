import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEncountersCommandBranchComponent,
  MEncountersCommandBranchDetailComponent,
  MEncountersCommandBranchUpdateComponent,
  MEncountersCommandBranchDeletePopupComponent,
  MEncountersCommandBranchDeleteDialogComponent,
  mEncountersCommandBranchRoute,
  mEncountersCommandBranchPopupRoute
} from './';

const ENTITY_STATES = [...mEncountersCommandBranchRoute, ...mEncountersCommandBranchPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEncountersCommandBranchComponent,
    MEncountersCommandBranchDetailComponent,
    MEncountersCommandBranchUpdateComponent,
    MEncountersCommandBranchDeleteDialogComponent,
    MEncountersCommandBranchDeletePopupComponent
  ],
  entryComponents: [
    MEncountersCommandBranchComponent,
    MEncountersCommandBranchUpdateComponent,
    MEncountersCommandBranchDeleteDialogComponent,
    MEncountersCommandBranchDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEncountersCommandBranchModule {}
