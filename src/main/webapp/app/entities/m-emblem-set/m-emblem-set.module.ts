import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MEmblemSetComponent,
  MEmblemSetDetailComponent,
  MEmblemSetUpdateComponent,
  MEmblemSetDeletePopupComponent,
  MEmblemSetDeleteDialogComponent,
  mEmblemSetRoute,
  mEmblemSetPopupRoute
} from './';

const ENTITY_STATES = [...mEmblemSetRoute, ...mEmblemSetPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MEmblemSetComponent,
    MEmblemSetDetailComponent,
    MEmblemSetUpdateComponent,
    MEmblemSetDeleteDialogComponent,
    MEmblemSetDeletePopupComponent
  ],
  entryComponents: [MEmblemSetComponent, MEmblemSetUpdateComponent, MEmblemSetDeleteDialogComponent, MEmblemSetDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMEmblemSetModule {}
