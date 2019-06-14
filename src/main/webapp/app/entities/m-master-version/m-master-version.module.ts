import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMasterVersionComponent,
  MMasterVersionDetailComponent,
  MMasterVersionUpdateComponent,
  MMasterVersionDeletePopupComponent,
  MMasterVersionDeleteDialogComponent,
  mMasterVersionRoute,
  mMasterVersionPopupRoute
} from './';

const ENTITY_STATES = [...mMasterVersionRoute, ...mMasterVersionPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMasterVersionComponent,
    MMasterVersionDetailComponent,
    MMasterVersionUpdateComponent,
    MMasterVersionDeleteDialogComponent,
    MMasterVersionDeletePopupComponent
  ],
  entryComponents: [
    MMasterVersionComponent,
    MMasterVersionUpdateComponent,
    MMasterVersionDeleteDialogComponent,
    MMasterVersionDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMasterVersionModule {}
