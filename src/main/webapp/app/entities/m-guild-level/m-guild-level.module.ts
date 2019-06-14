import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGuildLevelComponent,
  MGuildLevelDetailComponent,
  MGuildLevelUpdateComponent,
  MGuildLevelDeletePopupComponent,
  MGuildLevelDeleteDialogComponent,
  mGuildLevelRoute,
  mGuildLevelPopupRoute
} from './';

const ENTITY_STATES = [...mGuildLevelRoute, ...mGuildLevelPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGuildLevelComponent,
    MGuildLevelDetailComponent,
    MGuildLevelUpdateComponent,
    MGuildLevelDeleteDialogComponent,
    MGuildLevelDeletePopupComponent
  ],
  entryComponents: [MGuildLevelComponent, MGuildLevelUpdateComponent, MGuildLevelDeleteDialogComponent, MGuildLevelDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGuildLevelModule {}
