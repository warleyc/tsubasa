import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MExtraNewsComponent,
  MExtraNewsDetailComponent,
  MExtraNewsUpdateComponent,
  MExtraNewsDeletePopupComponent,
  MExtraNewsDeleteDialogComponent,
  mExtraNewsRoute,
  mExtraNewsPopupRoute
} from './';

const ENTITY_STATES = [...mExtraNewsRoute, ...mExtraNewsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MExtraNewsComponent,
    MExtraNewsDetailComponent,
    MExtraNewsUpdateComponent,
    MExtraNewsDeleteDialogComponent,
    MExtraNewsDeletePopupComponent
  ],
  entryComponents: [MExtraNewsComponent, MExtraNewsUpdateComponent, MExtraNewsDeleteDialogComponent, MExtraNewsDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMExtraNewsModule {}
