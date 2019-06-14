import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSellCardCoinComponent,
  MSellCardCoinDetailComponent,
  MSellCardCoinUpdateComponent,
  MSellCardCoinDeletePopupComponent,
  MSellCardCoinDeleteDialogComponent,
  mSellCardCoinRoute,
  mSellCardCoinPopupRoute
} from './';

const ENTITY_STATES = [...mSellCardCoinRoute, ...mSellCardCoinPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSellCardCoinComponent,
    MSellCardCoinDetailComponent,
    MSellCardCoinUpdateComponent,
    MSellCardCoinDeleteDialogComponent,
    MSellCardCoinDeletePopupComponent
  ],
  entryComponents: [
    MSellCardCoinComponent,
    MSellCardCoinUpdateComponent,
    MSellCardCoinDeleteDialogComponent,
    MSellCardCoinDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSellCardCoinModule {}
