import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMatchOptionComponent,
  MMatchOptionDetailComponent,
  MMatchOptionUpdateComponent,
  MMatchOptionDeletePopupComponent,
  MMatchOptionDeleteDialogComponent,
  mMatchOptionRoute,
  mMatchOptionPopupRoute
} from './';

const ENTITY_STATES = [...mMatchOptionRoute, ...mMatchOptionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMatchOptionComponent,
    MMatchOptionDetailComponent,
    MMatchOptionUpdateComponent,
    MMatchOptionDeleteDialogComponent,
    MMatchOptionDeletePopupComponent
  ],
  entryComponents: [
    MMatchOptionComponent,
    MMatchOptionUpdateComponent,
    MMatchOptionDeleteDialogComponent,
    MMatchOptionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMatchOptionModule {}
