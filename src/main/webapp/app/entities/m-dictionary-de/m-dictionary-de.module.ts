import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryDeComponent,
  MDictionaryDeDetailComponent,
  MDictionaryDeUpdateComponent,
  MDictionaryDeDeletePopupComponent,
  MDictionaryDeDeleteDialogComponent,
  mDictionaryDeRoute,
  mDictionaryDePopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryDeRoute, ...mDictionaryDePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryDeComponent,
    MDictionaryDeDetailComponent,
    MDictionaryDeUpdateComponent,
    MDictionaryDeDeleteDialogComponent,
    MDictionaryDeDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryDeComponent,
    MDictionaryDeUpdateComponent,
    MDictionaryDeDeleteDialogComponent,
    MDictionaryDeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryDeModule {}
