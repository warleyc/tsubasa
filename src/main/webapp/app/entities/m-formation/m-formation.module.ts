import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MFormationComponent,
  MFormationDetailComponent,
  MFormationUpdateComponent,
  MFormationDeletePopupComponent,
  MFormationDeleteDialogComponent,
  mFormationRoute,
  mFormationPopupRoute
} from './';

const ENTITY_STATES = [...mFormationRoute, ...mFormationPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MFormationComponent,
    MFormationDetailComponent,
    MFormationUpdateComponent,
    MFormationDeleteDialogComponent,
    MFormationDeletePopupComponent
  ],
  entryComponents: [MFormationComponent, MFormationUpdateComponent, MFormationDeleteDialogComponent, MFormationDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMFormationModule {}
