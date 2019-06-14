import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildNegativeWordComponent,
  MGuildNegativeWordDetailComponent,
  MGuildNegativeWordUpdateComponent,
  MGuildNegativeWordDeletePopupComponent,
  MGuildNegativeWordDeleteDialogComponent,
  mGuildNegativeWordRoute,
  mGuildNegativeWordPopupRoute
} from './';

const ENTITY_STATES = [...mGuildNegativeWordRoute, ...mGuildNegativeWordPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildNegativeWordComponent,
    MGuildNegativeWordDetailComponent,
    MGuildNegativeWordUpdateComponent,
    MGuildNegativeWordDeleteDialogComponent,
    MGuildNegativeWordDeletePopupComponent
  ],
  entryComponents: [
    MGuildNegativeWordComponent,
    MGuildNegativeWordUpdateComponent,
    MGuildNegativeWordDeleteDialogComponent,
    MGuildNegativeWordDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildNegativeWordModule {}
