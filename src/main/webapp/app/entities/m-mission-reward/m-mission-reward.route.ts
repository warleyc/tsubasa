import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMissionReward } from 'app/shared/model/m-mission-reward.model';
import { MMissionRewardService } from './m-mission-reward.service';
import { MMissionRewardComponent } from './m-mission-reward.component';
import { MMissionRewardDetailComponent } from './m-mission-reward-detail.component';
import { MMissionRewardUpdateComponent } from './m-mission-reward-update.component';
import { MMissionRewardDeletePopupComponent } from './m-mission-reward-delete-dialog.component';
import { IMMissionReward } from 'app/shared/model/m-mission-reward.model';

@Injectable({ providedIn: 'root' })
export class MMissionRewardResolve implements Resolve<IMMissionReward> {
  constructor(private service: MMissionRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMissionReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMissionReward>) => response.ok),
        map((mMissionReward: HttpResponse<MMissionReward>) => mMissionReward.body)
      );
    }
    return of(new MMissionReward());
  }
}

export const mMissionRewardRoute: Routes = [
  {
    path: '',
    component: MMissionRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMissionRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMissionRewardDetailComponent,
    resolve: {
      mMissionReward: MMissionRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissionRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMissionRewardUpdateComponent,
    resolve: {
      mMissionReward: MMissionRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissionRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMissionRewardUpdateComponent,
    resolve: {
      mMissionReward: MMissionRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissionRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMissionRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMissionRewardDeletePopupComponent,
    resolve: {
      mMissionReward: MMissionRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissionRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
