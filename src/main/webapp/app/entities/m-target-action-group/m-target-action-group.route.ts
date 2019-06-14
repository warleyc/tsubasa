import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetActionGroup } from 'app/shared/model/m-target-action-group.model';
import { MTargetActionGroupService } from './m-target-action-group.service';
import { MTargetActionGroupComponent } from './m-target-action-group.component';
import { MTargetActionGroupDetailComponent } from './m-target-action-group-detail.component';
import { MTargetActionGroupUpdateComponent } from './m-target-action-group-update.component';
import { MTargetActionGroupDeletePopupComponent } from './m-target-action-group-delete-dialog.component';
import { IMTargetActionGroup } from 'app/shared/model/m-target-action-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetActionGroupResolve implements Resolve<IMTargetActionGroup> {
  constructor(private service: MTargetActionGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetActionGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetActionGroup>) => response.ok),
        map((mTargetActionGroup: HttpResponse<MTargetActionGroup>) => mTargetActionGroup.body)
      );
    }
    return of(new MTargetActionGroup());
  }
}

export const mTargetActionGroupRoute: Routes = [
  {
    path: '',
    component: MTargetActionGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetActionGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetActionGroupDetailComponent,
    resolve: {
      mTargetActionGroup: MTargetActionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetActionGroupUpdateComponent,
    resolve: {
      mTargetActionGroup: MTargetActionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetActionGroupUpdateComponent,
    resolve: {
      mTargetActionGroup: MTargetActionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetActionGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetActionGroupDeletePopupComponent,
    resolve: {
      mTargetActionGroup: MTargetActionGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetActionGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
