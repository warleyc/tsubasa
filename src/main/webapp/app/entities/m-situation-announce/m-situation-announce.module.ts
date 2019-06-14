import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSituationAnnounceComponent,
  MSituationAnnounceDetailComponent,
  MSituationAnnounceUpdateComponent,
  MSituationAnnounceDeletePopupComponent,
  MSituationAnnounceDeleteDialogComponent,
  mSituationAnnounceRoute,
  mSituationAnnouncePopupRoute
} from './';

const ENTITY_STATES = [...mSituationAnnounceRoute, ...mSituationAnnouncePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSituationAnnounceComponent,
    MSituationAnnounceDetailComponent,
    MSituationAnnounceUpdateComponent,
    MSituationAnnounceDeleteDialogComponent,
    MSituationAnnounceDeletePopupComponent
  ],
  entryComponents: [
    MSituationAnnounceComponent,
    MSituationAnnounceUpdateComponent,
    MSituationAnnounceDeleteDialogComponent,
    MSituationAnnounceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSituationAnnounceModule {}
