import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';
import { MKeeperCutActionService } from './m-keeper-cut-action.service';
import { MKeeperCutActionComponent } from './m-keeper-cut-action.component';
import { MKeeperCutActionDetailComponent } from './m-keeper-cut-action-detail.component';
import { MKeeperCutActionUpdateComponent } from './m-keeper-cut-action-update.component';
import { MKeeperCutActionDeletePopupComponent } from './m-keeper-cut-action-delete-dialog.component';
import { IMKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';

@Injectable({ providedIn: 'root' })
export class MKeeperCutActionResolve implements Resolve<IMKeeperCutAction> {
  constructor(private service: MKeeperCutActionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMKeeperCutAction> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MKeeperCutAction>) => response.ok),
        map((mKeeperCutAction: HttpResponse<MKeeperCutAction>) => mKeeperCutAction.body)
      );
    }
    return of(new MKeeperCutAction());
  }
}

export const mKeeperCutActionRoute: Routes = [
  {
    path: '',
    component: MKeeperCutActionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MKeeperCutActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MKeeperCutActionDetailComponent,
    resolve: {
      mKeeperCutAction: MKeeperCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MKeeperCutActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MKeeperCutActionUpdateComponent,
    resolve: {
      mKeeperCutAction: MKeeperCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MKeeperCutActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MKeeperCutActionUpdateComponent,
    resolve: {
      mKeeperCutAction: MKeeperCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MKeeperCutActions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mKeeperCutActionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MKeeperCutActionDeletePopupComponent,
    resolve: {
      mKeeperCutAction: MKeeperCutActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MKeeperCutActions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
