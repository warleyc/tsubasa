import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTipsComponent,
  MTipsDetailComponent,
  MTipsUpdateComponent,
  MTipsDeletePopupComponent,
  MTipsDeleteDialogComponent,
  mTipsRoute,
  mTipsPopupRoute
} from './';

const ENTITY_STATES = [...mTipsRoute, ...mTipsPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MTipsComponent, MTipsDetailComponent, MTipsUpdateComponent, MTipsDeleteDialogComponent, MTipsDeletePopupComponent],
  entryComponents: [MTipsComponent, MTipsUpdateComponent, MTipsDeleteDialogComponent, MTipsDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTipsModule {}
