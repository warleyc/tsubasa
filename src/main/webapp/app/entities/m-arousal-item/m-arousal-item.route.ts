import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MArousalItem } from 'app/shared/model/m-arousal-item.model';
import { MArousalItemService } from './m-arousal-item.service';
import { MArousalItemComponent } from './m-arousal-item.component';
import { MArousalItemDetailComponent } from './m-arousal-item-detail.component';
import { MArousalItemUpdateComponent } from './m-arousal-item-update.component';
import { MArousalItemDeletePopupComponent } from './m-arousal-item-delete-dialog.component';
import { IMArousalItem } from 'app/shared/model/m-arousal-item.model';

@Injectable({ providedIn: 'root' })
export class MArousalItemResolve implements Resolve<IMArousalItem> {
  constructor(private service: MArousalItemService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMArousalItem> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MArousalItem>) => response.ok),
        map((mArousalItem: HttpResponse<MArousalItem>) => mArousalItem.body)
      );
    }
    return of(new MArousalItem());
  }
}

export const mArousalItemRoute: Routes = [
  {
    path: '',
    component: MArousalItemComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MArousalItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MArousalItemDetailComponent,
    resolve: {
      mArousalItem: MArousalItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MArousalItemUpdateComponent,
    resolve: {
      mArousalItem: MArousalItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalItems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MArousalItemUpdateComponent,
    resolve: {
      mArousalItem: MArousalItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalItems'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mArousalItemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MArousalItemDeletePopupComponent,
    resolve: {
      mArousalItem: MArousalItemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousalItems'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
