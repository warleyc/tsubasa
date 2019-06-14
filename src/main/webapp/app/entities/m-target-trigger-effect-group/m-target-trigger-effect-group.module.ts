import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetTriggerEffectGroupComponent,
  MTargetTriggerEffectGroupDetailComponent,
  MTargetTriggerEffectGroupUpdateComponent,
  MTargetTriggerEffectGroupDeletePopupComponent,
  MTargetTriggerEffectGroupDeleteDialogComponent,
  mTargetTriggerEffectGroupRoute,
  mTargetTriggerEffectGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetTriggerEffectGroupRoute, ...mTargetTriggerEffectGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetTriggerEffectGroupComponent,
    MTargetTriggerEffectGroupDetailComponent,
    MTargetTriggerEffectGroupUpdateComponent,
    MTargetTriggerEffectGroupDeleteDialogComponent,
    MTargetTriggerEffectGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetTriggerEffectGroupComponent,
    MTargetTriggerEffectGroupUpdateComponent,
    MTargetTriggerEffectGroupDeleteDialogComponent,
    MTargetTriggerEffectGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetTriggerEffectGroupModule {}
