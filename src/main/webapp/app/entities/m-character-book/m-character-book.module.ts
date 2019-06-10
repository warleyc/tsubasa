import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCharacterBookComponent,
  MCharacterBookDetailComponent,
  MCharacterBookUpdateComponent,
  MCharacterBookDeletePopupComponent,
  MCharacterBookDeleteDialogComponent,
  mCharacterBookRoute,
  mCharacterBookPopupRoute
} from './';

const ENTITY_STATES = [...mCharacterBookRoute, ...mCharacterBookPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCharacterBookComponent,
    MCharacterBookDetailComponent,
    MCharacterBookUpdateComponent,
    MCharacterBookDeleteDialogComponent,
    MCharacterBookDeletePopupComponent
  ],
  entryComponents: [
    MCharacterBookComponent,
    MCharacterBookUpdateComponent,
    MCharacterBookDeleteDialogComponent,
    MCharacterBookDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCharacterBookModule {}
