import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';
import { MDistributeParamPointService } from './m-distribute-param-point.service';
import { MDistributeParamPointComponent } from './m-distribute-param-point.component';
import { MDistributeParamPointDetailComponent } from './m-distribute-param-point-detail.component';
import { MDistributeParamPointUpdateComponent } from './m-distribute-param-point-update.component';
import { MDistributeParamPointDeletePopupComponent } from './m-distribute-param-point-delete-dialog.component';
import { IMDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';

@Injectable({ providedIn: 'root' })
export class MDistributeParamPointResolve implements Resolve<IMDistributeParamPoint> {
  constructor(private service: MDistributeParamPointService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDistributeParamPoint> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDistributeParamPoint>) => response.ok),
        map((mDistributeParamPoint: HttpResponse<MDistributeParamPoint>) => mDistributeParamPoint.body)
      );
    }
    return of(new MDistributeParamPoint());
  }
}

export const mDistributeParamPointRoute: Routes = [
  {
    path: '',
    component: MDistributeParamPointComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDistributeParamPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDistributeParamPointDetailComponent,
    resolve: {
      mDistributeParamPoint: MDistributeParamPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeParamPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDistributeParamPointUpdateComponent,
    resolve: {
      mDistributeParamPoint: MDistributeParamPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeParamPoints'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDistributeParamPointUpdateComponent,
    resolve: {
      mDistributeParamPoint: MDistributeParamPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeParamPoints'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDistributeParamPointPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDistributeParamPointDeletePopupComponent,
    resolve: {
      mDistributeParamPoint: MDistributeParamPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeParamPoints'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
