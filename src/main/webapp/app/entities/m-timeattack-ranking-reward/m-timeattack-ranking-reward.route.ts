import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';
import { MTimeattackRankingRewardService } from './m-timeattack-ranking-reward.service';
import { MTimeattackRankingRewardComponent } from './m-timeattack-ranking-reward.component';
import { MTimeattackRankingRewardDetailComponent } from './m-timeattack-ranking-reward-detail.component';
import { MTimeattackRankingRewardUpdateComponent } from './m-timeattack-ranking-reward-update.component';
import { MTimeattackRankingRewardDeletePopupComponent } from './m-timeattack-ranking-reward-delete-dialog.component';
import { IMTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';

@Injectable({ providedIn: 'root' })
export class MTimeattackRankingRewardResolve implements Resolve<IMTimeattackRankingReward> {
  constructor(private service: MTimeattackRankingRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTimeattackRankingReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTimeattackRankingReward>) => response.ok),
        map((mTimeattackRankingReward: HttpResponse<MTimeattackRankingReward>) => mTimeattackRankingReward.body)
      );
    }
    return of(new MTimeattackRankingReward());
  }
}

export const mTimeattackRankingRewardRoute: Routes = [
  {
    path: '',
    component: MTimeattackRankingRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTimeattackRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTimeattackRankingRewardDetailComponent,
    resolve: {
      mTimeattackRankingReward: MTimeattackRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTimeattackRankingRewardUpdateComponent,
    resolve: {
      mTimeattackRankingReward: MTimeattackRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTimeattackRankingRewardUpdateComponent,
    resolve: {
      mTimeattackRankingReward: MTimeattackRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTimeattackRankingRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTimeattackRankingRewardDeletePopupComponent,
    resolve: {
      mTimeattackRankingReward: MTimeattackRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
