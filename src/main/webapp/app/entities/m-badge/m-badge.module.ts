import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MBadgeComponent,
  MBadgeDetailComponent,
  MBadgeUpdateComponent,
  MBadgeDeletePopupComponent,
  MBadgeDeleteDialogComponent,
  mBadgeRoute,
  mBadgePopupRoute
} from './';

const ENTITY_STATES = [...mBadgeRoute, ...mBadgePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MBadgeComponent, MBadgeDetailComponent, MBadgeUpdateComponent, MBadgeDeleteDialogComponent, MBadgeDeletePopupComponent],
  entryComponents: [MBadgeComponent, MBadgeUpdateComponent, MBadgeDeleteDialogComponent, MBadgeDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMBadgeModule {}
