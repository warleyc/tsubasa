import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MConstant } from 'app/shared/model/m-constant.model';
import { MConstantService } from './m-constant.service';
import { MConstantComponent } from './m-constant.component';
import { MConstantDetailComponent } from './m-constant-detail.component';
import { MConstantUpdateComponent } from './m-constant-update.component';
import { MConstantDeletePopupComponent } from './m-constant-delete-dialog.component';
import { IMConstant } from 'app/shared/model/m-constant.model';

@Injectable({ providedIn: 'root' })
export class MConstantResolve implements Resolve<IMConstant> {
  constructor(private service: MConstantService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMConstant> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MConstant>) => response.ok),
        map((mConstant: HttpResponse<MConstant>) => mConstant.body)
      );
    }
    return of(new MConstant());
  }
}

export const mConstantRoute: Routes = [
  {
    path: '',
    component: MConstantComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MConstants'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MConstantDetailComponent,
    resolve: {
      mConstant: MConstantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MConstants'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MConstantUpdateComponent,
    resolve: {
      mConstant: MConstantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MConstants'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MConstantUpdateComponent,
    resolve: {
      mConstant: MConstantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MConstants'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mConstantPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MConstantDeletePopupComponent,
    resolve: {
      mConstant: MConstantResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MConstants'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
