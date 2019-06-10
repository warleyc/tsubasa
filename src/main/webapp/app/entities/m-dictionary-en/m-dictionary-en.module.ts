import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryEnComponent,
  MDictionaryEnDetailComponent,
  MDictionaryEnUpdateComponent,
  MDictionaryEnDeletePopupComponent,
  MDictionaryEnDeleteDialogComponent,
  mDictionaryEnRoute,
  mDictionaryEnPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryEnRoute, ...mDictionaryEnPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryEnComponent,
    MDictionaryEnDetailComponent,
    MDictionaryEnUpdateComponent,
    MDictionaryEnDeleteDialogComponent,
    MDictionaryEnDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryEnComponent,
    MDictionaryEnUpdateComponent,
    MDictionaryEnDeleteDialogComponent,
    MDictionaryEnDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryEnModule {}
