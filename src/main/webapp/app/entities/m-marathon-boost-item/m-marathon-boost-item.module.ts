import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MMarathonBoostItemComponent,
  MMarathonBoostItemDetailComponent,
  MMarathonBoostItemUpdateComponent,
  MMarathonBoostItemDeletePopupComponent,
  MMarathonBoostItemDeleteDialogComponent,
  mMarathonBoostItemRoute,
  mMarathonBoostItemPopupRoute
} from './';

const ENTITY_STATES = [...mMarathonBoostItemRoute, ...mMarathonBoostItemPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MMarathonBoostItemComponent,
    MMarathonBoostItemDetailComponent,
    MMarathonBoostItemUpdateComponent,
    MMarathonBoostItemDeleteDialogComponent,
    MMarathonBoostItemDeletePopupComponent
  ],
  entryComponents: [
    MMarathonBoostItemComponent,
    MMarathonBoostItemUpdateComponent,
    MMarathonBoostItemDeleteDialogComponent,
    MMarathonBoostItemDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMMarathonBoostItemModule {}
