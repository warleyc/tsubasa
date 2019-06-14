import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MNpcPersonalityComponent,
  MNpcPersonalityDetailComponent,
  MNpcPersonalityUpdateComponent,
  MNpcPersonalityDeletePopupComponent,
  MNpcPersonalityDeleteDialogComponent,
  mNpcPersonalityRoute,
  mNpcPersonalityPopupRoute
} from './';

const ENTITY_STATES = [...mNpcPersonalityRoute, ...mNpcPersonalityPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MNpcPersonalityComponent,
    MNpcPersonalityDetailComponent,
    MNpcPersonalityUpdateComponent,
    MNpcPersonalityDeleteDialogComponent,
    MNpcPersonalityDeletePopupComponent
  ],
  entryComponents: [
    MNpcPersonalityComponent,
    MNpcPersonalityUpdateComponent,
    MNpcPersonalityDeleteDialogComponent,
    MNpcPersonalityDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMNpcPersonalityModule {}
