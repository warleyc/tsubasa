import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonRankingReward } from 'app/shared/model/m-marathon-ranking-reward.model';
import { MMarathonRankingRewardService } from './m-marathon-ranking-reward.service';
import { MMarathonRankingRewardComponent } from './m-marathon-ranking-reward.component';
import { MMarathonRankingRewardDetailComponent } from './m-marathon-ranking-reward-detail.component';
import { MMarathonRankingRewardUpdateComponent } from './m-marathon-ranking-reward-update.component';
import { MMarathonRankingRewardDeletePopupComponent } from './m-marathon-ranking-reward-delete-dialog.component';
import { IMMarathonRankingReward } from 'app/shared/model/m-marathon-ranking-reward.model';

@Injectable({ providedIn: 'root' })
export class MMarathonRankingRewardResolve implements Resolve<IMMarathonRankingReward> {
  constructor(private service: MMarathonRankingRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonRankingReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonRankingReward>) => response.ok),
        map((mMarathonRankingReward: HttpResponse<MMarathonRankingReward>) => mMarathonRankingReward.body)
      );
    }
    return of(new MMarathonRankingReward());
  }
}

export const mMarathonRankingRewardRoute: Routes = [
  {
    path: '',
    component: MMarathonRankingRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonRankingRewardDetailComponent,
    resolve: {
      mMarathonRankingReward: MMarathonRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonRankingRewardUpdateComponent,
    resolve: {
      mMarathonRankingReward: MMarathonRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonRankingRewardUpdateComponent,
    resolve: {
      mMarathonRankingReward: MMarathonRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonRankingRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonRankingRewardDeletePopupComponent,
    resolve: {
      mMarathonRankingReward: MMarathonRankingRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonRankingRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
