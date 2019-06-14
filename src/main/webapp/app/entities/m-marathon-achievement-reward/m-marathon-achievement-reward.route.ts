import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';
import { MMarathonAchievementRewardService } from './m-marathon-achievement-reward.service';
import { MMarathonAchievementRewardComponent } from './m-marathon-achievement-reward.component';
import { MMarathonAchievementRewardDetailComponent } from './m-marathon-achievement-reward-detail.component';
import { MMarathonAchievementRewardUpdateComponent } from './m-marathon-achievement-reward-update.component';
import { MMarathonAchievementRewardDeletePopupComponent } from './m-marathon-achievement-reward-delete-dialog.component';
import { IMMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';

@Injectable({ providedIn: 'root' })
export class MMarathonAchievementRewardResolve implements Resolve<IMMarathonAchievementReward> {
  constructor(private service: MMarathonAchievementRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonAchievementReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonAchievementReward>) => response.ok),
        map((mMarathonAchievementReward: HttpResponse<MMarathonAchievementReward>) => mMarathonAchievementReward.body)
      );
    }
    return of(new MMarathonAchievementReward());
  }
}

export const mMarathonAchievementRewardRoute: Routes = [
  {
    path: '',
    component: MMarathonAchievementRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonAchievementRewardDetailComponent,
    resolve: {
      mMarathonAchievementReward: MMarathonAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonAchievementRewardUpdateComponent,
    resolve: {
      mMarathonAchievementReward: MMarathonAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonAchievementRewardUpdateComponent,
    resolve: {
      mMarathonAchievementReward: MMarathonAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonAchievementRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonAchievementRewardDeletePopupComponent,
    resolve: {
      mMarathonAchievementReward: MMarathonAchievementRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
