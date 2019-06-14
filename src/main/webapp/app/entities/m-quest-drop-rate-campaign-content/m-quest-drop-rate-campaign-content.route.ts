import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';
import { MQuestDropRateCampaignContentService } from './m-quest-drop-rate-campaign-content.service';
import { MQuestDropRateCampaignContentComponent } from './m-quest-drop-rate-campaign-content.component';
import { MQuestDropRateCampaignContentDetailComponent } from './m-quest-drop-rate-campaign-content-detail.component';
import { MQuestDropRateCampaignContentUpdateComponent } from './m-quest-drop-rate-campaign-content-update.component';
import { MQuestDropRateCampaignContentDeletePopupComponent } from './m-quest-drop-rate-campaign-content-delete-dialog.component';
import { IMQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';

@Injectable({ providedIn: 'root' })
export class MQuestDropRateCampaignContentResolve implements Resolve<IMQuestDropRateCampaignContent> {
  constructor(private service: MQuestDropRateCampaignContentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestDropRateCampaignContent> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestDropRateCampaignContent>) => response.ok),
        map((mQuestDropRateCampaignContent: HttpResponse<MQuestDropRateCampaignContent>) => mQuestDropRateCampaignContent.body)
      );
    }
    return of(new MQuestDropRateCampaignContent());
  }
}

export const mQuestDropRateCampaignContentRoute: Routes = [
  {
    path: '',
    component: MQuestDropRateCampaignContentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestDropRateCampaignContents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestDropRateCampaignContentDetailComponent,
    resolve: {
      mQuestDropRateCampaignContent: MQuestDropRateCampaignContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestDropRateCampaignContents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestDropRateCampaignContentUpdateComponent,
    resolve: {
      mQuestDropRateCampaignContent: MQuestDropRateCampaignContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestDropRateCampaignContents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestDropRateCampaignContentUpdateComponent,
    resolve: {
      mQuestDropRateCampaignContent: MQuestDropRateCampaignContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestDropRateCampaignContents'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestDropRateCampaignContentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestDropRateCampaignContentDeletePopupComponent,
    resolve: {
      mQuestDropRateCampaignContent: MQuestDropRateCampaignContentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestDropRateCampaignContents'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
