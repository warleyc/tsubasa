import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MPvpWaveComponent,
  MPvpWaveDetailComponent,
  MPvpWaveUpdateComponent,
  MPvpWaveDeletePopupComponent,
  MPvpWaveDeleteDialogComponent,
  mPvpWaveRoute,
  mPvpWavePopupRoute
} from './';

const ENTITY_STATES = [...mPvpWaveRoute, ...mPvpWavePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MPvpWaveComponent,
    MPvpWaveDetailComponent,
    MPvpWaveUpdateComponent,
    MPvpWaveDeleteDialogComponent,
    MPvpWaveDeletePopupComponent
  ],
  entryComponents: [MPvpWaveComponent, MPvpWaveUpdateComponent, MPvpWaveDeleteDialogComponent, MPvpWaveDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMPvpWaveModule {}
