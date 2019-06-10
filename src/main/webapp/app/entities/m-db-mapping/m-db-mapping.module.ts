import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDbMappingComponent,
  MDbMappingDetailComponent,
  MDbMappingUpdateComponent,
  MDbMappingDeletePopupComponent,
  MDbMappingDeleteDialogComponent,
  mDbMappingRoute,
  mDbMappingPopupRoute
} from './';

const ENTITY_STATES = [...mDbMappingRoute, ...mDbMappingPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDbMappingComponent,
    MDbMappingDetailComponent,
    MDbMappingUpdateComponent,
    MDbMappingDeleteDialogComponent,
    MDbMappingDeletePopupComponent
  ],
  entryComponents: [MDbMappingComponent, MDbMappingUpdateComponent, MDbMappingDeleteDialogComponent, MDbMappingDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDbMappingModule {}
