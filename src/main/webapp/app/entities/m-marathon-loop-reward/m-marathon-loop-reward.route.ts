import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonLoopReward } from 'app/shared/model/m-marathon-loop-reward.model';
import { MMarathonLoopRewardService } from './m-marathon-loop-reward.service';
import { MMarathonLoopRewardComponent } from './m-marathon-loop-reward.component';
import { MMarathonLoopRewardDetailComponent } from './m-marathon-loop-reward-detail.component';
import { MMarathonLoopRewardUpdateComponent } from './m-marathon-loop-reward-update.component';
import { MMarathonLoopRewardDeletePopupComponent } from './m-marathon-loop-reward-delete-dialog.component';
import { IMMarathonLoopReward } from 'app/shared/model/m-marathon-loop-reward.model';

@Injectable({ providedIn: 'root' })
export class MMarathonLoopRewardResolve implements Resolve<IMMarathonLoopReward> {
  constructor(private service: MMarathonLoopRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonLoopReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonLoopReward>) => response.ok),
        map((mMarathonLoopReward: HttpResponse<MMarathonLoopReward>) => mMarathonLoopReward.body)
      );
    }
    return of(new MMarathonLoopReward());
  }
}

export const mMarathonLoopRewardRoute: Routes = [
  {
    path: '',
    component: MMarathonLoopRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonLoopRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonLoopRewardDetailComponent,
    resolve: {
      mMarathonLoopReward: MMarathonLoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonLoopRewardUpdateComponent,
    resolve: {
      mMarathonLoopReward: MMarathonLoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonLoopRewardUpdateComponent,
    resolve: {
      mMarathonLoopReward: MMarathonLoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonLoopRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonLoopRewardDeletePopupComponent,
    resolve: {
      mMarathonLoopReward: MMarathonLoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
