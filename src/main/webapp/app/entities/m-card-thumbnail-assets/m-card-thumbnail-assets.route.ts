import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';
import { MCardThumbnailAssetsService } from './m-card-thumbnail-assets.service';
import { MCardThumbnailAssetsComponent } from './m-card-thumbnail-assets.component';
import { MCardThumbnailAssetsDetailComponent } from './m-card-thumbnail-assets-detail.component';
import { MCardThumbnailAssetsUpdateComponent } from './m-card-thumbnail-assets-update.component';
import { MCardThumbnailAssetsDeletePopupComponent } from './m-card-thumbnail-assets-delete-dialog.component';
import { IMCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';

@Injectable({ providedIn: 'root' })
export class MCardThumbnailAssetsResolve implements Resolve<IMCardThumbnailAssets> {
  constructor(private service: MCardThumbnailAssetsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCardThumbnailAssets> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCardThumbnailAssets>) => response.ok),
        map((mCardThumbnailAssets: HttpResponse<MCardThumbnailAssets>) => mCardThumbnailAssets.body)
      );
    }
    return of(new MCardThumbnailAssets());
  }
}

export const mCardThumbnailAssetsRoute: Routes = [
  {
    path: '',
    component: MCardThumbnailAssetsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCardThumbnailAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCardThumbnailAssetsDetailComponent,
    resolve: {
      mCardThumbnailAssets: MCardThumbnailAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardThumbnailAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCardThumbnailAssetsUpdateComponent,
    resolve: {
      mCardThumbnailAssets: MCardThumbnailAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardThumbnailAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCardThumbnailAssetsUpdateComponent,
    resolve: {
      mCardThumbnailAssets: MCardThumbnailAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardThumbnailAssets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCardThumbnailAssetsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCardThumbnailAssetsDeletePopupComponent,
    resolve: {
      mCardThumbnailAssets: MCardThumbnailAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardThumbnailAssets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
