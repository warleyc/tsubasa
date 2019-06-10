import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MActionRarityComponent,
  MActionRarityDetailComponent,
  MActionRarityUpdateComponent,
  MActionRarityDeletePopupComponent,
  MActionRarityDeleteDialogComponent,
  mActionRarityRoute,
  mActionRarityPopupRoute
} from './';

const ENTITY_STATES = [...mActionRarityRoute, ...mActionRarityPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MActionRarityComponent,
    MActionRarityDetailComponent,
    MActionRarityUpdateComponent,
    MActionRarityDeleteDialogComponent,
    MActionRarityDeletePopupComponent
  ],
  entryComponents: [
    MActionRarityComponent,
    MActionRarityUpdateComponent,
    MActionRarityDeleteDialogComponent,
    MActionRarityDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMActionRarityModule {}
