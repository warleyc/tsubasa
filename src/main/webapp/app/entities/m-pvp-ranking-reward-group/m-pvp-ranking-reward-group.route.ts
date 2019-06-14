import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';
import { MPvpRankingRewardGroupService } from './m-pvp-ranking-reward-group.service';
import { MPvpRankingRewardGroupComponent } from './m-pvp-ranking-reward-group.component';
import { MPvpRankingRewardGroupDetailComponent } from './m-pvp-ranking-reward-group-detail.component';
import { MPvpRankingRewardGroupUpdateComponent } from './m-pvp-ranking-reward-group-update.component';
import { MPvpRankingRewardGroupDeletePopupComponent } from './m-pvp-ranking-reward-group-delete-dialog.component';
import { IMPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MPvpRankingRewardGroupResolve implements Resolve<IMPvpRankingRewardGroup> {
  constructor(private service: MPvpRankingRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpRankingRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpRankingRewardGroup>) => response.ok),
        map((mPvpRankingRewardGroup: HttpResponse<MPvpRankingRewardGroup>) => mPvpRankingRewardGroup.body)
      );
    }
    return of(new MPvpRankingRewardGroup());
  }
}

export const mPvpRankingRewardGroupRoute: Routes = [
  {
    path: '',
    component: MPvpRankingRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpRankingRewardGroupDetailComponent,
    resolve: {
      mPvpRankingRewardGroup: MPvpRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpRankingRewardGroupUpdateComponent,
    resolve: {
      mPvpRankingRewardGroup: MPvpRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpRankingRewardGroupUpdateComponent,
    resolve: {
      mPvpRankingRewardGroup: MPvpRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpRankingRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpRankingRewardGroupDeletePopupComponent,
    resolve: {
      mPvpRankingRewardGroup: MPvpRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
