import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MAnnounceTextComponent,
  MAnnounceTextDetailComponent,
  MAnnounceTextUpdateComponent,
  MAnnounceTextDeletePopupComponent,
  MAnnounceTextDeleteDialogComponent,
  mAnnounceTextRoute,
  mAnnounceTextPopupRoute
} from './';

const ENTITY_STATES = [...mAnnounceTextRoute, ...mAnnounceTextPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MAnnounceTextComponent,
    MAnnounceTextDetailComponent,
    MAnnounceTextUpdateComponent,
    MAnnounceTextDeleteDialogComponent,
    MAnnounceTextDeletePopupComponent
  ],
  entryComponents: [
    MAnnounceTextComponent,
    MAnnounceTextUpdateComponent,
    MAnnounceTextDeleteDialogComponent,
    MAnnounceTextDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMAnnounceTextModule {}
