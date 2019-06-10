import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryArComponent,
  MDictionaryArDetailComponent,
  MDictionaryArUpdateComponent,
  MDictionaryArDeletePopupComponent,
  MDictionaryArDeleteDialogComponent,
  mDictionaryArRoute,
  mDictionaryArPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryArRoute, ...mDictionaryArPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryArComponent,
    MDictionaryArDetailComponent,
    MDictionaryArUpdateComponent,
    MDictionaryArDeleteDialogComponent,
    MDictionaryArDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryArComponent,
    MDictionaryArUpdateComponent,
    MDictionaryArDeleteDialogComponent,
    MDictionaryArDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryArModule {}
