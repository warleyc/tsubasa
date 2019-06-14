import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonBoostScheduleComponent,
  MMarathonBoostScheduleDetailComponent,
  MMarathonBoostScheduleUpdateComponent,
  MMarathonBoostScheduleDeletePopupComponent,
  MMarathonBoostScheduleDeleteDialogComponent,
  mMarathonBoostScheduleRoute,
  mMarathonBoostSchedulePopupRoute
} from './';

const ENTITY_STATES = [...mMarathonBoostScheduleRoute, ...mMarathonBoostSchedulePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonBoostScheduleComponent,
    MMarathonBoostScheduleDetailComponent,
    MMarathonBoostScheduleUpdateComponent,
    MMarathonBoostScheduleDeleteDialogComponent,
    MMarathonBoostScheduleDeletePopupComponent
  ],
  entryComponents: [
    MMarathonBoostScheduleComponent,
    MMarathonBoostScheduleUpdateComponent,
    MMarathonBoostScheduleDeleteDialogComponent,
    MMarathonBoostScheduleDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonBoostScheduleModule {}
