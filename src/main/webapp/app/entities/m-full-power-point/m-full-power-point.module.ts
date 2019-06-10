import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MFullPowerPointComponent,
  MFullPowerPointDetailComponent,
  MFullPowerPointUpdateComponent,
  MFullPowerPointDeletePopupComponent,
  MFullPowerPointDeleteDialogComponent,
  mFullPowerPointRoute,
  mFullPowerPointPopupRoute
} from './';

const ENTITY_STATES = [...mFullPowerPointRoute, ...mFullPowerPointPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MFullPowerPointComponent,
    MFullPowerPointDetailComponent,
    MFullPowerPointUpdateComponent,
    MFullPowerPointDeleteDialogComponent,
    MFullPowerPointDeletePopupComponent
  ],
  entryComponents: [
    MFullPowerPointComponent,
    MFullPowerPointUpdateComponent,
    MFullPowerPointDeleteDialogComponent,
    MFullPowerPointDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMFullPowerPointModule {}
