import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MApRecoveryItemComponent,
  MApRecoveryItemDetailComponent,
  MApRecoveryItemUpdateComponent,
  MApRecoveryItemDeletePopupComponent,
  MApRecoveryItemDeleteDialogComponent,
  mApRecoveryItemRoute,
  mApRecoveryItemPopupRoute
} from './';

const ENTITY_STATES = [...mApRecoveryItemRoute, ...mApRecoveryItemPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MApRecoveryItemComponent,
    MApRecoveryItemDetailComponent,
    MApRecoveryItemUpdateComponent,
    MApRecoveryItemDeleteDialogComponent,
    MApRecoveryItemDeletePopupComponent
  ],
  entryComponents: [
    MApRecoveryItemComponent,
    MApRecoveryItemUpdateComponent,
    MApRecoveryItemDeleteDialogComponent,
    MApRecoveryItemDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMApRecoveryItemModule {}
