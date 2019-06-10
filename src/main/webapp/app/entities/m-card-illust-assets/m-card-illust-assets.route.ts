import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';
import { MCardIllustAssetsService } from './m-card-illust-assets.service';
import { MCardIllustAssetsComponent } from './m-card-illust-assets.component';
import { MCardIllustAssetsDetailComponent } from './m-card-illust-assets-detail.component';
import { MCardIllustAssetsUpdateComponent } from './m-card-illust-assets-update.component';
import { MCardIllustAssetsDeletePopupComponent } from './m-card-illust-assets-delete-dialog.component';
import { IMCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';

@Injectable({ providedIn: 'root' })
export class MCardIllustAssetsResolve implements Resolve<IMCardIllustAssets> {
  constructor(private service: MCardIllustAssetsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCardIllustAssets> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCardIllustAssets>) => response.ok),
        map((mCardIllustAssets: HttpResponse<MCardIllustAssets>) => mCardIllustAssets.body)
      );
    }
    return of(new MCardIllustAssets());
  }
}

export const mCardIllustAssetsRoute: Routes = [
  {
    path: '',
    component: MCardIllustAssetsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCardIllustAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCardIllustAssetsDetailComponent,
    resolve: {
      mCardIllustAssets: MCardIllustAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardIllustAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCardIllustAssetsUpdateComponent,
    resolve: {
      mCardIllustAssets: MCardIllustAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardIllustAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCardIllustAssetsUpdateComponent,
    resolve: {
      mCardIllustAssets: MCardIllustAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardIllustAssets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCardIllustAssetsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCardIllustAssetsDeletePopupComponent,
    resolve: {
      mCardIllustAssets: MCardIllustAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardIllustAssets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
