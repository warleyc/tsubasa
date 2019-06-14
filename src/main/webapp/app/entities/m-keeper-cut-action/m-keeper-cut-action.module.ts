import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MKeeperCutActionComponent,
  MKeeperCutActionDetailComponent,
  MKeeperCutActionUpdateComponent,
  MKeeperCutActionDeletePopupComponent,
  MKeeperCutActionDeleteDialogComponent,
  mKeeperCutActionRoute,
  mKeeperCutActionPopupRoute
} from './';

const ENTITY_STATES = [...mKeeperCutActionRoute, ...mKeeperCutActionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MKeeperCutActionComponent,
    MKeeperCutActionDetailComponent,
    MKeeperCutActionUpdateComponent,
    MKeeperCutActionDeleteDialogComponent,
    MKeeperCutActionDeletePopupComponent
  ],
  entryComponents: [
    MKeeperCutActionComponent,
    MKeeperCutActionUpdateComponent,
    MKeeperCutActionDeleteDialogComponent,
    MKeeperCutActionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMKeeperCutActionModule {}
