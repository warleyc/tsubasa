import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionExtraCutinComponent,
  MGachaRenditionExtraCutinDetailComponent,
  MGachaRenditionExtraCutinUpdateComponent,
  MGachaRenditionExtraCutinDeletePopupComponent,
  MGachaRenditionExtraCutinDeleteDialogComponent,
  mGachaRenditionExtraCutinRoute,
  mGachaRenditionExtraCutinPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionExtraCutinRoute, ...mGachaRenditionExtraCutinPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionExtraCutinComponent,
    MGachaRenditionExtraCutinDetailComponent,
    MGachaRenditionExtraCutinUpdateComponent,
    MGachaRenditionExtraCutinDeleteDialogComponent,
    MGachaRenditionExtraCutinDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionExtraCutinComponent,
    MGachaRenditionExtraCutinUpdateComponent,
    MGachaRenditionExtraCutinDeleteDialogComponent,
    MGachaRenditionExtraCutinDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionExtraCutinModule {}
