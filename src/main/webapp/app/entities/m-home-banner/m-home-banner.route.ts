import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MHomeBanner } from 'app/shared/model/m-home-banner.model';
import { MHomeBannerService } from './m-home-banner.service';
import { MHomeBannerComponent } from './m-home-banner.component';
import { MHomeBannerDetailComponent } from './m-home-banner-detail.component';
import { MHomeBannerUpdateComponent } from './m-home-banner-update.component';
import { MHomeBannerDeletePopupComponent } from './m-home-banner-delete-dialog.component';
import { IMHomeBanner } from 'app/shared/model/m-home-banner.model';

@Injectable({ providedIn: 'root' })
export class MHomeBannerResolve implements Resolve<IMHomeBanner> {
  constructor(private service: MHomeBannerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMHomeBanner> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MHomeBanner>) => response.ok),
        map((mHomeBanner: HttpResponse<MHomeBanner>) => mHomeBanner.body)
      );
    }
    return of(new MHomeBanner());
  }
}

export const mHomeBannerRoute: Routes = [
  {
    path: '',
    component: MHomeBannerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MHomeBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MHomeBannerDetailComponent,
    resolve: {
      mHomeBanner: MHomeBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MHomeBannerUpdateComponent,
    resolve: {
      mHomeBanner: MHomeBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MHomeBannerUpdateComponent,
    resolve: {
      mHomeBanner: MHomeBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeBanners'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mHomeBannerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MHomeBannerDeletePopupComponent,
    resolve: {
      mHomeBanner: MHomeBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MHomeBanners'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
