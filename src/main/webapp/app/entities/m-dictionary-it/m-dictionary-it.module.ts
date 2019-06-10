import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryItComponent,
  MDictionaryItDetailComponent,
  MDictionaryItUpdateComponent,
  MDictionaryItDeletePopupComponent,
  MDictionaryItDeleteDialogComponent,
  mDictionaryItRoute,
  mDictionaryItPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryItRoute, ...mDictionaryItPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryItComponent,
    MDictionaryItDetailComponent,
    MDictionaryItUpdateComponent,
    MDictionaryItDeleteDialogComponent,
    MDictionaryItDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryItComponent,
    MDictionaryItUpdateComponent,
    MDictionaryItDeleteDialogComponent,
    MDictionaryItDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryItModule {}
