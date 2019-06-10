import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';
import { MChallengeQuestAchievementRewardGroupService } from './m-challenge-quest-achievement-reward-group.service';
import { MChallengeQuestAchievementRewardGroupComponent } from './m-challenge-quest-achievement-reward-group.component';
import { MChallengeQuestAchievementRewardGroupDetailComponent } from './m-challenge-quest-achievement-reward-group-detail.component';
import { MChallengeQuestAchievementRewardGroupUpdateComponent } from './m-challenge-quest-achievement-reward-group-update.component';
import { MChallengeQuestAchievementRewardGroupDeletePopupComponent } from './m-challenge-quest-achievement-reward-group-delete-dialog.component';
import { IMChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MChallengeQuestAchievementRewardGroupResolve implements Resolve<IMChallengeQuestAchievementRewardGroup> {
  constructor(private service: MChallengeQuestAchievementRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMChallengeQuestAchievementRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MChallengeQuestAchievementRewardGroup>) => response.ok),
        map(
          (mChallengeQuestAchievementRewardGroup: HttpResponse<MChallengeQuestAchievementRewardGroup>) =>
            mChallengeQuestAchievementRewardGroup.body
        )
      );
    }
    return of(new MChallengeQuestAchievementRewardGroup());
  }
}

export const mChallengeQuestAchievementRewardGroupRoute: Routes = [
  {
    path: '',
    component: MChallengeQuestAchievementRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MChallengeQuestAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MChallengeQuestAchievementRewardGroupDetailComponent,
    resolve: {
      mChallengeQuestAchievementRewardGroup: MChallengeQuestAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MChallengeQuestAchievementRewardGroupUpdateComponent,
    resolve: {
      mChallengeQuestAchievementRewardGroup: MChallengeQuestAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MChallengeQuestAchievementRewardGroupUpdateComponent,
    resolve: {
      mChallengeQuestAchievementRewardGroup: MChallengeQuestAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mChallengeQuestAchievementRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MChallengeQuestAchievementRewardGroupDeletePopupComponent,
    resolve: {
      mChallengeQuestAchievementRewardGroup: MChallengeQuestAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
