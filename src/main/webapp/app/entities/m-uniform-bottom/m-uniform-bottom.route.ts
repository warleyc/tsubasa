import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MUniformBottom } from 'app/shared/model/m-uniform-bottom.model';
import { MUniformBottomService } from './m-uniform-bottom.service';
import { MUniformBottomComponent } from './m-uniform-bottom.component';
import { MUniformBottomDetailComponent } from './m-uniform-bottom-detail.component';
import { MUniformBottomUpdateComponent } from './m-uniform-bottom-update.component';
import { MUniformBottomDeletePopupComponent } from './m-uniform-bottom-delete-dialog.component';
import { IMUniformBottom } from 'app/shared/model/m-uniform-bottom.model';

@Injectable({ providedIn: 'root' })
export class MUniformBottomResolve implements Resolve<IMUniformBottom> {
  constructor(private service: MUniformBottomService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMUniformBottom> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MUniformBottom>) => response.ok),
        map((mUniformBottom: HttpResponse<MUniformBottom>) => mUniformBottom.body)
      );
    }
    return of(new MUniformBottom());
  }
}

export const mUniformBottomRoute: Routes = [
  {
    path: '',
    component: MUniformBottomComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MUniformBottomDetailComponent,
    resolve: {
      mUniformBottom: MUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MUniformBottomUpdateComponent,
    resolve: {
      mUniformBottom: MUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MUniformBottomUpdateComponent,
    resolve: {
      mUniformBottom: MUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mUniformBottomPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MUniformBottomDeletePopupComponent,
    resolve: {
      mUniformBottom: MUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformBottoms'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
