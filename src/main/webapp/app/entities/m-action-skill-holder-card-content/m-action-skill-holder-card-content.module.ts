import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MActionSkillHolderCardContentComponent,
  MActionSkillHolderCardContentDetailComponent,
  MActionSkillHolderCardContentUpdateComponent,
  MActionSkillHolderCardContentDeletePopupComponent,
  MActionSkillHolderCardContentDeleteDialogComponent,
  mActionSkillHolderCardContentRoute,
  mActionSkillHolderCardContentPopupRoute
} from './';

const ENTITY_STATES = [...mActionSkillHolderCardContentRoute, ...mActionSkillHolderCardContentPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MActionSkillHolderCardContentComponent,
    MActionSkillHolderCardContentDetailComponent,
    MActionSkillHolderCardContentUpdateComponent,
    MActionSkillHolderCardContentDeleteDialogComponent,
    MActionSkillHolderCardContentDeletePopupComponent
  ],
  entryComponents: [
    MActionSkillHolderCardContentComponent,
    MActionSkillHolderCardContentUpdateComponent,
    MActionSkillHolderCardContentDeleteDialogComponent,
    MActionSkillHolderCardContentDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMActionSkillHolderCardContentModule {}
