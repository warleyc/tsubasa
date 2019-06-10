import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCardLevelComponent,
  MCardLevelDetailComponent,
  MCardLevelUpdateComponent,
  MCardLevelDeletePopupComponent,
  MCardLevelDeleteDialogComponent,
  mCardLevelRoute,
  mCardLevelPopupRoute
} from './';

const ENTITY_STATES = [...mCardLevelRoute, ...mCardLevelPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCardLevelComponent,
    MCardLevelDetailComponent,
    MCardLevelUpdateComponent,
    MCardLevelDeleteDialogComponent,
    MCardLevelDeletePopupComponent
  ],
  entryComponents: [MCardLevelComponent, MCardLevelUpdateComponent, MCardLevelDeleteDialogComponent, MCardLevelDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCardLevelModule {}
