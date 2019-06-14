import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSituationComponent,
  MSituationDetailComponent,
  MSituationUpdateComponent,
  MSituationDeletePopupComponent,
  MSituationDeleteDialogComponent,
  mSituationRoute,
  mSituationPopupRoute
} from './';

const ENTITY_STATES = [...mSituationRoute, ...mSituationPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSituationComponent,
    MSituationDetailComponent,
    MSituationUpdateComponent,
    MSituationDeleteDialogComponent,
    MSituationDeletePopupComponent
  ],
  entryComponents: [MSituationComponent, MSituationUpdateComponent, MSituationDeleteDialogComponent, MSituationDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSituationModule {}
