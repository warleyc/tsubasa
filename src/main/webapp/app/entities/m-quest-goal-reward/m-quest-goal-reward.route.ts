import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';
import { MQuestGoalRewardService } from './m-quest-goal-reward.service';
import { MQuestGoalRewardComponent } from './m-quest-goal-reward.component';
import { MQuestGoalRewardDetailComponent } from './m-quest-goal-reward-detail.component';
import { MQuestGoalRewardUpdateComponent } from './m-quest-goal-reward-update.component';
import { MQuestGoalRewardDeletePopupComponent } from './m-quest-goal-reward-delete-dialog.component';
import { IMQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';

@Injectable({ providedIn: 'root' })
export class MQuestGoalRewardResolve implements Resolve<IMQuestGoalReward> {
  constructor(private service: MQuestGoalRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestGoalReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestGoalReward>) => response.ok),
        map((mQuestGoalReward: HttpResponse<MQuestGoalReward>) => mQuestGoalReward.body)
      );
    }
    return of(new MQuestGoalReward());
  }
}

export const mQuestGoalRewardRoute: Routes = [
  {
    path: '',
    component: MQuestGoalRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestGoalRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestGoalRewardDetailComponent,
    resolve: {
      mQuestGoalReward: MQuestGoalRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestGoalRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestGoalRewardUpdateComponent,
    resolve: {
      mQuestGoalReward: MQuestGoalRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestGoalRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestGoalRewardUpdateComponent,
    resolve: {
      mQuestGoalReward: MQuestGoalRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestGoalRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestGoalRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestGoalRewardDeletePopupComponent,
    resolve: {
      mQuestGoalReward: MQuestGoalRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestGoalRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
