import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MShootComponent,
  MShootDetailComponent,
  MShootUpdateComponent,
  MShootDeletePopupComponent,
  MShootDeleteDialogComponent,
  mShootRoute,
  mShootPopupRoute
} from './';

const ENTITY_STATES = [...mShootRoute, ...mShootPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MShootComponent, MShootDetailComponent, MShootUpdateComponent, MShootDeleteDialogComponent, MShootDeletePopupComponent],
  entryComponents: [MShootComponent, MShootUpdateComponent, MShootDeleteDialogComponent, MShootDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMShootModule {}
