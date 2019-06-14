import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';
import { MMarathonLoopRewardGroupService } from './m-marathon-loop-reward-group.service';
import { MMarathonLoopRewardGroupComponent } from './m-marathon-loop-reward-group.component';
import { MMarathonLoopRewardGroupDetailComponent } from './m-marathon-loop-reward-group-detail.component';
import { MMarathonLoopRewardGroupUpdateComponent } from './m-marathon-loop-reward-group-update.component';
import { MMarathonLoopRewardGroupDeletePopupComponent } from './m-marathon-loop-reward-group-delete-dialog.component';
import { IMMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MMarathonLoopRewardGroupResolve implements Resolve<IMMarathonLoopRewardGroup> {
  constructor(private service: MMarathonLoopRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonLoopRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonLoopRewardGroup>) => response.ok),
        map((mMarathonLoopRewardGroup: HttpResponse<MMarathonLoopRewardGroup>) => mMarathonLoopRewardGroup.body)
      );
    }
    return of(new MMarathonLoopRewardGroup());
  }
}

export const mMarathonLoopRewardGroupRoute: Routes = [
  {
    path: '',
    component: MMarathonLoopRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonLoopRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonLoopRewardGroupDetailComponent,
    resolve: {
      mMarathonLoopRewardGroup: MMarathonLoopRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonLoopRewardGroupUpdateComponent,
    resolve: {
      mMarathonLoopRewardGroup: MMarathonLoopRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonLoopRewardGroupUpdateComponent,
    resolve: {
      mMarathonLoopRewardGroup: MMarathonLoopRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonLoopRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonLoopRewardGroupDeletePopupComponent,
    resolve: {
      mMarathonLoopRewardGroup: MMarathonLoopRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonLoopRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
