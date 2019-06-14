import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';
import { MLoginBonusIncentiveService } from './m-login-bonus-incentive.service';
import { MLoginBonusIncentiveComponent } from './m-login-bonus-incentive.component';
import { MLoginBonusIncentiveDetailComponent } from './m-login-bonus-incentive-detail.component';
import { MLoginBonusIncentiveUpdateComponent } from './m-login-bonus-incentive-update.component';
import { MLoginBonusIncentiveDeletePopupComponent } from './m-login-bonus-incentive-delete-dialog.component';
import { IMLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';

@Injectable({ providedIn: 'root' })
export class MLoginBonusIncentiveResolve implements Resolve<IMLoginBonusIncentive> {
  constructor(private service: MLoginBonusIncentiveService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLoginBonusIncentive> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLoginBonusIncentive>) => response.ok),
        map((mLoginBonusIncentive: HttpResponse<MLoginBonusIncentive>) => mLoginBonusIncentive.body)
      );
    }
    return of(new MLoginBonusIncentive());
  }
}

export const mLoginBonusIncentiveRoute: Routes = [
  {
    path: '',
    component: MLoginBonusIncentiveComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLoginBonusIncentives'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLoginBonusIncentiveDetailComponent,
    resolve: {
      mLoginBonusIncentive: MLoginBonusIncentiveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusIncentives'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLoginBonusIncentiveUpdateComponent,
    resolve: {
      mLoginBonusIncentive: MLoginBonusIncentiveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusIncentives'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLoginBonusIncentiveUpdateComponent,
    resolve: {
      mLoginBonusIncentive: MLoginBonusIncentiveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusIncentives'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLoginBonusIncentivePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLoginBonusIncentiveDeletePopupComponent,
    resolve: {
      mLoginBonusIncentive: MLoginBonusIncentiveResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusIncentives'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
