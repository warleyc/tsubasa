import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';
import { MMarathonAchievementRewardGroupService } from './m-marathon-achievement-reward-group.service';
import { MMarathonAchievementRewardGroupComponent } from './m-marathon-achievement-reward-group.component';
import { MMarathonAchievementRewardGroupDetailComponent } from './m-marathon-achievement-reward-group-detail.component';
import { MMarathonAchievementRewardGroupUpdateComponent } from './m-marathon-achievement-reward-group-update.component';
import { MMarathonAchievementRewardGroupDeletePopupComponent } from './m-marathon-achievement-reward-group-delete-dialog.component';
import { IMMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MMarathonAchievementRewardGroupResolve implements Resolve<IMMarathonAchievementRewardGroup> {
  constructor(private service: MMarathonAchievementRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonAchievementRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonAchievementRewardGroup>) => response.ok),
        map((mMarathonAchievementRewardGroup: HttpResponse<MMarathonAchievementRewardGroup>) => mMarathonAchievementRewardGroup.body)
      );
    }
    return of(new MMarathonAchievementRewardGroup());
  }
}

export const mMarathonAchievementRewardGroupRoute: Routes = [
  {
    path: '',
    component: MMarathonAchievementRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonAchievementRewardGroupDetailComponent,
    resolve: {
      mMarathonAchievementRewardGroup: MMarathonAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonAchievementRewardGroupUpdateComponent,
    resolve: {
      mMarathonAchievementRewardGroup: MMarathonAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonAchievementRewardGroupUpdateComponent,
    resolve: {
      mMarathonAchievementRewardGroup: MMarathonAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonAchievementRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonAchievementRewardGroupDeletePopupComponent,
    resolve: {
      mMarathonAchievementRewardGroup: MMarathonAchievementRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonAchievementRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
