import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTutorialMessage } from 'app/shared/model/m-tutorial-message.model';
import { MTutorialMessageService } from './m-tutorial-message.service';
import { MTutorialMessageComponent } from './m-tutorial-message.component';
import { MTutorialMessageDetailComponent } from './m-tutorial-message-detail.component';
import { MTutorialMessageUpdateComponent } from './m-tutorial-message-update.component';
import { MTutorialMessageDeletePopupComponent } from './m-tutorial-message-delete-dialog.component';
import { IMTutorialMessage } from 'app/shared/model/m-tutorial-message.model';

@Injectable({ providedIn: 'root' })
export class MTutorialMessageResolve implements Resolve<IMTutorialMessage> {
  constructor(private service: MTutorialMessageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTutorialMessage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTutorialMessage>) => response.ok),
        map((mTutorialMessage: HttpResponse<MTutorialMessage>) => mTutorialMessage.body)
      );
    }
    return of(new MTutorialMessage());
  }
}

export const mTutorialMessageRoute: Routes = [
  {
    path: '',
    component: MTutorialMessageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTutorialMessageDetailComponent,
    resolve: {
      mTutorialMessage: MTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTutorialMessageUpdateComponent,
    resolve: {
      mTutorialMessage: MTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTutorialMessageUpdateComponent,
    resolve: {
      mTutorialMessage: MTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTutorialMessagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTutorialMessageDeletePopupComponent,
    resolve: {
      mTutorialMessage: MTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTutorialMessages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
