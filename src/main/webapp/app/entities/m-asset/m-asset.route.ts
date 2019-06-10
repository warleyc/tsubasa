import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAsset } from 'app/shared/model/m-asset.model';
import { MAssetService } from './m-asset.service';
import { MAssetComponent } from './m-asset.component';
import { MAssetDetailComponent } from './m-asset-detail.component';
import { MAssetUpdateComponent } from './m-asset-update.component';
import { MAssetDeletePopupComponent } from './m-asset-delete-dialog.component';
import { IMAsset } from 'app/shared/model/m-asset.model';

@Injectable({ providedIn: 'root' })
export class MAssetResolve implements Resolve<IMAsset> {
  constructor(private service: MAssetService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAsset> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAsset>) => response.ok),
        map((mAsset: HttpResponse<MAsset>) => mAsset.body)
      );
    }
    return of(new MAsset());
  }
}

export const mAssetRoute: Routes = [
  {
    path: '',
    component: MAssetComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAssetDetailComponent,
    resolve: {
      mAsset: MAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAssetUpdateComponent,
    resolve: {
      mAsset: MAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAssetUpdateComponent,
    resolve: {
      mAsset: MAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAssetPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAssetDeletePopupComponent,
    resolve: {
      mAsset: MAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
