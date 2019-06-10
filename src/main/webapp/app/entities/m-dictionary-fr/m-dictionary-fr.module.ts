import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryFrComponent,
  MDictionaryFrDetailComponent,
  MDictionaryFrUpdateComponent,
  MDictionaryFrDeletePopupComponent,
  MDictionaryFrDeleteDialogComponent,
  mDictionaryFrRoute,
  mDictionaryFrPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryFrRoute, ...mDictionaryFrPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryFrComponent,
    MDictionaryFrDetailComponent,
    MDictionaryFrUpdateComponent,
    MDictionaryFrDeleteDialogComponent,
    MDictionaryFrDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryFrComponent,
    MDictionaryFrUpdateComponent,
    MDictionaryFrDeleteDialogComponent,
    MDictionaryFrDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryFrModule {}
