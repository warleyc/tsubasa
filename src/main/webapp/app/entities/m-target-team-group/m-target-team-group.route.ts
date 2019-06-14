import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';
import { MTargetTeamGroupService } from './m-target-team-group.service';
import { MTargetTeamGroupComponent } from './m-target-team-group.component';
import { MTargetTeamGroupDetailComponent } from './m-target-team-group-detail.component';
import { MTargetTeamGroupUpdateComponent } from './m-target-team-group-update.component';
import { MTargetTeamGroupDeletePopupComponent } from './m-target-team-group-delete-dialog.component';
import { IMTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetTeamGroupResolve implements Resolve<IMTargetTeamGroup> {
  constructor(private service: MTargetTeamGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetTeamGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetTeamGroup>) => response.ok),
        map((mTargetTeamGroup: HttpResponse<MTargetTeamGroup>) => mTargetTeamGroup.body)
      );
    }
    return of(new MTargetTeamGroup());
  }
}

export const mTargetTeamGroupRoute: Routes = [
  {
    path: '',
    component: MTargetTeamGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetTeamGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetTeamGroupDetailComponent,
    resolve: {
      mTargetTeamGroup: MTargetTeamGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTeamGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetTeamGroupUpdateComponent,
    resolve: {
      mTargetTeamGroup: MTargetTeamGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTeamGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetTeamGroupUpdateComponent,
    resolve: {
      mTargetTeamGroup: MTargetTeamGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTeamGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetTeamGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetTeamGroupDeletePopupComponent,
    resolve: {
      mTargetTeamGroup: MTargetTeamGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTeamGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
