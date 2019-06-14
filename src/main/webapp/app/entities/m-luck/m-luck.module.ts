import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MLuckComponent,
  MLuckDetailComponent,
  MLuckUpdateComponent,
  MLuckDeletePopupComponent,
  MLuckDeleteDialogComponent,
  mLuckRoute,
  mLuckPopupRoute
} from './';

const ENTITY_STATES = [...mLuckRoute, ...mLuckPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MLuckComponent, MLuckDetailComponent, MLuckUpdateComponent, MLuckDeleteDialogComponent, MLuckDeletePopupComponent],
  entryComponents: [MLuckComponent, MLuckUpdateComponent, MLuckDeleteDialogComponent, MLuckDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMLuckModule {}
