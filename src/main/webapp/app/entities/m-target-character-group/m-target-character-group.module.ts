import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetCharacterGroupComponent,
  MTargetCharacterGroupDetailComponent,
  MTargetCharacterGroupUpdateComponent,
  MTargetCharacterGroupDeletePopupComponent,
  MTargetCharacterGroupDeleteDialogComponent,
  mTargetCharacterGroupRoute,
  mTargetCharacterGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetCharacterGroupRoute, ...mTargetCharacterGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetCharacterGroupComponent,
    MTargetCharacterGroupDetailComponent,
    MTargetCharacterGroupUpdateComponent,
    MTargetCharacterGroupDeleteDialogComponent,
    MTargetCharacterGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetCharacterGroupComponent,
    MTargetCharacterGroupUpdateComponent,
    MTargetCharacterGroupDeleteDialogComponent,
    MTargetCharacterGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetCharacterGroupModule {}
