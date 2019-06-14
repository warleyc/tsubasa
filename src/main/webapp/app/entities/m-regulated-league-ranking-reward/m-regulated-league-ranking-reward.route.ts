import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';
import { MRegulatedLeagueRankingRewardService } from './m-regulated-league-ranking-reward.service';
import { MRegulatedLeagueRankingRewardComponent } from './m-regulated-league-ranking-reward.component';
import { MRegulatedLeagueRankingRewardDetailComponent } from './m-regulated-league-ranking-reward-detail.component';
import { MRegulatedLeagueRankingRewardUpdateComponent } from './m-regulated-league-ranking-reward-update.component';
import { MRegulatedLeagueRankingRewardDeletePopupComponent } from './m-regulated-league-ranking-reward-delete-dialog.component';
import { IMRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';

@Injectable({ providedIn: 'root' })
export class MRegulatedLeagueRankingRewardResolve implements Resolve<IMRegulatedLeagueRankingReward> {
  constructor(private service: MRegulatedLeagueRankingRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMRegulatedLeagueRankingReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MRegulatedLeagueRankingReward>) => response.ok),
        map((mRegulatedLeagueRankingReward: HttpResponse<MRegulatedLeagueRankingReward>) => mRegulatedLeagueRankingReward.body)
      );
    }
    return of(new MRegulatedLeagueRankingReward());
  }
}

export const mRegulatedLeagueRankingRewardRoute: Routes = [
  {
    path: '',
    component: MRegulatedLeagueRankingRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MRegulatedLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MRegulatedLeagueRankingRewardDetailComponent,
    resolve: {
      mRegulatedLeagueRankingReward: MRegulatedLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulatedLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MRegulatedLeagueRankingRewardUpdateComponent,
    resolve: {
      mRegulatedLeagueRankingReward: MRegulatedLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulatedLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MRegulatedLeagueRankingRewardUpdateComponent,
    resolve: {
      mRegulatedLeagueRankingReward: MRegulatedLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulatedLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mRegulatedLeagueRankingRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MRegulatedLeagueRankingRewardDeletePopupComponent,
    resolve: {
      mRegulatedLeagueRankingReward: MRegulatedLeagueRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulatedLeagueRankingRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
