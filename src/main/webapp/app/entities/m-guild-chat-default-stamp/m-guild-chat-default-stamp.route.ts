import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';
import { MGuildChatDefaultStampService } from './m-guild-chat-default-stamp.service';
import { MGuildChatDefaultStampComponent } from './m-guild-chat-default-stamp.component';
import { MGuildChatDefaultStampDetailComponent } from './m-guild-chat-default-stamp-detail.component';
import { MGuildChatDefaultStampUpdateComponent } from './m-guild-chat-default-stamp-update.component';
import { MGuildChatDefaultStampDeletePopupComponent } from './m-guild-chat-default-stamp-delete-dialog.component';
import { IMGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';

@Injectable({ providedIn: 'root' })
export class MGuildChatDefaultStampResolve implements Resolve<IMGuildChatDefaultStamp> {
  constructor(private service: MGuildChatDefaultStampService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildChatDefaultStamp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildChatDefaultStamp>) => response.ok),
        map((mGuildChatDefaultStamp: HttpResponse<MGuildChatDefaultStamp>) => mGuildChatDefaultStamp.body)
      );
    }
    return of(new MGuildChatDefaultStamp());
  }
}

export const mGuildChatDefaultStampRoute: Routes = [
  {
    path: '',
    component: MGuildChatDefaultStampComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildChatDefaultStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildChatDefaultStampDetailComponent,
    resolve: {
      mGuildChatDefaultStamp: MGuildChatDefaultStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildChatDefaultStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildChatDefaultStampUpdateComponent,
    resolve: {
      mGuildChatDefaultStamp: MGuildChatDefaultStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildChatDefaultStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildChatDefaultStampUpdateComponent,
    resolve: {
      mGuildChatDefaultStamp: MGuildChatDefaultStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildChatDefaultStamps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildChatDefaultStampPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildChatDefaultStampDeletePopupComponent,
    resolve: {
      mGuildChatDefaultStamp: MGuildChatDefaultStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildChatDefaultStamps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
