import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';
import { MCoopRoomStampService } from './m-coop-room-stamp.service';
import { MCoopRoomStampComponent } from './m-coop-room-stamp.component';
import { MCoopRoomStampDetailComponent } from './m-coop-room-stamp-detail.component';
import { MCoopRoomStampUpdateComponent } from './m-coop-room-stamp-update.component';
import { MCoopRoomStampDeletePopupComponent } from './m-coop-room-stamp-delete-dialog.component';
import { IMCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';

@Injectable({ providedIn: 'root' })
export class MCoopRoomStampResolve implements Resolve<IMCoopRoomStamp> {
  constructor(private service: MCoopRoomStampService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCoopRoomStamp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCoopRoomStamp>) => response.ok),
        map((mCoopRoomStamp: HttpResponse<MCoopRoomStamp>) => mCoopRoomStamp.body)
      );
    }
    return of(new MCoopRoomStamp());
  }
}

export const mCoopRoomStampRoute: Routes = [
  {
    path: '',
    component: MCoopRoomStampComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCoopRoomStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCoopRoomStampDetailComponent,
    resolve: {
      mCoopRoomStamp: MCoopRoomStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCoopRoomStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCoopRoomStampUpdateComponent,
    resolve: {
      mCoopRoomStamp: MCoopRoomStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCoopRoomStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCoopRoomStampUpdateComponent,
    resolve: {
      mCoopRoomStamp: MCoopRoomStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCoopRoomStamps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCoopRoomStampPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCoopRoomStampDeletePopupComponent,
    resolve: {
      mCoopRoomStamp: MCoopRoomStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCoopRoomStamps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
