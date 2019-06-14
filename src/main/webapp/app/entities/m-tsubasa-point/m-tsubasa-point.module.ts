import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTsubasaPointComponent,
  MTsubasaPointDetailComponent,
  MTsubasaPointUpdateComponent,
  MTsubasaPointDeletePopupComponent,
  MTsubasaPointDeleteDialogComponent,
  mTsubasaPointRoute,
  mTsubasaPointPopupRoute
} from './';

const ENTITY_STATES = [...mTsubasaPointRoute, ...mTsubasaPointPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MTsubasaPointComponent,
    MTsubasaPointDetailComponent,
    MTsubasaPointUpdateComponent,
    MTsubasaPointDeleteDialogComponent,
    MTsubasaPointDeletePopupComponent
  ],
  entryComponents: [
    MTsubasaPointComponent,
    MTsubasaPointUpdateComponent,
    MTsubasaPointDeleteDialogComponent,
    MTsubasaPointDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTsubasaPointModule {}
