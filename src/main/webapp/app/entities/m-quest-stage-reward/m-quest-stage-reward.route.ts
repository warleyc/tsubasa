import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';
import { MQuestStageRewardService } from './m-quest-stage-reward.service';
import { MQuestStageRewardComponent } from './m-quest-stage-reward.component';
import { MQuestStageRewardDetailComponent } from './m-quest-stage-reward-detail.component';
import { MQuestStageRewardUpdateComponent } from './m-quest-stage-reward-update.component';
import { MQuestStageRewardDeletePopupComponent } from './m-quest-stage-reward-delete-dialog.component';
import { IMQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MQuestStageRewardResolve implements Resolve<IMQuestStageReward> {
  constructor(private service: MQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestStageReward>) => response.ok),
        map((mQuestStageReward: HttpResponse<MQuestStageReward>) => mQuestStageReward.body)
      );
    }
    return of(new MQuestStageReward());
  }
}

export const mQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestStageRewardDetailComponent,
    resolve: {
      mQuestStageReward: MQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestStageRewardUpdateComponent,
    resolve: {
      mQuestStageReward: MQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestStageRewardUpdateComponent,
    resolve: {
      mQuestStageReward: MQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestStageRewardDeletePopupComponent,
    resolve: {
      mQuestStageReward: MQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
