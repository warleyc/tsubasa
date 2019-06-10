import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';
import { MChallengeQuestStageRewardService } from './m-challenge-quest-stage-reward.service';
import { MChallengeQuestStageRewardComponent } from './m-challenge-quest-stage-reward.component';
import { MChallengeQuestStageRewardDetailComponent } from './m-challenge-quest-stage-reward-detail.component';
import { MChallengeQuestStageRewardUpdateComponent } from './m-challenge-quest-stage-reward-update.component';
import { MChallengeQuestStageRewardDeletePopupComponent } from './m-challenge-quest-stage-reward-delete-dialog.component';
import { IMChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MChallengeQuestStageRewardResolve implements Resolve<IMChallengeQuestStageReward> {
  constructor(private service: MChallengeQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMChallengeQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MChallengeQuestStageReward>) => response.ok),
        map((mChallengeQuestStageReward: HttpResponse<MChallengeQuestStageReward>) => mChallengeQuestStageReward.body)
      );
    }
    return of(new MChallengeQuestStageReward());
  }
}

export const mChallengeQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MChallengeQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MChallengeQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MChallengeQuestStageRewardDetailComponent,
    resolve: {
      mChallengeQuestStageReward: MChallengeQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MChallengeQuestStageRewardUpdateComponent,
    resolve: {
      mChallengeQuestStageReward: MChallengeQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MChallengeQuestStageRewardUpdateComponent,
    resolve: {
      mChallengeQuestStageReward: MChallengeQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mChallengeQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MChallengeQuestStageRewardDeletePopupComponent,
    resolve: {
      mChallengeQuestStageReward: MChallengeQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
