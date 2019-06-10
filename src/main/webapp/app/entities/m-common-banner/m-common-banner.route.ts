import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCommonBanner } from 'app/shared/model/m-common-banner.model';
import { MCommonBannerService } from './m-common-banner.service';
import { MCommonBannerComponent } from './m-common-banner.component';
import { MCommonBannerDetailComponent } from './m-common-banner-detail.component';
import { MCommonBannerUpdateComponent } from './m-common-banner-update.component';
import { MCommonBannerDeletePopupComponent } from './m-common-banner-delete-dialog.component';
import { IMCommonBanner } from 'app/shared/model/m-common-banner.model';

@Injectable({ providedIn: 'root' })
export class MCommonBannerResolve implements Resolve<IMCommonBanner> {
  constructor(private service: MCommonBannerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCommonBanner> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCommonBanner>) => response.ok),
        map((mCommonBanner: HttpResponse<MCommonBanner>) => mCommonBanner.body)
      );
    }
    return of(new MCommonBanner());
  }
}

export const mCommonBannerRoute: Routes = [
  {
    path: '',
    component: MCommonBannerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCommonBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCommonBannerDetailComponent,
    resolve: {
      mCommonBanner: MCommonBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCommonBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCommonBannerUpdateComponent,
    resolve: {
      mCommonBanner: MCommonBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCommonBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCommonBannerUpdateComponent,
    resolve: {
      mCommonBanner: MCommonBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCommonBanners'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCommonBannerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCommonBannerDeletePopupComponent,
    resolve: {
      mCommonBanner: MCommonBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCommonBanners'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
