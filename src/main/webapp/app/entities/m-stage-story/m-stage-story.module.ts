import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MStageStoryComponent,
  MStageStoryDetailComponent,
  MStageStoryUpdateComponent,
  MStageStoryDeletePopupComponent,
  MStageStoryDeleteDialogComponent,
  mStageStoryRoute,
  mStageStoryPopupRoute
} from './';

const ENTITY_STATES = [...mStageStoryRoute, ...mStageStoryPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MStageStoryComponent,
    MStageStoryDetailComponent,
    MStageStoryUpdateComponent,
    MStageStoryDeleteDialogComponent,
    MStageStoryDeletePopupComponent
  ],
  entryComponents: [MStageStoryComponent, MStageStoryUpdateComponent, MStageStoryDeleteDialogComponent, MStageStoryDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMStageStoryModule {}
