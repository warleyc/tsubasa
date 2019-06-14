import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';
import { MMarathonRankingRewardGroupService } from './m-marathon-ranking-reward-group.service';
import { MMarathonRankingRewardGroupComponent } from './m-marathon-ranking-reward-group.component';
import { MMarathonRankingRewardGroupDetailComponent } from './m-marathon-ranking-reward-group-detail.component';
import { MMarathonRankingRewardGroupUpdateComponent } from './m-marathon-ranking-reward-group-update.component';
import { MMarathonRankingRewardGroupDeletePopupComponent } from './m-marathon-ranking-reward-group-delete-dialog.component';
import { IMMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MMarathonRankingRewardGroupResolve implements Resolve<IMMarathonRankingRewardGroup> {
  constructor(private service: MMarathonRankingRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonRankingRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonRankingRewardGroup>) => response.ok),
        map((mMarathonRankingRewardGroup: HttpResponse<MMarathonRankingRewardGroup>) => mMarathonRankingRewardGroup.body)
      );
    }
    return of(new MMarathonRankingRewardGroup());
  }
}

export const mMarathonRankingRewardGroupRoute: Routes = [
  {
    path: '',
    component: MMarathonRankingRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonRankingRewardGroupDetailComponent,
    resolve: {
      mMarathonRankingRewardGroup: MMarathonRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonRankingRewardGroupUpdateComponent,
    resolve: {
      mMarathonRankingRewardGroup: MMarathonRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonRankingRewardGroupUpdateComponent,
    resolve: {
      mMarathonRankingRewardGroup: MMarathonRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonRankingRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonRankingRewardGroupDeletePopupComponent,
    resolve: {
      mMarathonRankingRewardGroup: MMarathonRankingRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
