import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGoalEffectiveCardComponent,
  MGoalEffectiveCardDetailComponent,
  MGoalEffectiveCardUpdateComponent,
  MGoalEffectiveCardDeletePopupComponent,
  MGoalEffectiveCardDeleteDialogComponent,
  mGoalEffectiveCardRoute,
  mGoalEffectiveCardPopupRoute
} from './';

const ENTITY_STATES = [...mGoalEffectiveCardRoute, ...mGoalEffectiveCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGoalEffectiveCardComponent,
    MGoalEffectiveCardDetailComponent,
    MGoalEffectiveCardUpdateComponent,
    MGoalEffectiveCardDeleteDialogComponent,
    MGoalEffectiveCardDeletePopupComponent
  ],
  entryComponents: [
    MGoalEffectiveCardComponent,
    MGoalEffectiveCardUpdateComponent,
    MGoalEffectiveCardDeleteDialogComponent,
    MGoalEffectiveCardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGoalEffectiveCardModule {}
