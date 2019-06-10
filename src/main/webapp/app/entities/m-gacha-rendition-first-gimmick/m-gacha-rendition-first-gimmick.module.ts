import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MGachaRenditionFirstGimmickComponent,
  MGachaRenditionFirstGimmickDetailComponent,
  MGachaRenditionFirstGimmickUpdateComponent,
  MGachaRenditionFirstGimmickDeletePopupComponent,
  MGachaRenditionFirstGimmickDeleteDialogComponent,
  mGachaRenditionFirstGimmickRoute,
  mGachaRenditionFirstGimmickPopupRoute
} from './';

const ENTITY_STATES = [...mGachaRenditionFirstGimmickRoute, ...mGachaRenditionFirstGimmickPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MGachaRenditionFirstGimmickComponent,
    MGachaRenditionFirstGimmickDetailComponent,
    MGachaRenditionFirstGimmickUpdateComponent,
    MGachaRenditionFirstGimmickDeleteDialogComponent,
    MGachaRenditionFirstGimmickDeletePopupComponent
  ],
  entryComponents: [
    MGachaRenditionFirstGimmickComponent,
    MGachaRenditionFirstGimmickUpdateComponent,
    MGachaRenditionFirstGimmickDeleteDialogComponent,
    MGachaRenditionFirstGimmickDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMGachaRenditionFirstGimmickModule {}
