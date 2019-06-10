import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAction } from 'app/shared/model/m-action.model';
import { MActionService } from './m-action.service';
import { MActionComponent } from './m-action.component';
import { MActionDetailComponent } from './m-action-detail.component';
import { MActionUpdateComponent } from './m-action-update.component';
import { MActionDeletePopupComponent } from './m-action-delete-dialog.component';
import { IMAction } from 'app/shared/model/m-action.model';

@Injectable({ providedIn: 'root' })
export class MActionResolve implements Resolve<IMAction> {
  constructor(private service: MActionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAction> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAction>) => response.ok),
        map((mAction: HttpResponse<MAction>) => mAction.body)
      );
    }
    return of(new MAction());
  }
}

export const mActionRoute: Routes = [
  {
    path: '',
    component: MActionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MActionDetailComponent,
    resolve: {
      mAction: MActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MActionUpdateComponent,
    resolve: {
      mAction: MActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MActionUpdateComponent,
    resolve: {
      mAction: MActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mActionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MActionDeletePopupComponent,
    resolve: {
      mAction: MActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
