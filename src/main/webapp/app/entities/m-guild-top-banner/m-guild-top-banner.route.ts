import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';
import { MGuildTopBannerService } from './m-guild-top-banner.service';
import { MGuildTopBannerComponent } from './m-guild-top-banner.component';
import { MGuildTopBannerDetailComponent } from './m-guild-top-banner-detail.component';
import { MGuildTopBannerUpdateComponent } from './m-guild-top-banner-update.component';
import { MGuildTopBannerDeletePopupComponent } from './m-guild-top-banner-delete-dialog.component';
import { IMGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';

@Injectable({ providedIn: 'root' })
export class MGuildTopBannerResolve implements Resolve<IMGuildTopBanner> {
  constructor(private service: MGuildTopBannerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildTopBanner> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildTopBanner>) => response.ok),
        map((mGuildTopBanner: HttpResponse<MGuildTopBanner>) => mGuildTopBanner.body)
      );
    }
    return of(new MGuildTopBanner());
  }
}

export const mGuildTopBannerRoute: Routes = [
  {
    path: '',
    component: MGuildTopBannerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildTopBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildTopBannerDetailComponent,
    resolve: {
      mGuildTopBanner: MGuildTopBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildTopBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildTopBannerUpdateComponent,
    resolve: {
      mGuildTopBanner: MGuildTopBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildTopBanners'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildTopBannerUpdateComponent,
    resolve: {
      mGuildTopBanner: MGuildTopBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildTopBanners'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildTopBannerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildTopBannerDeletePopupComponent,
    resolve: {
      mGuildTopBanner: MGuildTopBannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildTopBanners'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
