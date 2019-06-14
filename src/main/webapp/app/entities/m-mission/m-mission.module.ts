import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMissionComponent,
  MMissionDetailComponent,
  MMissionUpdateComponent,
  MMissionDeletePopupComponent,
  MMissionDeleteDialogComponent,
  mMissionRoute,
  mMissionPopupRoute
} from './';

const ENTITY_STATES = [...mMissionRoute, ...mMissionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMissionComponent,
    MMissionDetailComponent,
    MMissionUpdateComponent,
    MMissionDeleteDialogComponent,
    MMissionDeletePopupComponent
  ],
  entryComponents: [MMissionComponent, MMissionUpdateComponent, MMissionDeleteDialogComponent, MMissionDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMissionModule {}
