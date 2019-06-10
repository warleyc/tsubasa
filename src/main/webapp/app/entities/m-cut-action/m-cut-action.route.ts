import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCutAction } from 'app/shared/model/m-cut-action.model';
import { MCutActionService } from './m-cut-action.service';
import { MCutActionComponent } from './m-cut-action.component';
import { MCutActionDetailComponent } from './m-cut-action-detail.component';
import { MCutActionUpdateComponent } from './m-cut-action-update.component';
import { MCutActionDeletePopupComponent } from './m-cut-action-delete-dialog.component';
import { IMCutAction } from 'app/shared/model/m-cut-action.model';

@Injectable({ providedIn: 'root' })
export class MCutActionResolve implements Resolve<IMCutAction> {
  constructor(private service: MCutActionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCutAction> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCutAction>) => response.ok),
        map((mCutAction: HttpResponse<MCutAction>) => mCutAction.body)
      );
    }
    return of(new MCutAction());
  }
}

export const mCutActionRoute: Routes = [
  {
    path: '',
    component: MCutActionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCutActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCutActionDetailComponent,
    resolve: {
      mCutAction: MCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCutActionUpdateComponent,
    resolve: {
      mCutAction: MCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCutActionUpdateComponent,
    resolve: {
      mCutAction: MCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutActions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCutActionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCutActionDeletePopupComponent,
    resolve: {
      mCutAction: MCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutActions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
