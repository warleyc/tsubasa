import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionTrajectoryCutInComponent,
  MGachaRenditionTrajectoryCutInDetailComponent,
  MGachaRenditionTrajectoryCutInUpdateComponent,
  MGachaRenditionTrajectoryCutInDeletePopupComponent,
  MGachaRenditionTrajectoryCutInDeleteDialogComponent,
  mGachaRenditionTrajectoryCutInRoute,
  mGachaRenditionTrajectoryCutInPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionTrajectoryCutInRoute, ...mGachaRenditionTrajectoryCutInPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionTrajectoryCutInComponent,
    MGachaRenditionTrajectoryCutInDetailComponent,
    MGachaRenditionTrajectoryCutInUpdateComponent,
    MGachaRenditionTrajectoryCutInDeleteDialogComponent,
    MGachaRenditionTrajectoryCutInDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionTrajectoryCutInComponent,
    MGachaRenditionTrajectoryCutInUpdateComponent,
    MGachaRenditionTrajectoryCutInDeleteDialogComponent,
    MGachaRenditionTrajectoryCutInDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionTrajectoryCutInModule {}
