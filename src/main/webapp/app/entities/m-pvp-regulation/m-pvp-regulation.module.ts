import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpRegulationComponent,
  MPvpRegulationDetailComponent,
  MPvpRegulationUpdateComponent,
  MPvpRegulationDeletePopupComponent,
  MPvpRegulationDeleteDialogComponent,
  mPvpRegulationRoute,
  mPvpRegulationPopupRoute
} from './';

const ENTITY_STATES = [...mPvpRegulationRoute, ...mPvpRegulationPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpRegulationComponent,
    MPvpRegulationDetailComponent,
    MPvpRegulationUpdateComponent,
    MPvpRegulationDeleteDialogComponent,
    MPvpRegulationDeletePopupComponent
  ],
  entryComponents: [
    MPvpRegulationComponent,
    MPvpRegulationUpdateComponent,
    MPvpRegulationDeleteDialogComponent,
    MPvpRegulationDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpRegulationModule {}
