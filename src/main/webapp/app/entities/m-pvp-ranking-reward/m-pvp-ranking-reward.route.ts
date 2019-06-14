import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';
import { MPvpRankingRewardService } from './m-pvp-ranking-reward.service';
import { MPvpRankingRewardComponent } from './m-pvp-ranking-reward.component';
import { MPvpRankingRewardDetailComponent } from './m-pvp-ranking-reward-detail.component';
import { MPvpRankingRewardUpdateComponent } from './m-pvp-ranking-reward-update.component';
import { MPvpRankingRewardDeletePopupComponent } from './m-pvp-ranking-reward-delete-dialog.component';
import { IMPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';

@Injectable({ providedIn: 'root' })
export class MPvpRankingRewardResolve implements Resolve<IMPvpRankingReward> {
  constructor(private service: MPvpRankingRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpRankingReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpRankingReward>) => response.ok),
        map((mPvpRankingReward: HttpResponse<MPvpRankingReward>) => mPvpRankingReward.body)
      );
    }
    return of(new MPvpRankingReward());
  }
}

export const mPvpRankingRewardRoute: Routes = [
  {
    path: '',
    component: MPvpRankingRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpRankingRewardDetailComponent,
    resolve: {
      mPvpRankingReward: MPvpRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpRankingRewardUpdateComponent,
    resolve: {
      mPvpRankingReward: MPvpRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpRankingRewardUpdateComponent,
    resolve: {
      mPvpRankingReward: MPvpRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpRankingRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpRankingRewardDeletePopupComponent,
    resolve: {
      mPvpRankingReward: MPvpRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
