import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MArousalItemComponent,
  MArousalItemDetailComponent,
  MArousalItemUpdateComponent,
  MArousalItemDeletePopupComponent,
  MArousalItemDeleteDialogComponent,
  mArousalItemRoute,
  mArousalItemPopupRoute
} from './';

const ENTITY_STATES = [...mArousalItemRoute, ...mArousalItemPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MArousalItemComponent,
    MArousalItemDetailComponent,
    MArousalItemUpdateComponent,
    MArousalItemDeleteDialogComponent,
    MArousalItemDeletePopupComponent
  ],
  entryComponents: [
    MArousalItemComponent,
    MArousalItemUpdateComponent,
    MArousalItemDeleteDialogComponent,
    MArousalItemDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMArousalItemModule {}
