import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDictionaryEsComponent,
  MDictionaryEsDetailComponent,
  MDictionaryEsUpdateComponent,
  MDictionaryEsDeletePopupComponent,
  MDictionaryEsDeleteDialogComponent,
  mDictionaryEsRoute,
  mDictionaryEsPopupRoute
} from './';

const ENTITY_STATES = [...mDictionaryEsRoute, ...mDictionaryEsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDictionaryEsComponent,
    MDictionaryEsDetailComponent,
    MDictionaryEsUpdateComponent,
    MDictionaryEsDeleteDialogComponent,
    MDictionaryEsDeletePopupComponent
  ],
  entryComponents: [
    MDictionaryEsComponent,
    MDictionaryEsUpdateComponent,
    MDictionaryEsDeleteDialogComponent,
    MDictionaryEsDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDictionaryEsModule {}
