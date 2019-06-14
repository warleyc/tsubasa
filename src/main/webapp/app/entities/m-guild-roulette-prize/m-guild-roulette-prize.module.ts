import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildRoulettePrizeComponent,
  MGuildRoulettePrizeDetailComponent,
  MGuildRoulettePrizeUpdateComponent,
  MGuildRoulettePrizeDeletePopupComponent,
  MGuildRoulettePrizeDeleteDialogComponent,
  mGuildRoulettePrizeRoute,
  mGuildRoulettePrizePopupRoute
} from './';

const ENTITY_STATES = [...mGuildRoulettePrizeRoute, ...mGuildRoulettePrizePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildRoulettePrizeComponent,
    MGuildRoulettePrizeDetailComponent,
    MGuildRoulettePrizeUpdateComponent,
    MGuildRoulettePrizeDeleteDialogComponent,
    MGuildRoulettePrizeDeletePopupComponent
  ],
  entryComponents: [
    MGuildRoulettePrizeComponent,
    MGuildRoulettePrizeUpdateComponent,
    MGuildRoulettePrizeDeleteDialogComponent,
    MGuildRoulettePrizeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildRoulettePrizeModule {}
