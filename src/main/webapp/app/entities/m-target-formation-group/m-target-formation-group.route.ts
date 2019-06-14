import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';
import { MTargetFormationGroupService } from './m-target-formation-group.service';
import { MTargetFormationGroupComponent } from './m-target-formation-group.component';
import { MTargetFormationGroupDetailComponent } from './m-target-formation-group-detail.component';
import { MTargetFormationGroupUpdateComponent } from './m-target-formation-group-update.component';
import { MTargetFormationGroupDeletePopupComponent } from './m-target-formation-group-delete-dialog.component';
import { IMTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetFormationGroupResolve implements Resolve<IMTargetFormationGroup> {
  constructor(private service: MTargetFormationGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetFormationGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetFormationGroup>) => response.ok),
        map((mTargetFormationGroup: HttpResponse<MTargetFormationGroup>) => mTargetFormationGroup.body)
      );
    }
    return of(new MTargetFormationGroup());
  }
}

export const mTargetFormationGroupRoute: Routes = [
  {
    path: '',
    component: MTargetFormationGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetFormationGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetFormationGroupDetailComponent,
    resolve: {
      mTargetFormationGroup: MTargetFormationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetFormationGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetFormationGroupUpdateComponent,
    resolve: {
      mTargetFormationGroup: MTargetFormationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetFormationGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetFormationGroupUpdateComponent,
    resolve: {
      mTargetFormationGroup: MTargetFormationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetFormationGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetFormationGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetFormationGroupDeletePopupComponent,
    resolve: {
      mTargetFormationGroup: MTargetFormationGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetFormationGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
