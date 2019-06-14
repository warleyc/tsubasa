import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MInheritActionSkillCostComponent,
  MInheritActionSkillCostDetailComponent,
  MInheritActionSkillCostUpdateComponent,
  MInheritActionSkillCostDeletePopupComponent,
  MInheritActionSkillCostDeleteDialogComponent,
  mInheritActionSkillCostRoute,
  mInheritActionSkillCostPopupRoute
} from './';

const ENTITY_STATES = [...mInheritActionSkillCostRoute, ...mInheritActionSkillCostPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MInheritActionSkillCostComponent,
    MInheritActionSkillCostDetailComponent,
    MInheritActionSkillCostUpdateComponent,
    MInheritActionSkillCostDeleteDialogComponent,
    MInheritActionSkillCostDeletePopupComponent
  ],
  entryComponents: [
    MInheritActionSkillCostComponent,
    MInheritActionSkillCostUpdateComponent,
    MInheritActionSkillCostDeleteDialogComponent,
    MInheritActionSkillCostDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMInheritActionSkillCostModule {}
