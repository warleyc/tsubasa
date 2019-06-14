import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSceneTutorialMessageComponent,
  MSceneTutorialMessageDetailComponent,
  MSceneTutorialMessageUpdateComponent,
  MSceneTutorialMessageDeletePopupComponent,
  MSceneTutorialMessageDeleteDialogComponent,
  mSceneTutorialMessageRoute,
  mSceneTutorialMessagePopupRoute
} from './';

const ENTITY_STATES = [...mSceneTutorialMessageRoute, ...mSceneTutorialMessagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSceneTutorialMessageComponent,
    MSceneTutorialMessageDetailComponent,
    MSceneTutorialMessageUpdateComponent,
    MSceneTutorialMessageDeleteDialogComponent,
    MSceneTutorialMessageDeletePopupComponent
  ],
  entryComponents: [
    MSceneTutorialMessageComponent,
    MSceneTutorialMessageUpdateComponent,
    MSceneTutorialMessageDeleteDialogComponent,
    MSceneTutorialMessageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSceneTutorialMessageModule {}
