import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDefineComponent,
  MDefineDetailComponent,
  MDefineUpdateComponent,
  MDefineDeletePopupComponent,
  MDefineDeleteDialogComponent,
  mDefineRoute,
  mDefinePopupRoute
} from './';

const ENTITY_STATES = [...mDefineRoute, ...mDefinePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDefineComponent,
    MDefineDetailComponent,
    MDefineUpdateComponent,
    MDefineDeleteDialogComponent,
    MDefineDeletePopupComponent
  ],
  entryComponents: [MDefineComponent, MDefineUpdateComponent, MDefineDeleteDialogComponent, MDefineDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDefineModule {}
