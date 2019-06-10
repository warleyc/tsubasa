import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCharacterScoreCutComponent,
  MCharacterScoreCutDetailComponent,
  MCharacterScoreCutUpdateComponent,
  MCharacterScoreCutDeletePopupComponent,
  MCharacterScoreCutDeleteDialogComponent,
  mCharacterScoreCutRoute,
  mCharacterScoreCutPopupRoute
} from './';

const ENTITY_STATES = [...mCharacterScoreCutRoute, ...mCharacterScoreCutPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCharacterScoreCutComponent,
    MCharacterScoreCutDetailComponent,
    MCharacterScoreCutUpdateComponent,
    MCharacterScoreCutDeleteDialogComponent,
    MCharacterScoreCutDeletePopupComponent
  ],
  entryComponents: [
    MCharacterScoreCutComponent,
    MCharacterScoreCutUpdateComponent,
    MCharacterScoreCutDeleteDialogComponent,
    MCharacterScoreCutDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCharacterScoreCutModule {}
