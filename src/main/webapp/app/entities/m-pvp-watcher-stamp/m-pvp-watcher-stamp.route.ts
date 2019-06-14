import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';
import { MPvpWatcherStampService } from './m-pvp-watcher-stamp.service';
import { MPvpWatcherStampComponent } from './m-pvp-watcher-stamp.component';
import { MPvpWatcherStampDetailComponent } from './m-pvp-watcher-stamp-detail.component';
import { MPvpWatcherStampUpdateComponent } from './m-pvp-watcher-stamp-update.component';
import { MPvpWatcherStampDeletePopupComponent } from './m-pvp-watcher-stamp-delete-dialog.component';
import { IMPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';

@Injectable({ providedIn: 'root' })
export class MPvpWatcherStampResolve implements Resolve<IMPvpWatcherStamp> {
  constructor(private service: MPvpWatcherStampService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpWatcherStamp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpWatcherStamp>) => response.ok),
        map((mPvpWatcherStamp: HttpResponse<MPvpWatcherStamp>) => mPvpWatcherStamp.body)
      );
    }
    return of(new MPvpWatcherStamp());
  }
}

export const mPvpWatcherStampRoute: Routes = [
  {
    path: '',
    component: MPvpWatcherStampComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpWatcherStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpWatcherStampDetailComponent,
    resolve: {
      mPvpWatcherStamp: MPvpWatcherStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWatcherStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpWatcherStampUpdateComponent,
    resolve: {
      mPvpWatcherStamp: MPvpWatcherStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWatcherStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpWatcherStampUpdateComponent,
    resolve: {
      mPvpWatcherStamp: MPvpWatcherStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWatcherStamps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpWatcherStampPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpWatcherStampDeletePopupComponent,
    resolve: {
      mPvpWatcherStamp: MPvpWatcherStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpWatcherStamps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
