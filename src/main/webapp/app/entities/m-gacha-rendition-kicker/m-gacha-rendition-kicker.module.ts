import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionKickerComponent,
  MGachaRenditionKickerDetailComponent,
  MGachaRenditionKickerUpdateComponent,
  MGachaRenditionKickerDeletePopupComponent,
  MGachaRenditionKickerDeleteDialogComponent,
  mGachaRenditionKickerRoute,
  mGachaRenditionKickerPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionKickerRoute, ...mGachaRenditionKickerPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionKickerComponent,
    MGachaRenditionKickerDetailComponent,
    MGachaRenditionKickerUpdateComponent,
    MGachaRenditionKickerDeleteDialogComponent,
    MGachaRenditionKickerDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionKickerComponent,
    MGachaRenditionKickerUpdateComponent,
    MGachaRenditionKickerDeleteDialogComponent,
    MGachaRenditionKickerDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionKickerModule {}
