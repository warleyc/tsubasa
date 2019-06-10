import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';
import { MApRecoveryItemService } from './m-ap-recovery-item.service';
import { MApRecoveryItemComponent } from './m-ap-recovery-item.component';
import { MApRecoveryItemDetailComponent } from './m-ap-recovery-item-detail.component';
import { MApRecoveryItemUpdateComponent } from './m-ap-recovery-item-update.component';
import { MApRecoveryItemDeletePopupComponent } from './m-ap-recovery-item-delete-dialog.component';
import { IMApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';

@Injectable({ providedIn: 'root' })
export class MApRecoveryItemResolve implements Resolve<IMApRecoveryItem> {
  constructor(private service: MApRecoveryItemService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMApRecoveryItem> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MApRecoveryItem>) => response.ok),
        map((mApRecoveryItem: HttpResponse<MApRecoveryItem>) => mApRecoveryItem.body)
      );
    }
    return of(new MApRecoveryItem());
  }
}

export const mApRecoveryItemRoute: Routes = [
  {
    path: '',
    component: MApRecoveryItemComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MApRecoveryItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MApRecoveryItemDetailComponent,
    resolve: {
      mApRecoveryItem: MApRecoveryItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MApRecoveryItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MApRecoveryItemUpdateComponent,
    resolve: {
      mApRecoveryItem: MApRecoveryItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MApRecoveryItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MApRecoveryItemUpdateComponent,
    resolve: {
      mApRecoveryItem: MApRecoveryItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MApRecoveryItems'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mApRecoveryItemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MApRecoveryItemDeletePopupComponent,
    resolve: {
      mApRecoveryItem: MApRecoveryItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MApRecoveryItems'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
