import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryZhComponent,
  MDictionaryZhDetailComponent,
  MDictionaryZhUpdateComponent,
  MDictionaryZhDeletePopupComponent,
  MDictionaryZhDeleteDialogComponent,
  mDictionaryZhRoute,
  mDictionaryZhPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryZhRoute, ...mDictionaryZhPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryZhComponent,
    MDictionaryZhDetailComponent,
    MDictionaryZhUpdateComponent,
    MDictionaryZhDeleteDialogComponent,
    MDictionaryZhDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryZhComponent,
    MDictionaryZhUpdateComponent,
    MDictionaryZhDeleteDialogComponent,
    MDictionaryZhDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryZhModule {}
