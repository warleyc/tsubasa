import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';
import { MWeeklyQuestStageRewardService } from './m-weekly-quest-stage-reward.service';
import { MWeeklyQuestStageRewardComponent } from './m-weekly-quest-stage-reward.component';
import { MWeeklyQuestStageRewardDetailComponent } from './m-weekly-quest-stage-reward-detail.component';
import { MWeeklyQuestStageRewardUpdateComponent } from './m-weekly-quest-stage-reward-update.component';
import { MWeeklyQuestStageRewardDeletePopupComponent } from './m-weekly-quest-stage-reward-delete-dialog.component';
import { IMWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestStageRewardResolve implements Resolve<IMWeeklyQuestStageReward> {
  constructor(private service: MWeeklyQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMWeeklyQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MWeeklyQuestStageReward>) => response.ok),
        map((mWeeklyQuestStageReward: HttpResponse<MWeeklyQuestStageReward>) => mWeeklyQuestStageReward.body)
      );
    }
    return of(new MWeeklyQuestStageReward());
  }
}

export const mWeeklyQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MWeeklyQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MWeeklyQuestStageRewardDetailComponent,
    resolve: {
      mWeeklyQuestStageReward: MWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MWeeklyQuestStageRewardUpdateComponent,
    resolve: {
      mWeeklyQuestStageReward: MWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MWeeklyQuestStageRewardUpdateComponent,
    resolve: {
      mWeeklyQuestStageReward: MWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mWeeklyQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MWeeklyQuestStageRewardDeletePopupComponent,
    resolve: {
      mWeeklyQuestStageReward: MWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
