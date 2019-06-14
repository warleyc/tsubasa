import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';
import { MModelUniformBottomService } from './m-model-uniform-bottom.service';
import { MModelUniformBottomComponent } from './m-model-uniform-bottom.component';
import { MModelUniformBottomDetailComponent } from './m-model-uniform-bottom-detail.component';
import { MModelUniformBottomUpdateComponent } from './m-model-uniform-bottom-update.component';
import { MModelUniformBottomDeletePopupComponent } from './m-model-uniform-bottom-delete-dialog.component';
import { IMModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';

@Injectable({ providedIn: 'root' })
export class MModelUniformBottomResolve implements Resolve<IMModelUniformBottom> {
  constructor(private service: MModelUniformBottomService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMModelUniformBottom> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MModelUniformBottom>) => response.ok),
        map((mModelUniformBottom: HttpResponse<MModelUniformBottom>) => mModelUniformBottom.body)
      );
    }
    return of(new MModelUniformBottom());
  }
}

export const mModelUniformBottomRoute: Routes = [
  {
    path: '',
    component: MModelUniformBottomComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MModelUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MModelUniformBottomDetailComponent,
    resolve: {
      mModelUniformBottom: MModelUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MModelUniformBottomUpdateComponent,
    resolve: {
      mModelUniformBottom: MModelUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MModelUniformBottomUpdateComponent,
    resolve: {
      mModelUniformBottom: MModelUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottoms'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mModelUniformBottomPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MModelUniformBottomDeletePopupComponent,
    resolve: {
      mModelUniformBottom: MModelUniformBottomResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottoms'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
