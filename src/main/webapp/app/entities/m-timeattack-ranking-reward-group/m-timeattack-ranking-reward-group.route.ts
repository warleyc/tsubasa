import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';
import { MTimeattackRankingRewardGroupService } from './m-timeattack-ranking-reward-group.service';
import { MTimeattackRankingRewardGroupComponent } from './m-timeattack-ranking-reward-group.component';
import { MTimeattackRankingRewardGroupDetailComponent } from './m-timeattack-ranking-reward-group-detail.component';
import { MTimeattackRankingRewardGroupUpdateComponent } from './m-timeattack-ranking-reward-group-update.component';
import { MTimeattackRankingRewardGroupDeletePopupComponent } from './m-timeattack-ranking-reward-group-delete-dialog.component';
import { IMTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MTimeattackRankingRewardGroupResolve implements Resolve<IMTimeattackRankingRewardGroup> {
  constructor(private service: MTimeattackRankingRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTimeattackRankingRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTimeattackRankingRewardGroup>) => response.ok),
        map((mTimeattackRankingRewardGroup: HttpResponse<MTimeattackRankingRewardGroup>) => mTimeattackRankingRewardGroup.body)
      );
    }
    return of(new MTimeattackRankingRewardGroup());
  }
}

export const mTimeattackRankingRewardGroupRoute: Routes = [
  {
    path: '',
    component: MTimeattackRankingRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTimeattackRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTimeattackRankingRewardGroupDetailComponent,
    resolve: {
      mTimeattackRankingRewardGroup: MTimeattackRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTimeattackRankingRewardGroupUpdateComponent,
    resolve: {
      mTimeattackRankingRewardGroup: MTimeattackRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTimeattackRankingRewardGroupUpdateComponent,
    resolve: {
      mTimeattackRankingRewardGroup: MTimeattackRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTimeattackRankingRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTimeattackRankingRewardGroupDeletePopupComponent,
    resolve: {
      mTimeattackRankingRewardGroup: MTimeattackRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
