import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCardRarityComponent,
  MCardRarityDetailComponent,
  MCardRarityUpdateComponent,
  MCardRarityDeletePopupComponent,
  MCardRarityDeleteDialogComponent,
  mCardRarityRoute,
  mCardRarityPopupRoute
} from './';

const ENTITY_STATES = [...mCardRarityRoute, ...mCardRarityPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCardRarityComponent,
    MCardRarityDetailComponent,
    MCardRarityUpdateComponent,
    MCardRarityDeleteDialogComponent,
    MCardRarityDeletePopupComponent
  ],
  entryComponents: [MCardRarityComponent, MCardRarityUpdateComponent, MCardRarityDeleteDialogComponent, MCardRarityDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCardRarityModule {}
