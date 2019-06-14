import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';
import { MTargetActionTypeGroupService } from './m-target-action-type-group.service';
import { MTargetActionTypeGroupComponent } from './m-target-action-type-group.component';
import { MTargetActionTypeGroupDetailComponent } from './m-target-action-type-group-detail.component';
import { MTargetActionTypeGroupUpdateComponent } from './m-target-action-type-group-update.component';
import { MTargetActionTypeGroupDeletePopupComponent } from './m-target-action-type-group-delete-dialog.component';
import { IMTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetActionTypeGroupResolve implements Resolve<IMTargetActionTypeGroup> {
  constructor(private service: MTargetActionTypeGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetActionTypeGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetActionTypeGroup>) => response.ok),
        map((mTargetActionTypeGroup: HttpResponse<MTargetActionTypeGroup>) => mTargetActionTypeGroup.body)
      );
    }
    return of(new MTargetActionTypeGroup());
  }
}

export const mTargetActionTypeGroupRoute: Routes = [
  {
    path: '',
    component: MTargetActionTypeGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetActionTypeGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetActionTypeGroupDetailComponent,
    resolve: {
      mTargetActionTypeGroup: MTargetActionTypeGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionTypeGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetActionTypeGroupUpdateComponent,
    resolve: {
      mTargetActionTypeGroup: MTargetActionTypeGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionTypeGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetActionTypeGroupUpdateComponent,
    resolve: {
      mTargetActionTypeGroup: MTargetActionTypeGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionTypeGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetActionTypeGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetActionTypeGroupDeletePopupComponent,
    resolve: {
      mTargetActionTypeGroup: MTargetActionTypeGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionTypeGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
