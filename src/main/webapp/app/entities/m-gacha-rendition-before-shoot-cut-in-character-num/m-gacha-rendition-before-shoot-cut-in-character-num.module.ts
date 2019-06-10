import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionBeforeShootCutInCharacterNumComponent,
  MGachaRenditionBeforeShootCutInCharacterNumDetailComponent,
  MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent,
  MGachaRenditionBeforeShootCutInCharacterNumDeletePopupComponent,
  MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent,
  mGachaRenditionBeforeShootCutInCharacterNumRoute,
  mGachaRenditionBeforeShootCutInCharacterNumPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionBeforeShootCutInCharacterNumRoute, ...mGachaRenditionBeforeShootCutInCharacterNumPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionBeforeShootCutInCharacterNumComponent,
    MGachaRenditionBeforeShootCutInCharacterNumDetailComponent,
    MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent,
    MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent,
    MGachaRenditionBeforeShootCutInCharacterNumDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionBeforeShootCutInCharacterNumComponent,
    MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent,
    MGachaRenditionBeforeShootCutInCharacterNumDeleteDialogComponent,
    MGachaRenditionBeforeShootCutInCharacterNumDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionBeforeShootCutInCharacterNumModule {}
