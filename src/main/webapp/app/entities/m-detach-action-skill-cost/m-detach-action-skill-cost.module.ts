import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDetachActionSkillCostComponent,
  MDetachActionSkillCostDetailComponent,
  MDetachActionSkillCostUpdateComponent,
  MDetachActionSkillCostDeletePopupComponent,
  MDetachActionSkillCostDeleteDialogComponent,
  mDetachActionSkillCostRoute,
  mDetachActionSkillCostPopupRoute
} from './';

const ENTITY_STATES = [...mDetachActionSkillCostRoute, ...mDetachActionSkillCostPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDetachActionSkillCostComponent,
    MDetachActionSkillCostDetailComponent,
    MDetachActionSkillCostUpdateComponent,
    MDetachActionSkillCostDeleteDialogComponent,
    MDetachActionSkillCostDeletePopupComponent
  ],
  entryComponents: [
    MDetachActionSkillCostComponent,
    MDetachActionSkillCostUpdateComponent,
    MDetachActionSkillCostDeleteDialogComponent,
    MDetachActionSkillCostDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDetachActionSkillCostModule {}
