import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPowerupActionSkillCostComponent,
  MPowerupActionSkillCostDetailComponent,
  MPowerupActionSkillCostUpdateComponent,
  MPowerupActionSkillCostDeletePopupComponent,
  MPowerupActionSkillCostDeleteDialogComponent,
  mPowerupActionSkillCostRoute,
  mPowerupActionSkillCostPopupRoute
} from './';

const ENTITY_STATES = [...mPowerupActionSkillCostRoute, ...mPowerupActionSkillCostPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPowerupActionSkillCostComponent,
    MPowerupActionSkillCostDetailComponent,
    MPowerupActionSkillCostUpdateComponent,
    MPowerupActionSkillCostDeleteDialogComponent,
    MPowerupActionSkillCostDeletePopupComponent
  ],
  entryComponents: [
    MPowerupActionSkillCostComponent,
    MPowerupActionSkillCostUpdateComponent,
    MPowerupActionSkillCostDeleteDialogComponent,
    MPowerupActionSkillCostDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPowerupActionSkillCostModule {}
