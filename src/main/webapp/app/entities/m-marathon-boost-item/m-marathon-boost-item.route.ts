import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';
import { MMarathonBoostItemService } from './m-marathon-boost-item.service';
import { MMarathonBoostItemComponent } from './m-marathon-boost-item.component';
import { MMarathonBoostItemDetailComponent } from './m-marathon-boost-item-detail.component';
import { MMarathonBoostItemUpdateComponent } from './m-marathon-boost-item-update.component';
import { MMarathonBoostItemDeletePopupComponent } from './m-marathon-boost-item-delete-dialog.component';
import { IMMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';

@Injectable({ providedIn: 'root' })
export class MMarathonBoostItemResolve implements Resolve<IMMarathonBoostItem> {
  constructor(private service: MMarathonBoostItemService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonBoostItem> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonBoostItem>) => response.ok),
        map((mMarathonBoostItem: HttpResponse<MMarathonBoostItem>) => mMarathonBoostItem.body)
      );
    }
    return of(new MMarathonBoostItem());
  }
}

export const mMarathonBoostItemRoute: Routes = [
  {
    path: '',
    component: MMarathonBoostItemComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonBoostItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonBoostItemDetailComponent,
    resolve: {
      mMarathonBoostItem: MMarathonBoostItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonBoostItemUpdateComponent,
    resolve: {
      mMarathonBoostItem: MMarathonBoostItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonBoostItemUpdateComponent,
    resolve: {
      mMarathonBoostItem: MMarathonBoostItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostItems'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonBoostItemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonBoostItemDeletePopupComponent,
    resolve: {
      mMarathonBoostItem: MMarathonBoostItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostItems'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
