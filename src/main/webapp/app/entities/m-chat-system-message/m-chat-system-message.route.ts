import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';
import { MChatSystemMessageService } from './m-chat-system-message.service';
import { MChatSystemMessageComponent } from './m-chat-system-message.component';
import { MChatSystemMessageDetailComponent } from './m-chat-system-message-detail.component';
import { MChatSystemMessageUpdateComponent } from './m-chat-system-message-update.component';
import { MChatSystemMessageDeletePopupComponent } from './m-chat-system-message-delete-dialog.component';
import { IMChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';

@Injectable({ providedIn: 'root' })
export class MChatSystemMessageResolve implements Resolve<IMChatSystemMessage> {
  constructor(private service: MChatSystemMessageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMChatSystemMessage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MChatSystemMessage>) => response.ok),
        map((mChatSystemMessage: HttpResponse<MChatSystemMessage>) => mChatSystemMessage.body)
      );
    }
    return of(new MChatSystemMessage());
  }
}

export const mChatSystemMessageRoute: Routes = [
  {
    path: '',
    component: MChatSystemMessageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MChatSystemMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MChatSystemMessageDetailComponent,
    resolve: {
      mChatSystemMessage: MChatSystemMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChatSystemMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MChatSystemMessageUpdateComponent,
    resolve: {
      mChatSystemMessage: MChatSystemMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChatSystemMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MChatSystemMessageUpdateComponent,
    resolve: {
      mChatSystemMessage: MChatSystemMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChatSystemMessages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mChatSystemMessagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MChatSystemMessageDeletePopupComponent,
    resolve: {
      mChatSystemMessage: MChatSystemMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChatSystemMessages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
