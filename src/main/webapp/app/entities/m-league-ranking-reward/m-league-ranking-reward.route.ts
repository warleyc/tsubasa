import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';
import { MLeagueRankingRewardService } from './m-league-ranking-reward.service';
import { MLeagueRankingRewardComponent } from './m-league-ranking-reward.component';
import { MLeagueRankingRewardDetailComponent } from './m-league-ranking-reward-detail.component';
import { MLeagueRankingRewardUpdateComponent } from './m-league-ranking-reward-update.component';
import { MLeagueRankingRewardDeletePopupComponent } from './m-league-ranking-reward-delete-dialog.component';
import { IMLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';

@Injectable({ providedIn: 'root' })
export class MLeagueRankingRewardResolve implements Resolve<IMLeagueRankingReward> {
  constructor(private service: MLeagueRankingRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLeagueRankingReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLeagueRankingReward>) => response.ok),
        map((mLeagueRankingReward: HttpResponse<MLeagueRankingReward>) => mLeagueRankingReward.body)
      );
    }
    return of(new MLeagueRankingReward());
  }
}

export const mLeagueRankingRewardRoute: Routes = [
  {
    path: '',
    component: MLeagueRankingRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLeagueRankingRewardDetailComponent,
    resolve: {
      mLeagueRankingReward: MLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLeagueRankingRewardUpdateComponent,
    resolve: {
      mLeagueRankingReward: MLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLeagueRankingRewardUpdateComponent,
    resolve: {
      mLeagueRankingReward: MLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLeagueRankingRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLeagueRankingRewardDeletePopupComponent,
    resolve: {
      mLeagueRankingReward: MLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
