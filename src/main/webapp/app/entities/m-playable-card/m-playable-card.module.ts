import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPlayableCardComponent,
  MPlayableCardDetailComponent,
  MPlayableCardUpdateComponent,
  MPlayableCardDeletePopupComponent,
  MPlayableCardDeleteDialogComponent,
  mPlayableCardRoute,
  mPlayableCardPopupRoute
} from './';

const ENTITY_STATES = [...mPlayableCardRoute, ...mPlayableCardPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPlayableCardComponent,
    MPlayableCardDetailComponent,
    MPlayableCardUpdateComponent,
    MPlayableCardDeleteDialogComponent,
    MPlayableCardDeletePopupComponent
  ],
  entryComponents: [
    MPlayableCardComponent,
    MPlayableCardUpdateComponent,
    MPlayableCardDeleteDialogComponent,
    MPlayableCardDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPlayableCardModule {}
