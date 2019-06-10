import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDummyEmblemComponent,
  MDummyEmblemDetailComponent,
  MDummyEmblemUpdateComponent,
  MDummyEmblemDeletePopupComponent,
  MDummyEmblemDeleteDialogComponent,
  mDummyEmblemRoute,
  mDummyEmblemPopupRoute
} from './';

const ENTITY_STATES = [...mDummyEmblemRoute, ...mDummyEmblemPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDummyEmblemComponent,
    MDummyEmblemDetailComponent,
    MDummyEmblemUpdateComponent,
    MDummyEmblemDeleteDialogComponent,
    MDummyEmblemDeletePopupComponent
  ],
  entryComponents: [
    MDummyEmblemComponent,
    MDummyEmblemUpdateComponent,
    MDummyEmblemDeleteDialogComponent,
    MDummyEmblemDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDummyEmblemModule {}
