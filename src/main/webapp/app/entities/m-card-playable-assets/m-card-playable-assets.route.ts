import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';
import { MCardPlayableAssetsService } from './m-card-playable-assets.service';
import { MCardPlayableAssetsComponent } from './m-card-playable-assets.component';
import { MCardPlayableAssetsDetailComponent } from './m-card-playable-assets-detail.component';
import { MCardPlayableAssetsUpdateComponent } from './m-card-playable-assets-update.component';
import { MCardPlayableAssetsDeletePopupComponent } from './m-card-playable-assets-delete-dialog.component';
import { IMCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';

@Injectable({ providedIn: 'root' })
export class MCardPlayableAssetsResolve implements Resolve<IMCardPlayableAssets> {
  constructor(private service: MCardPlayableAssetsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCardPlayableAssets> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCardPlayableAssets>) => response.ok),
        map((mCardPlayableAssets: HttpResponse<MCardPlayableAssets>) => mCardPlayableAssets.body)
      );
    }
    return of(new MCardPlayableAssets());
  }
}

export const mCardPlayableAssetsRoute: Routes = [
  {
    path: '',
    component: MCardPlayableAssetsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCardPlayableAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCardPlayableAssetsDetailComponent,
    resolve: {
      mCardPlayableAssets: MCardPlayableAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPlayableAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCardPlayableAssetsUpdateComponent,
    resolve: {
      mCardPlayableAssets: MCardPlayableAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPlayableAssets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCardPlayableAssetsUpdateComponent,
    resolve: {
      mCardPlayableAssets: MCardPlayableAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPlayableAssets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCardPlayableAssetsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCardPlayableAssetsDeletePopupComponent,
    resolve: {
      mCardPlayableAssets: MCardPlayableAssetsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardPlayableAssets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
