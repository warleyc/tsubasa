import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';
import { MModelUniformBottomResourceService } from './m-model-uniform-bottom-resource.service';
import { MModelUniformBottomResourceComponent } from './m-model-uniform-bottom-resource.component';
import { MModelUniformBottomResourceDetailComponent } from './m-model-uniform-bottom-resource-detail.component';
import { MModelUniformBottomResourceUpdateComponent } from './m-model-uniform-bottom-resource-update.component';
import { MModelUniformBottomResourceDeletePopupComponent } from './m-model-uniform-bottom-resource-delete-dialog.component';
import { IMModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';

@Injectable({ providedIn: 'root' })
export class MModelUniformBottomResourceResolve implements Resolve<IMModelUniformBottomResource> {
  constructor(private service: MModelUniformBottomResourceService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMModelUniformBottomResource> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MModelUniformBottomResource>) => response.ok),
        map((mModelUniformBottomResource: HttpResponse<MModelUniformBottomResource>) => mModelUniformBottomResource.body)
      );
    }
    return of(new MModelUniformBottomResource());
  }
}

export const mModelUniformBottomResourceRoute: Routes = [
  {
    path: '',
    component: MModelUniformBottomResourceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MModelUniformBottomResources'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MModelUniformBottomResourceDetailComponent,
    resolve: {
      mModelUniformBottomResource: MModelUniformBottomResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottomResources'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MModelUniformBottomResourceUpdateComponent,
    resolve: {
      mModelUniformBottomResource: MModelUniformBottomResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottomResources'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MModelUniformBottomResourceUpdateComponent,
    resolve: {
      mModelUniformBottomResource: MModelUniformBottomResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottomResources'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mModelUniformBottomResourcePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MModelUniformBottomResourceDeletePopupComponent,
    resolve: {
      mModelUniformBottomResource: MModelUniformBottomResourceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformBottomResources'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
