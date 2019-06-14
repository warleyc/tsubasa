import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';
import { MTicketQuestStageRewardService } from './m-ticket-quest-stage-reward.service';
import { MTicketQuestStageRewardComponent } from './m-ticket-quest-stage-reward.component';
import { MTicketQuestStageRewardDetailComponent } from './m-ticket-quest-stage-reward-detail.component';
import { MTicketQuestStageRewardUpdateComponent } from './m-ticket-quest-stage-reward-update.component';
import { MTicketQuestStageRewardDeletePopupComponent } from './m-ticket-quest-stage-reward-delete-dialog.component';
import { IMTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MTicketQuestStageRewardResolve implements Resolve<IMTicketQuestStageReward> {
  constructor(private service: MTicketQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTicketQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTicketQuestStageReward>) => response.ok),
        map((mTicketQuestStageReward: HttpResponse<MTicketQuestStageReward>) => mTicketQuestStageReward.body)
      );
    }
    return of(new MTicketQuestStageReward());
  }
}

export const mTicketQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MTicketQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTicketQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTicketQuestStageRewardDetailComponent,
    resolve: {
      mTicketQuestStageReward: MTicketQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTicketQuestStageRewardUpdateComponent,
    resolve: {
      mTicketQuestStageReward: MTicketQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTicketQuestStageRewardUpdateComponent,
    resolve: {
      mTicketQuestStageReward: MTicketQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTicketQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTicketQuestStageRewardDeletePopupComponent,
    resolve: {
      mTicketQuestStageReward: MTicketQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
