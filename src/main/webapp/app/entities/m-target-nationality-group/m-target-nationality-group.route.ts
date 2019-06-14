import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';
import { MTargetNationalityGroupService } from './m-target-nationality-group.service';
import { MTargetNationalityGroupComponent } from './m-target-nationality-group.component';
import { MTargetNationalityGroupDetailComponent } from './m-target-nationality-group-detail.component';
import { MTargetNationalityGroupUpdateComponent } from './m-target-nationality-group-update.component';
import { MTargetNationalityGroupDeletePopupComponent } from './m-target-nationality-group-delete-dialog.component';
import { IMTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetNationalityGroupResolve implements Resolve<IMTargetNationalityGroup> {
  constructor(private service: MTargetNationalityGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetNationalityGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetNationalityGroup>) => response.ok),
        map((mTargetNationalityGroup: HttpResponse<MTargetNationalityGroup>) => mTargetNationalityGroup.body)
      );
    }
    return of(new MTargetNationalityGroup());
  }
}

export const mTargetNationalityGroupRoute: Routes = [
  {
    path: '',
    component: MTargetNationalityGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetNationalityGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetNationalityGroupDetailComponent,
    resolve: {
      mTargetNationalityGroup: MTargetNationalityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetNationalityGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetNationalityGroupUpdateComponent,
    resolve: {
      mTargetNationalityGroup: MTargetNationalityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetNationalityGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetNationalityGroupUpdateComponent,
    resolve: {
      mTargetNationalityGroup: MTargetNationalityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetNationalityGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetNationalityGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetNationalityGroupDeletePopupComponent,
    resolve: {
      mTargetNationalityGroup: MTargetNationalityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetNationalityGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
