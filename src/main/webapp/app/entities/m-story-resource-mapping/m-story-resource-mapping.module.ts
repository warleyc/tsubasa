import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MStoryResourceMappingComponent,
  MStoryResourceMappingDetailComponent,
  MStoryResourceMappingUpdateComponent,
  MStoryResourceMappingDeletePopupComponent,
  MStoryResourceMappingDeleteDialogComponent,
  mStoryResourceMappingRoute,
  mStoryResourceMappingPopupRoute
} from './';

const ENTITY_STATES = [...mStoryResourceMappingRoute, ...mStoryResourceMappingPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MStoryResourceMappingComponent,
    MStoryResourceMappingDetailComponent,
    MStoryResourceMappingUpdateComponent,
    MStoryResourceMappingDeleteDialogComponent,
    MStoryResourceMappingDeletePopupComponent
  ],
  entryComponents: [
    MStoryResourceMappingComponent,
    MStoryResourceMappingUpdateComponent,
    MStoryResourceMappingDeleteDialogComponent,
    MStoryResourceMappingDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMStoryResourceMappingModule {}
