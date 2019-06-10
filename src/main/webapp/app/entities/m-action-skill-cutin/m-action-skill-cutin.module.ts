import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MActionSkillCutinComponent,
  MActionSkillCutinDetailComponent,
  MActionSkillCutinUpdateComponent,
  MActionSkillCutinDeletePopupComponent,
  MActionSkillCutinDeleteDialogComponent,
  mActionSkillCutinRoute,
  mActionSkillCutinPopupRoute
} from './';

const ENTITY_STATES = [...mActionSkillCutinRoute, ...mActionSkillCutinPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MActionSkillCutinComponent,
    MActionSkillCutinDetailComponent,
    MActionSkillCutinUpdateComponent,
    MActionSkillCutinDeleteDialogComponent,
    MActionSkillCutinDeletePopupComponent
  ],
  entryComponents: [
    MActionSkillCutinComponent,
    MActionSkillCutinUpdateComponent,
    MActionSkillCutinDeleteDialogComponent,
    MActionSkillCutinDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMActionSkillCutinModule {}
