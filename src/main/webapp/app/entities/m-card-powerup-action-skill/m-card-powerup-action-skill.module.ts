import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCardPowerupActionSkillComponent,
  MCardPowerupActionSkillDetailComponent,
  MCardPowerupActionSkillUpdateComponent,
  MCardPowerupActionSkillDeletePopupComponent,
  MCardPowerupActionSkillDeleteDialogComponent,
  mCardPowerupActionSkillRoute,
  mCardPowerupActionSkillPopupRoute
} from './';

const ENTITY_STATES = [...mCardPowerupActionSkillRoute, ...mCardPowerupActionSkillPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCardPowerupActionSkillComponent,
    MCardPowerupActionSkillDetailComponent,
    MCardPowerupActionSkillUpdateComponent,
    MCardPowerupActionSkillDeleteDialogComponent,
    MCardPowerupActionSkillDeletePopupComponent
  ],
  entryComponents: [
    MCardPowerupActionSkillComponent,
    MCardPowerupActionSkillUpdateComponent,
    MCardPowerupActionSkillDeleteDialogComponent,
    MCardPowerupActionSkillDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCardPowerupActionSkillModule {}
