import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MDistributeParamPointComponent,
  MDistributeParamPointDetailComponent,
  MDistributeParamPointUpdateComponent,
  MDistributeParamPointDeletePopupComponent,
  MDistributeParamPointDeleteDialogComponent,
  mDistributeParamPointRoute,
  mDistributeParamPointPopupRoute
} from './';

const ENTITY_STATES = [...mDistributeParamPointRoute, ...mDistributeParamPointPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MDistributeParamPointComponent,
    MDistributeParamPointDetailComponent,
    MDistributeParamPointUpdateComponent,
    MDistributeParamPointDeleteDialogComponent,
    MDistributeParamPointDeletePopupComponent
  ],
  entryComponents: [
    MDistributeParamPointComponent,
    MDistributeParamPointUpdateComponent,
    MDistributeParamPointDeleteDialogComponent,
    MDistributeParamPointDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMDistributeParamPointModule {}
