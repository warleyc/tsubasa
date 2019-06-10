import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCharacterComponent,
  MCharacterDetailComponent,
  MCharacterUpdateComponent,
  MCharacterDeletePopupComponent,
  MCharacterDeleteDialogComponent,
  mCharacterRoute,
  mCharacterPopupRoute
} from './';

const ENTITY_STATES = [...mCharacterRoute, ...mCharacterPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCharacterComponent,
    MCharacterDetailComponent,
    MCharacterUpdateComponent,
    MCharacterDeleteDialogComponent,
    MCharacterDeletePopupComponent
  ],
  entryComponents: [MCharacterComponent, MCharacterUpdateComponent, MCharacterDeleteDialogComponent, MCharacterDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCharacterModule {}
