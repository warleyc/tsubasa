import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';
import { MModelUniformUpResourceService } from './m-model-uniform-up-resource.service';
import { MModelUniformUpResourceComponent } from './m-model-uniform-up-resource.component';
import { MModelUniformUpResourceDetailComponent } from './m-model-uniform-up-resource-detail.component';
import { MModelUniformUpResourceUpdateComponent } from './m-model-uniform-up-resource-update.component';
import { MModelUniformUpResourceDeletePopupComponent } from './m-model-uniform-up-resource-delete-dialog.component';
import { IMModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';

@Injectable({ providedIn: 'root' })
export class MModelUniformUpResourceResolve implements Resolve<IMModelUniformUpResource> {
  constructor(private service: MModelUniformUpResourceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMModelUniformUpResource> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MModelUniformUpResource>) => response.ok),
        map((mModelUniformUpResource: HttpResponse<MModelUniformUpResource>) => mModelUniformUpResource.body)
      );
    }
    return of(new MModelUniformUpResource());
  }
}

export const mModelUniformUpResourceRoute: Routes = [
  {
    path: '',
    component: MModelUniformUpResourceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MModelUniformUpResources'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MModelUniformUpResourceDetailComponent,
    resolve: {
      mModelUniformUpResource: MModelUniformUpResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUpResources'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MModelUniformUpResourceUpdateComponent,
    resolve: {
      mModelUniformUpResource: MModelUniformUpResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUpResources'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MModelUniformUpResourceUpdateComponent,
    resolve: {
      mModelUniformUpResource: MModelUniformUpResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUpResources'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mModelUniformUpResourcePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MModelUniformUpResourceDeletePopupComponent,
    resolve: {
      mModelUniformUpResource: MModelUniformUpResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUpResources'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
