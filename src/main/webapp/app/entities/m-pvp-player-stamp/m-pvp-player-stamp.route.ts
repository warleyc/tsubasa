import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';
import { MPvpPlayerStampService } from './m-pvp-player-stamp.service';
import { MPvpPlayerStampComponent } from './m-pvp-player-stamp.component';
import { MPvpPlayerStampDetailComponent } from './m-pvp-player-stamp-detail.component';
import { MPvpPlayerStampUpdateComponent } from './m-pvp-player-stamp-update.component';
import { MPvpPlayerStampDeletePopupComponent } from './m-pvp-player-stamp-delete-dialog.component';
import { IMPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';

@Injectable({ providedIn: 'root' })
export class MPvpPlayerStampResolve implements Resolve<IMPvpPlayerStamp> {
  constructor(private service: MPvpPlayerStampService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpPlayerStamp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpPlayerStamp>) => response.ok),
        map((mPvpPlayerStamp: HttpResponse<MPvpPlayerStamp>) => mPvpPlayerStamp.body)
      );
    }
    return of(new MPvpPlayerStamp());
  }
}

export const mPvpPlayerStampRoute: Routes = [
  {
    path: '',
    component: MPvpPlayerStampComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpPlayerStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpPlayerStampDetailComponent,
    resolve: {
      mPvpPlayerStamp: MPvpPlayerStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpPlayerStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpPlayerStampUpdateComponent,
    resolve: {
      mPvpPlayerStamp: MPvpPlayerStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpPlayerStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpPlayerStampUpdateComponent,
    resolve: {
      mPvpPlayerStamp: MPvpPlayerStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpPlayerStamps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpPlayerStampPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpPlayerStampDeletePopupComponent,
    resolve: {
      mPvpPlayerStamp: MPvpPlayerStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpPlayerStamps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
