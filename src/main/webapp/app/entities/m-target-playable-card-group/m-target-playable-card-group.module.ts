import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTargetPlayableCardGroupComponent,
  MTargetPlayableCardGroupDetailComponent,
  MTargetPlayableCardGroupUpdateComponent,
  MTargetPlayableCardGroupDeletePopupComponent,
  MTargetPlayableCardGroupDeleteDialogComponent,
  mTargetPlayableCardGroupRoute,
  mTargetPlayableCardGroupPopupRoute
} from './';

const ENTITY_STATES = [...mTargetPlayableCardGroupRoute, ...mTargetPlayableCardGroupPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTargetPlayableCardGroupComponent,
    MTargetPlayableCardGroupDetailComponent,
    MTargetPlayableCardGroupUpdateComponent,
    MTargetPlayableCardGroupDeleteDialogComponent,
    MTargetPlayableCardGroupDeletePopupComponent
  ],
  entryComponents: [
    MTargetPlayableCardGroupComponent,
    MTargetPlayableCardGroupUpdateComponent,
    MTargetPlayableCardGroupDeleteDialogComponent,
    MTargetPlayableCardGroupDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTargetPlayableCardGroupModule {}
