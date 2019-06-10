import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryJaComponent,
  MDictionaryJaDetailComponent,
  MDictionaryJaUpdateComponent,
  MDictionaryJaDeletePopupComponent,
  MDictionaryJaDeleteDialogComponent,
  mDictionaryJaRoute,
  mDictionaryJaPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryJaRoute, ...mDictionaryJaPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryJaComponent,
    MDictionaryJaDetailComponent,
    MDictionaryJaUpdateComponent,
    MDictionaryJaDeleteDialogComponent,
    MDictionaryJaDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryJaComponent,
    MDictionaryJaUpdateComponent,
    MDictionaryJaDeleteDialogComponent,
    MDictionaryJaDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryJaModule {}
