import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MExtensionSale } from 'app/shared/model/m-extension-sale.model';
import { MExtensionSaleService } from './m-extension-sale.service';
import { MExtensionSaleComponent } from './m-extension-sale.component';
import { MExtensionSaleDetailComponent } from './m-extension-sale-detail.component';
import { MExtensionSaleUpdateComponent } from './m-extension-sale-update.component';
import { MExtensionSaleDeletePopupComponent } from './m-extension-sale-delete-dialog.component';
import { IMExtensionSale } from 'app/shared/model/m-extension-sale.model';

@Injectable({ providedIn: 'root' })
export class MExtensionSaleResolve implements Resolve<IMExtensionSale> {
  constructor(private service: MExtensionSaleService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMExtensionSale> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MExtensionSale>) => response.ok),
        map((mExtensionSale: HttpResponse<MExtensionSale>) => mExtensionSale.body)
      );
    }
    return of(new MExtensionSale());
  }
}

export const mExtensionSaleRoute: Routes = [
  {
    path: '',
    component: MExtensionSaleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MExtensionSales'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MExtensionSaleDetailComponent,
    resolve: {
      mExtensionSale: MExtensionSaleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtensionSales'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MExtensionSaleUpdateComponent,
    resolve: {
      mExtensionSale: MExtensionSaleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtensionSales'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MExtensionSaleUpdateComponent,
    resolve: {
      mExtensionSale: MExtensionSaleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtensionSales'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mExtensionSalePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MExtensionSaleDeletePopupComponent,
    resolve: {
      mExtensionSale: MExtensionSaleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtensionSales'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
