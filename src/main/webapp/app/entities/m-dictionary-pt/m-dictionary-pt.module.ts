import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryPtComponent,
  MDictionaryPtDetailComponent,
  MDictionaryPtUpdateComponent,
  MDictionaryPtDeletePopupComponent,
  MDictionaryPtDeleteDialogComponent,
  mDictionaryPtRoute,
  mDictionaryPtPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryPtRoute, ...mDictionaryPtPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryPtComponent,
    MDictionaryPtDetailComponent,
    MDictionaryPtUpdateComponent,
    MDictionaryPtDeleteDialogComponent,
    MDictionaryPtDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryPtComponent,
    MDictionaryPtUpdateComponent,
    MDictionaryPtDeleteDialogComponent,
    MDictionaryPtDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryPtModule {}
