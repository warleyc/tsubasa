import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MCutShootOrbitComponent,
  MCutShootOrbitDetailComponent,
  MCutShootOrbitUpdateComponent,
  MCutShootOrbitDeletePopupComponent,
  MCutShootOrbitDeleteDialogComponent,
  mCutShootOrbitRoute,
  mCutShootOrbitPopupRoute
} from './';

const ENTITY_STATES = [...mCutShootOrbitRoute, ...mCutShootOrbitPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MCutShootOrbitComponent,
    MCutShootOrbitDetailComponent,
    MCutShootOrbitUpdateComponent,
    MCutShootOrbitDeleteDialogComponent,
    MCutShootOrbitDeletePopupComponent
  ],
  entryComponents: [
    MCutShootOrbitComponent,
    MCutShootOrbitUpdateComponent,
    MCutShootOrbitDeleteDialogComponent,
    MCutShootOrbitDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMCutShootOrbitModule {}
