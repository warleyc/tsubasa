import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';
import { MGachaRenditionTradeSignService } from './m-gacha-rendition-trade-sign.service';
import { MGachaRenditionTradeSignComponent } from './m-gacha-rendition-trade-sign.component';
import { MGachaRenditionTradeSignDetailComponent } from './m-gacha-rendition-trade-sign-detail.component';
import { MGachaRenditionTradeSignUpdateComponent } from './m-gacha-rendition-trade-sign-update.component';
import { MGachaRenditionTradeSignDeletePopupComponent } from './m-gacha-rendition-trade-sign-delete-dialog.component';
import { IMGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTradeSignResolve implements Resolve<IMGachaRenditionTradeSign> {
  constructor(private service: MGachaRenditionTradeSignService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionTradeSign> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionTradeSign>) => response.ok),
        map((mGachaRenditionTradeSign: HttpResponse<MGachaRenditionTradeSign>) => mGachaRenditionTradeSign.body)
      );
    }
    return of(new MGachaRenditionTradeSign());
  }
}

export const mGachaRenditionTradeSignRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionTradeSignComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionTradeSigns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionTradeSignDetailComponent,
    resolve: {
      mGachaRenditionTradeSign: MGachaRenditionTradeSignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTradeSigns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionTradeSignUpdateComponent,
    resolve: {
      mGachaRenditionTradeSign: MGachaRenditionTradeSignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTradeSigns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionTradeSignUpdateComponent,
    resolve: {
      mGachaRenditionTradeSign: MGachaRenditionTradeSignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTradeSigns'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionTradeSignPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionTradeSignDeletePopupComponent,
    resolve: {
      mGachaRenditionTradeSign: MGachaRenditionTradeSignResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTradeSigns'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
