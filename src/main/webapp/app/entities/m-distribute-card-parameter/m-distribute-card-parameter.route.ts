import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';
import { MDistributeCardParameterService } from './m-distribute-card-parameter.service';
import { MDistributeCardParameterComponent } from './m-distribute-card-parameter.component';
import { MDistributeCardParameterDetailComponent } from './m-distribute-card-parameter-detail.component';
import { MDistributeCardParameterUpdateComponent } from './m-distribute-card-parameter-update.component';
import { MDistributeCardParameterDeletePopupComponent } from './m-distribute-card-parameter-delete-dialog.component';
import { IMDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';

@Injectable({ providedIn: 'root' })
export class MDistributeCardParameterResolve implements Resolve<IMDistributeCardParameter> {
  constructor(private service: MDistributeCardParameterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDistributeCardParameter> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDistributeCardParameter>) => response.ok),
        map((mDistributeCardParameter: HttpResponse<MDistributeCardParameter>) => mDistributeCardParameter.body)
      );
    }
    return of(new MDistributeCardParameter());
  }
}

export const mDistributeCardParameterRoute: Routes = [
  {
    path: '',
    component: MDistributeCardParameterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDistributeCardParameters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDistributeCardParameterDetailComponent,
    resolve: {
      mDistributeCardParameter: MDistributeCardParameterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDistributeCardParameterUpdateComponent,
    resolve: {
      mDistributeCardParameter: MDistributeCardParameterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDistributeCardParameterUpdateComponent,
    resolve: {
      mDistributeCardParameter: MDistributeCardParameterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDistributeCardParameterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDistributeCardParameterDeletePopupComponent,
    resolve: {
      mDistributeCardParameter: MDistributeCardParameterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
