import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MBadge } from 'app/shared/model/m-badge.model';
import { MBadgeService } from './m-badge.service';
import { MBadgeComponent } from './m-badge.component';
import { MBadgeDetailComponent } from './m-badge-detail.component';
import { MBadgeUpdateComponent } from './m-badge-update.component';
import { MBadgeDeletePopupComponent } from './m-badge-delete-dialog.component';
import { IMBadge } from 'app/shared/model/m-badge.model';

@Injectable({ providedIn: 'root' })
export class MBadgeResolve implements Resolve<IMBadge> {
  constructor(private service: MBadgeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMBadge> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MBadge>) => response.ok),
        map((mBadge: HttpResponse<MBadge>) => mBadge.body)
      );
    }
    return of(new MBadge());
  }
}

export const mBadgeRoute: Routes = [
  {
    path: '',
    component: MBadgeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MBadges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MBadgeDetailComponent,
    resolve: {
      mBadge: MBadgeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MBadges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MBadgeUpdateComponent,
    resolve: {
      mBadge: MBadgeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MBadges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MBadgeUpdateComponent,
    resolve: {
      mBadge: MBadgeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MBadges'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mBadgePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MBadgeDeletePopupComponent,
    resolve: {
      mBadge: MBadgeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MBadges'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
