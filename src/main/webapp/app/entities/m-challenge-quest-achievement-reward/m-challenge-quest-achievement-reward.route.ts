import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';
import { MChallengeQuestAchievementRewardService } from './m-challenge-quest-achievement-reward.service';
import { MChallengeQuestAchievementRewardComponent } from './m-challenge-quest-achievement-reward.component';
import { MChallengeQuestAchievementRewardDetailComponent } from './m-challenge-quest-achievement-reward-detail.component';
import { MChallengeQuestAchievementRewardUpdateComponent } from './m-challenge-quest-achievement-reward-update.component';
import { MChallengeQuestAchievementRewardDeletePopupComponent } from './m-challenge-quest-achievement-reward-delete-dialog.component';
import { IMChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';

@Injectable({ providedIn: 'root' })
export class MChallengeQuestAchievementRewardResolve implements Resolve<IMChallengeQuestAchievementReward> {
  constructor(private service: MChallengeQuestAchievementRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMChallengeQuestAchievementReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MChallengeQuestAchievementReward>) => response.ok),
        map((mChallengeQuestAchievementReward: HttpResponse<MChallengeQuestAchievementReward>) => mChallengeQuestAchievementReward.body)
      );
    }
    return of(new MChallengeQuestAchievementReward());
  }
}

export const mChallengeQuestAchievementRewardRoute: Routes = [
  {
    path: '',
    component: MChallengeQuestAchievementRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MChallengeQuestAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MChallengeQuestAchievementRewardDetailComponent,
    resolve: {
      mChallengeQuestAchievementReward: MChallengeQuestAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MChallengeQuestAchievementRewardUpdateComponent,
    resolve: {
      mChallengeQuestAchievementReward: MChallengeQuestAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MChallengeQuestAchievementRewardUpdateComponent,
    resolve: {
      mChallengeQuestAchievementReward: MChallengeQuestAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mChallengeQuestAchievementRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MChallengeQuestAchievementRewardDeletePopupComponent,
    resolve: {
      mChallengeQuestAchievementReward: MChallengeQuestAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
