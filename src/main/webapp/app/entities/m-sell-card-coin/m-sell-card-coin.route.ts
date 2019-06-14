import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';
import { MSellCardCoinService } from './m-sell-card-coin.service';
import { MSellCardCoinComponent } from './m-sell-card-coin.component';
import { MSellCardCoinDetailComponent } from './m-sell-card-coin-detail.component';
import { MSellCardCoinUpdateComponent } from './m-sell-card-coin-update.component';
import { MSellCardCoinDeletePopupComponent } from './m-sell-card-coin-delete-dialog.component';
import { IMSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';

@Injectable({ providedIn: 'root' })
export class MSellCardCoinResolve implements Resolve<IMSellCardCoin> {
  constructor(private service: MSellCardCoinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSellCardCoin> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSellCardCoin>) => response.ok),
        map((mSellCardCoin: HttpResponse<MSellCardCoin>) => mSellCardCoin.body)
      );
    }
    return of(new MSellCardCoin());
  }
}

export const mSellCardCoinRoute: Routes = [
  {
    path: '',
    component: MSellCardCoinComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSellCardCoins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSellCardCoinDetailComponent,
    resolve: {
      mSellCardCoin: MSellCardCoinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardCoins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSellCardCoinUpdateComponent,
    resolve: {
      mSellCardCoin: MSellCardCoinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardCoins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSellCardCoinUpdateComponent,
    resolve: {
      mSellCardCoin: MSellCardCoinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardCoins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSellCardCoinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSellCardCoinDeletePopupComponent,
    resolve: {
      mSellCardCoin: MSellCardCoinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSellCardCoins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
