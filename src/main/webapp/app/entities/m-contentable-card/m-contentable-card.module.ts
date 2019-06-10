import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MContentableCardComponent,
  MContentableCardDetailComponent,
  MContentableCardUpdateComponent,
  MContentableCardDeletePopupComponent,
  MContentableCardDeleteDialogComponent,
  mContentableCardRoute,
  mContentableCardPopupRoute
} from './';

const ENTITY_STATES = [...mContentableCardRoute, ...mContentableCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MContentableCardComponent,
    MContentableCardDetailComponent,
    MContentableCardUpdateComponent,
    MContentableCardDeleteDialogComponent,
    MContentableCardDeletePopupComponent
  ],
  entryComponents: [
    MContentableCardComponent,
    MContentableCardUpdateComponent,
    MContentableCardDeleteDialogComponent,
    MContentableCardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMContentableCardModule {}
