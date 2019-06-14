import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMatchFormationComponent,
  MMatchFormationDetailComponent,
  MMatchFormationUpdateComponent,
  MMatchFormationDeletePopupComponent,
  MMatchFormationDeleteDialogComponent,
  mMatchFormationRoute,
  mMatchFormationPopupRoute
} from './';

const ENTITY_STATES = [...mMatchFormationRoute, ...mMatchFormationPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMatchFormationComponent,
    MMatchFormationDetailComponent,
    MMatchFormationUpdateComponent,
    MMatchFormationDeleteDialogComponent,
    MMatchFormationDeletePopupComponent
  ],
  entryComponents: [
    MMatchFormationComponent,
    MMatchFormationUpdateComponent,
    MMatchFormationDeleteDialogComponent,
    MMatchFormationDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMatchFormationModule {}
