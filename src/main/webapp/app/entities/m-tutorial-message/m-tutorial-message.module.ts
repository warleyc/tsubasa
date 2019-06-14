import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTutorialMessageComponent,
  MTutorialMessageDetailComponent,
  MTutorialMessageUpdateComponent,
  MTutorialMessageDeletePopupComponent,
  MTutorialMessageDeleteDialogComponent,
  mTutorialMessageRoute,
  mTutorialMessagePopupRoute
} from './';

const ENTITY_STATES = [...mTutorialMessageRoute, ...mTutorialMessagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTutorialMessageComponent,
    MTutorialMessageDetailComponent,
    MTutorialMessageUpdateComponent,
    MTutorialMessageDeleteDialogComponent,
    MTutorialMessageDeletePopupComponent
  ],
  entryComponents: [
    MTutorialMessageComponent,
    MTutorialMessageUpdateComponent,
    MTutorialMessageDeleteDialogComponent,
    MTutorialMessageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTutorialMessageModule {}
