import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TsubasaSharedModule } from 'app/shared';
import {
  MQuestDropRateCampaignContentComponent,
  MQuestDropRateCampaignContentDetailComponent,
  MQuestDropRateCampaignContentUpdateComponent,
  MQuestDropRateCampaignContentDeletePopupComponent,
  MQuestDropRateCampaignContentDeleteDialogComponent,
  mQuestDropRateCampaignContentRoute,
  mQuestDropRateCampaignContentPopupRoute
} from './';

const ENTITY_STATES = [...mQuestDropRateCampaignContentRoute, ...mQuestDropRateCampaignContentPopupRoute];

@NgModule({
  imports: [TsubasaSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MQuestDropRateCampaignContentComponent,
    MQuestDropRateCampaignContentDetailComponent,
    MQuestDropRateCampaignContentUpdateComponent,
    MQuestDropRateCampaignContentDeleteDialogComponent,
    MQuestDropRateCampaignContentDeletePopupComponent
  ],
  entryComponents: [
    MQuestDropRateCampaignContentComponent,
    MQuestDropRateCampaignContentUpdateComponent,
    MQuestDropRateCampaignContentDeleteDialogComponent,
    MQuestDropRateCampaignContentDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TsubasaMQuestDropRateCampaignContentModule {}
