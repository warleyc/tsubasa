import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSoundBankEventComponent,
  MSoundBankEventDetailComponent,
  MSoundBankEventUpdateComponent,
  MSoundBankEventDeletePopupComponent,
  MSoundBankEventDeleteDialogComponent,
  mSoundBankEventRoute,
  mSoundBankEventPopupRoute
} from './';

const ENTITY_STATES = [...mSoundBankEventRoute, ...mSoundBankEventPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSoundBankEventComponent,
    MSoundBankEventDetailComponent,
    MSoundBankEventUpdateComponent,
    MSoundBankEventDeleteDialogComponent,
    MSoundBankEventDeletePopupComponent
  ],
  entryComponents: [
    MSoundBankEventComponent,
    MSoundBankEventUpdateComponent,
    MSoundBankEventDeleteDialogComponent,
    MSoundBankEventDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSoundBankEventModule {}
