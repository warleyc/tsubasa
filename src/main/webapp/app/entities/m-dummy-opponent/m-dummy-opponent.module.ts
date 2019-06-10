import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDummyOpponentComponent,
  MDummyOpponentDetailComponent,
  MDummyOpponentUpdateComponent,
  MDummyOpponentDeletePopupComponent,
  MDummyOpponentDeleteDialogComponent,
  mDummyOpponentRoute,
  mDummyOpponentPopupRoute
} from './';

const ENTITY_STATES = [...mDummyOpponentRoute, ...mDummyOpponentPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDummyOpponentComponent,
    MDummyOpponentDetailComponent,
    MDummyOpponentUpdateComponent,
    MDummyOpponentDeleteDialogComponent,
    MDummyOpponentDeletePopupComponent
  ],
  entryComponents: [
    MDummyOpponentComponent,
    MDummyOpponentUpdateComponent,
    MDummyOpponentDeleteDialogComponent,
    MDummyOpponentDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDummyOpponentModule {}
