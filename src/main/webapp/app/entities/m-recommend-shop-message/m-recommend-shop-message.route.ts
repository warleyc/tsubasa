import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';
import { MRecommendShopMessageService } from './m-recommend-shop-message.service';
import { MRecommendShopMessageComponent } from './m-recommend-shop-message.component';
import { MRecommendShopMessageDetailComponent } from './m-recommend-shop-message-detail.component';
import { MRecommendShopMessageUpdateComponent } from './m-recommend-shop-message-update.component';
import { MRecommendShopMessageDeletePopupComponent } from './m-recommend-shop-message-delete-dialog.component';
import { IMRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';

@Injectable({ providedIn: 'root' })
export class MRecommendShopMessageResolve implements Resolve<IMRecommendShopMessage> {
  constructor(private service: MRecommendShopMessageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMRecommendShopMessage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MRecommendShopMessage>) => response.ok),
        map((mRecommendShopMessage: HttpResponse<MRecommendShopMessage>) => mRecommendShopMessage.body)
      );
    }
    return of(new MRecommendShopMessage());
  }
}

export const mRecommendShopMessageRoute: Routes = [
  {
    path: '',
    component: MRecommendShopMessageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MRecommendShopMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MRecommendShopMessageDetailComponent,
    resolve: {
      mRecommendShopMessage: MRecommendShopMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendShopMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MRecommendShopMessageUpdateComponent,
    resolve: {
      mRecommendShopMessage: MRecommendShopMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendShopMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MRecommendShopMessageUpdateComponent,
    resolve: {
      mRecommendShopMessage: MRecommendShopMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendShopMessages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mRecommendShopMessagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MRecommendShopMessageDeletePopupComponent,
    resolve: {
      mRecommendShopMessage: MRecommendShopMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendShopMessages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
