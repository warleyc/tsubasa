import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionBeforeShootCutInPatternComponent,
  MGachaRenditionBeforeShootCutInPatternDetailComponent,
  MGachaRenditionBeforeShootCutInPatternUpdateComponent,
  MGachaRenditionBeforeShootCutInPatternDeletePopupComponent,
  MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent,
  mGachaRenditionBeforeShootCutInPatternRoute,
  mGachaRenditionBeforeShootCutInPatternPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionBeforeShootCutInPatternRoute, ...mGachaRenditionBeforeShootCutInPatternPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionBeforeShootCutInPatternComponent,
    MGachaRenditionBeforeShootCutInPatternDetailComponent,
    MGachaRenditionBeforeShootCutInPatternUpdateComponent,
    MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent,
    MGachaRenditionBeforeShootCutInPatternDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionBeforeShootCutInPatternComponent,
    MGachaRenditionBeforeShootCutInPatternUpdateComponent,
    MGachaRenditionBeforeShootCutInPatternDeleteDialogComponent,
    MGachaRenditionBeforeShootCutInPatternDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionBeforeShootCutInPatternModule {}
