import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionTradeSignComponent,
  MGachaRenditionTradeSignDetailComponent,
  MGachaRenditionTradeSignUpdateComponent,
  MGachaRenditionTradeSignDeletePopupComponent,
  MGachaRenditionTradeSignDeleteDialogComponent,
  mGachaRenditionTradeSignRoute,
  mGachaRenditionTradeSignPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionTradeSignRoute, ...mGachaRenditionTradeSignPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionTradeSignComponent,
    MGachaRenditionTradeSignDetailComponent,
    MGachaRenditionTradeSignUpdateComponent,
    MGachaRenditionTradeSignDeleteDialogComponent,
    MGachaRenditionTradeSignDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionTradeSignComponent,
    MGachaRenditionTradeSignUpdateComponent,
    MGachaRenditionTradeSignDeleteDialogComponent,
    MGachaRenditionTradeSignDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionTradeSignModule {}
