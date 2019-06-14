import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MTeamComponent,
  MTeamDetailComponent,
  MTeamUpdateComponent,
  MTeamDeletePopupComponent,
  MTeamDeleteDialogComponent,
  mTeamRoute,
  mTeamPopupRoute
} from './';

const ENTITY_STATES = [...mTeamRoute, ...mTeamPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [MTeamComponent, MTeamDetailComponent, MTeamUpdateComponent, MTeamDeleteDialogComponent, MTeamDeletePopupComponent],
  entryComponents: [MTeamComponent, MTeamUpdateComponent, MTeamDeleteDialogComponent, MTeamDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMTeamModule {}
