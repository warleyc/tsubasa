import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';
import { MLeagueRankingRewardGroupService } from './m-league-ranking-reward-group.service';
import { MLeagueRankingRewardGroupComponent } from './m-league-ranking-reward-group.component';
import { MLeagueRankingRewardGroupDetailComponent } from './m-league-ranking-reward-group-detail.component';
import { MLeagueRankingRewardGroupUpdateComponent } from './m-league-ranking-reward-group-update.component';
import { MLeagueRankingRewardGroupDeletePopupComponent } from './m-league-ranking-reward-group-delete-dialog.component';
import { IMLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MLeagueRankingRewardGroupResolve implements Resolve<IMLeagueRankingRewardGroup> {
  constructor(private service: MLeagueRankingRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLeagueRankingRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLeagueRankingRewardGroup>) => response.ok),
        map((mLeagueRankingRewardGroup: HttpResponse<MLeagueRankingRewardGroup>) => mLeagueRankingRewardGroup.body)
      );
    }
    return of(new MLeagueRankingRewardGroup());
  }
}

export const mLeagueRankingRewardGroupRoute: Routes = [
  {
    path: '',
    component: MLeagueRankingRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLeagueRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLeagueRankingRewardGroupDetailComponent,
    resolve: {
      mLeagueRankingRewardGroup: MLeagueRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLeagueRankingRewardGroupUpdateComponent,
    resolve: {
      mLeagueRankingRewardGroup: MLeagueRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLeagueRankingRewardGroupUpdateComponent,
    resolve: {
      mLeagueRankingRewardGroup: MLeagueRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLeagueRankingRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLeagueRankingRewardGroupDeletePopupComponent,
    resolve: {
      mLeagueRankingRewardGroup: MLeagueRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
