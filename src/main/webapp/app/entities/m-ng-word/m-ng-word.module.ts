import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MNgWordComponent,
  MNgWordDetailComponent,
  MNgWordUpdateComponent,
  MNgWordDeletePopupComponent,
  MNgWordDeleteDialogComponent,
  mNgWordRoute,
  mNgWordPopupRoute
} from './';

const ENTITY_STATES = [...mNgWordRoute, ...mNgWordPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MNgWordComponent,
    MNgWordDetailComponent,
    MNgWordUpdateComponent,
    MNgWordDeleteDialogComponent,
    MNgWordDeletePopupComponent
  ],
  entryComponents: [MNgWordComponent, MNgWordUpdateComponent, MNgWordDeleteDialogComponent, MNgWordDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMNgWordModule {}
