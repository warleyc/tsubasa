import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MArousalComponent,
  MArousalDetailComponent,
  MArousalUpdateComponent,
  MArousalDeletePopupComponent,
  MArousalDeleteDialogComponent,
  mArousalRoute,
  mArousalPopupRoute
} from './';

const ENTITY_STATES = [...mArousalRoute, ...mArousalPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MArousalComponent,
    MArousalDetailComponent,
    MArousalUpdateComponent,
    MArousalDeleteDialogComponent,
    MArousalDeletePopupComponent
  ],
  entryComponents: [MArousalComponent, MArousalUpdateComponent, MArousalDeleteDialogComponent, MArousalDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMArousalModule {}
