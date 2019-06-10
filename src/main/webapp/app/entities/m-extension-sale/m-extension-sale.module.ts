import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MExtensionSaleComponent,
  MExtensionSaleDetailComponent,
  MExtensionSaleUpdateComponent,
  MExtensionSaleDeletePopupComponent,
  MExtensionSaleDeleteDialogComponent,
  mExtensionSaleRoute,
  mExtensionSalePopupRoute
} from './';

const ENTITY_STATES = [...mExtensionSaleRoute, ...mExtensionSalePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MExtensionSaleComponent,
    MExtensionSaleDetailComponent,
    MExtensionSaleUpdateComponent,
    MExtensionSaleDeleteDialogComponent,
    MExtensionSaleDeletePopupComponent
  ],
  entryComponents: [
    MExtensionSaleComponent,
    MExtensionSaleUpdateComponent,
    MExtensionSaleDeleteDialogComponent,
    MExtensionSaleDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMExtensionSaleModule {}
