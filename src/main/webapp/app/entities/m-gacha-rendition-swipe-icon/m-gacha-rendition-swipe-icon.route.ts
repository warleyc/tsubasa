import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';
import { MGachaRenditionSwipeIconService } from './m-gacha-rendition-swipe-icon.service';
import { MGachaRenditionSwipeIconComponent } from './m-gacha-rendition-swipe-icon.component';
import { MGachaRenditionSwipeIconDetailComponent } from './m-gacha-rendition-swipe-icon-detail.component';
import { MGachaRenditionSwipeIconUpdateComponent } from './m-gacha-rendition-swipe-icon-update.component';
import { MGachaRenditionSwipeIconDeletePopupComponent } from './m-gacha-rendition-swipe-icon-delete-dialog.component';
import { IMGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionSwipeIconResolve implements Resolve<IMGachaRenditionSwipeIcon> {
  constructor(private service: MGachaRenditionSwipeIconService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionSwipeIcon> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionSwipeIcon>) => response.ok),
        map((mGachaRenditionSwipeIcon: HttpResponse<MGachaRenditionSwipeIcon>) => mGachaRenditionSwipeIcon.body)
      );
    }
    return of(new MGachaRenditionSwipeIcon());
  }
}

export const mGachaRenditionSwipeIconRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionSwipeIconComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionSwipeIcons'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionSwipeIconDetailComponent,
    resolve: {
      mGachaRenditionSwipeIcon: MGachaRenditionSwipeIconResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionSwipeIcons'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionSwipeIconUpdateComponent,
    resolve: {
      mGachaRenditionSwipeIcon: MGachaRenditionSwipeIconResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionSwipeIcons'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionSwipeIconUpdateComponent,
    resolve: {
      mGachaRenditionSwipeIcon: MGachaRenditionSwipeIconResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionSwipeIcons'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionSwipeIconPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionSwipeIconDeletePopupComponent,
    resolve: {
      mGachaRenditionSwipeIcon: MGachaRenditionSwipeIconResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionSwipeIcons'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
