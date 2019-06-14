import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMovieAsset } from 'app/shared/model/m-movie-asset.model';
import { MMovieAssetService } from './m-movie-asset.service';
import { MMovieAssetComponent } from './m-movie-asset.component';
import { MMovieAssetDetailComponent } from './m-movie-asset-detail.component';
import { MMovieAssetUpdateComponent } from './m-movie-asset-update.component';
import { MMovieAssetDeletePopupComponent } from './m-movie-asset-delete-dialog.component';
import { IMMovieAsset } from 'app/shared/model/m-movie-asset.model';

@Injectable({ providedIn: 'root' })
export class MMovieAssetResolve implements Resolve<IMMovieAsset> {
  constructor(private service: MMovieAssetService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMovieAsset> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMovieAsset>) => response.ok),
        map((mMovieAsset: HttpResponse<MMovieAsset>) => mMovieAsset.body)
      );
    }
    return of(new MMovieAsset());
  }
}

export const mMovieAssetRoute: Routes = [
  {
    path: '',
    component: MMovieAssetComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMovieAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMovieAssetDetailComponent,
    resolve: {
      mMovieAsset: MMovieAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMovieAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMovieAssetUpdateComponent,
    resolve: {
      mMovieAsset: MMovieAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMovieAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMovieAssetUpdateComponent,
    resolve: {
      mMovieAsset: MMovieAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMovieAssets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMovieAssetPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMovieAssetDeletePopupComponent,
    resolve: {
      mMovieAsset: MMovieAssetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMovieAssets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
