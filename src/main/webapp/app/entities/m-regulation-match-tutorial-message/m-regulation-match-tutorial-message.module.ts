import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MRegulationMatchTutorialMessageComponent,
  MRegulationMatchTutorialMessageDetailComponent,
  MRegulationMatchTutorialMessageUpdateComponent,
  MRegulationMatchTutorialMessageDeletePopupComponent,
  MRegulationMatchTutorialMessageDeleteDialogComponent,
  mRegulationMatchTutorialMessageRoute,
  mRegulationMatchTutorialMessagePopupRoute
} from './';

const ENTITY_STATES = [...mRegulationMatchTutorialMessageRoute, ...mRegulationMatchTutorialMessagePopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MRegulationMatchTutorialMessageComponent,
    MRegulationMatchTutorialMessageDetailComponent,
    MRegulationMatchTutorialMessageUpdateComponent,
    MRegulationMatchTutorialMessageDeleteDialogComponent,
    MRegulationMatchTutorialMessageDeletePopupComponent
  ],
  entryComponents: [
    MRegulationMatchTutorialMessageComponent,
    MRegulationMatchTutorialMessageUpdateComponent,
    MRegulationMatchTutorialMessageDeleteDialogComponent,
    MRegulationMatchTutorialMessageDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMRegulationMatchTutorialMessageModule {}
