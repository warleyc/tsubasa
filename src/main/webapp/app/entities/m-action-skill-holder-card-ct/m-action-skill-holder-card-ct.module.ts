import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MActionSkillHolderCardCtComponent,
  MActionSkillHolderCardCtDetailComponent,
  MActionSkillHolderCardCtUpdateComponent,
  MActionSkillHolderCardCtDeletePopupComponent,
  MActionSkillHolderCardCtDeleteDialogComponent,
  mActionSkillHolderCardCtRoute,
  mActionSkillHolderCardCtPopupRoute
} from './';

const ENTITY_STATES = [...mActionSkillHolderCardCtRoute, ...mActionSkillHolderCardCtPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MActionSkillHolderCardCtComponent,
    MActionSkillHolderCardCtDetailComponent,
    MActionSkillHolderCardCtUpdateComponent,
    MActionSkillHolderCardCtDeleteDialogComponent,
    MActionSkillHolderCardCtDeletePopupComponent
  ],
  entryComponents: [
    MActionSkillHolderCardCtComponent,
    MActionSkillHolderCardCtUpdateComponent,
    MActionSkillHolderCardCtDeleteDialogComponent,
    MActionSkillHolderCardCtDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMActionSkillHolderCardCtModule {}
