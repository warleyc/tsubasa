import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';
import { MLuckWeeklyQuestStageRewardService } from './m-luck-weekly-quest-stage-reward.service';
import { MLuckWeeklyQuestStageRewardComponent } from './m-luck-weekly-quest-stage-reward.component';
import { MLuckWeeklyQuestStageRewardDetailComponent } from './m-luck-weekly-quest-stage-reward-detail.component';
import { MLuckWeeklyQuestStageRewardUpdateComponent } from './m-luck-weekly-quest-stage-reward-update.component';
import { MLuckWeeklyQuestStageRewardDeletePopupComponent } from './m-luck-weekly-quest-stage-reward-delete-dialog.component';
import { IMLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MLuckWeeklyQuestStageRewardResolve implements Resolve<IMLuckWeeklyQuestStageReward> {
  constructor(private service: MLuckWeeklyQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLuckWeeklyQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLuckWeeklyQuestStageReward>) => response.ok),
        map((mLuckWeeklyQuestStageReward: HttpResponse<MLuckWeeklyQuestStageReward>) => mLuckWeeklyQuestStageReward.body)
      );
    }
    return of(new MLuckWeeklyQuestStageReward());
  }
}

export const mLuckWeeklyQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MLuckWeeklyQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLuckWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLuckWeeklyQuestStageRewardDetailComponent,
    resolve: {
      mLuckWeeklyQuestStageReward: MLuckWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLuckWeeklyQuestStageRewardUpdateComponent,
    resolve: {
      mLuckWeeklyQuestStageReward: MLuckWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLuckWeeklyQuestStageRewardUpdateComponent,
    resolve: {
      mLuckWeeklyQuestStageReward: MLuckWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLuckWeeklyQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLuckWeeklyQuestStageRewardDeletePopupComponent,
    resolve: {
      mLuckWeeklyQuestStageReward: MLuckWeeklyQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckWeeklyQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
