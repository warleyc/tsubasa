import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonEffectiveCardComponent,
  MMarathonEffectiveCardDetailComponent,
  MMarathonEffectiveCardUpdateComponent,
  MMarathonEffectiveCardDeletePopupComponent,
  MMarathonEffectiveCardDeleteDialogComponent,
  mMarathonEffectiveCardRoute,
  mMarathonEffectiveCardPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonEffectiveCardRoute, ...mMarathonEffectiveCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonEffectiveCardComponent,
    MMarathonEffectiveCardDetailComponent,
    MMarathonEffectiveCardUpdateComponent,
    MMarathonEffectiveCardDeleteDialogComponent,
    MMarathonEffectiveCardDeletePopupComponent
  ],
  entryComponents: [
    MMarathonEffectiveCardComponent,
    MMarathonEffectiveCardUpdateComponent,
    MMarathonEffectiveCardDeleteDialogComponent,
    MMarathonEffectiveCardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonEffectiveCardModule {}
