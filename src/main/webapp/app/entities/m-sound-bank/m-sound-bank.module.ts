import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MSoundBankComponent,
  MSoundBankDetailComponent,
  MSoundBankUpdateComponent,
  MSoundBankDeletePopupComponent,
  MSoundBankDeleteDialogComponent,
  mSoundBankRoute,
  mSoundBankPopupRoute
} from './';

const ENTITY_STATES = [...mSoundBankRoute, ...mSoundBankPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MSoundBankComponent,
    MSoundBankDetailComponent,
    MSoundBankUpdateComponent,
    MSoundBankDeleteDialogComponent,
    MSoundBankDeletePopupComponent
  ],
  entryComponents: [MSoundBankComponent, MSoundBankUpdateComponent, MSoundBankDeleteDialogComponent, MSoundBankDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMSoundBankModule {}
