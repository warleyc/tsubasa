import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MActionLevelComponent,
  MActionLevelDetailComponent,
  MActionLevelUpdateComponent,
  MActionLevelDeletePopupComponent,
  MActionLevelDeleteDialogComponent,
  mActionLevelRoute,
  mActionLevelPopupRoute
} from './';

const ENTITY_STATES = [...mActionLevelRoute, ...mActionLevelPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MActionLevelComponent,
    MActionLevelDetailComponent,
    MActionLevelUpdateComponent,
    MActionLevelDeleteDialogComponent,
    MActionLevelDeletePopupComponent
  ],
  entryComponents: [
    MActionLevelComponent,
    MActionLevelUpdateComponent,
    MActionLevelDeleteDialogComponent,
    MActionLevelDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMActionLevelModule {}
