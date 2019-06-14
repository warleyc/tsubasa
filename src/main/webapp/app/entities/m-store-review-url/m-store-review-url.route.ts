import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';
import { MStoreReviewUrlService } from './m-store-review-url.service';
import { MStoreReviewUrlComponent } from './m-store-review-url.component';
import { MStoreReviewUrlDetailComponent } from './m-store-review-url-detail.component';
import { MStoreReviewUrlUpdateComponent } from './m-store-review-url-update.component';
import { MStoreReviewUrlDeletePopupComponent } from './m-store-review-url-delete-dialog.component';
import { IMStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';

@Injectable({ providedIn: 'root' })
export class MStoreReviewUrlResolve implements Resolve<IMStoreReviewUrl> {
  constructor(private service: MStoreReviewUrlService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMStoreReviewUrl> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MStoreReviewUrl>) => response.ok),
        map((mStoreReviewUrl: HttpResponse<MStoreReviewUrl>) => mStoreReviewUrl.body)
      );
    }
    return of(new MStoreReviewUrl());
  }
}

export const mStoreReviewUrlRoute: Routes = [
  {
    path: '',
    component: MStoreReviewUrlComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MStoreReviewUrls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MStoreReviewUrlDetailComponent,
    resolve: {
      mStoreReviewUrl: MStoreReviewUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoreReviewUrls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MStoreReviewUrlUpdateComponent,
    resolve: {
      mStoreReviewUrl: MStoreReviewUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoreReviewUrls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MStoreReviewUrlUpdateComponent,
    resolve: {
      mStoreReviewUrl: MStoreReviewUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoreReviewUrls'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mStoreReviewUrlPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MStoreReviewUrlDeletePopupComponent,
    resolve: {
      mStoreReviewUrl: MStoreReviewUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoreReviewUrls'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
