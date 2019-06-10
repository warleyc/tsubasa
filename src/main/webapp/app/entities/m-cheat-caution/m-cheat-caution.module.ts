import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCheatCautionComponent,
  MCheatCautionDetailComponent,
  MCheatCautionUpdateComponent,
  MCheatCautionDeletePopupComponent,
  MCheatCautionDeleteDialogComponent,
  mCheatCautionRoute,
  mCheatCautionPopupRoute
} from './';

const ENTITY_STATES = [...mCheatCautionRoute, ...mCheatCautionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCheatCautionComponent,
    MCheatCautionDetailComponent,
    MCheatCautionUpdateComponent,
    MCheatCautionDeleteDialogComponent,
    MCheatCautionDeletePopupComponent
  ],
  entryComponents: [
    MCheatCautionComponent,
    MCheatCautionUpdateComponent,
    MCheatCautionDeleteDialogComponent,
    MCheatCautionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCheatCautionModule {}
