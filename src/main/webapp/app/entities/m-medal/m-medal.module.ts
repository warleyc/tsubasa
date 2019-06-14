import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMedalComponent,
  MMedalDetailComponent,
  MMedalUpdateComponent,
  MMedalDeletePopupComponent,
  MMedalDeleteDialogComponent,
  mMedalRoute,
  mMedalPopupRoute
} from './';

const ENTITY_STATES = [...mMedalRoute, ...mMedalPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MMedalComponent, MMedalDetailComponent, MMedalUpdateComponent, MMedalDeleteDialogComponent, MMedalDeletePopupComponent],
  entryComponents: [MMedalComponent, MMedalUpdateComponent, MMedalDeleteDialogComponent, MMedalDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMedalModule {}
