import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';
import { MCutSeqGroupService } from './m-cut-seq-group.service';
import { MCutSeqGroupComponent } from './m-cut-seq-group.component';
import { MCutSeqGroupDetailComponent } from './m-cut-seq-group-detail.component';
import { MCutSeqGroupUpdateComponent } from './m-cut-seq-group-update.component';
import { MCutSeqGroupDeletePopupComponent } from './m-cut-seq-group-delete-dialog.component';
import { IMCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';

@Injectable({ providedIn: 'root' })
export class MCutSeqGroupResolve implements Resolve<IMCutSeqGroup> {
  constructor(private service: MCutSeqGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCutSeqGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCutSeqGroup>) => response.ok),
        map((mCutSeqGroup: HttpResponse<MCutSeqGroup>) => mCutSeqGroup.body)
      );
    }
    return of(new MCutSeqGroup());
  }
}

export const mCutSeqGroupRoute: Routes = [
  {
    path: '',
    component: MCutSeqGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCutSeqGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCutSeqGroupDetailComponent,
    resolve: {
      mCutSeqGroup: MCutSeqGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutSeqGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCutSeqGroupUpdateComponent,
    resolve: {
      mCutSeqGroup: MCutSeqGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutSeqGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCutSeqGroupUpdateComponent,
    resolve: {
      mCutSeqGroup: MCutSeqGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutSeqGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCutSeqGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCutSeqGroupDeletePopupComponent,
    resolve: {
      mCutSeqGroup: MCutSeqGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutSeqGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
