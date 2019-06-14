import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MShoot } from 'app/shared/model/m-shoot.model';
import { MShootService } from './m-shoot.service';
import { MShootComponent } from './m-shoot.component';
import { MShootDetailComponent } from './m-shoot-detail.component';
import { MShootUpdateComponent } from './m-shoot-update.component';
import { MShootDeletePopupComponent } from './m-shoot-delete-dialog.component';
import { IMShoot } from 'app/shared/model/m-shoot.model';

@Injectable({ providedIn: 'root' })
export class MShootResolve implements Resolve<IMShoot> {
  constructor(private service: MShootService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMShoot> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MShoot>) => response.ok),
        map((mShoot: HttpResponse<MShoot>) => mShoot.body)
      );
    }
    return of(new MShoot());
  }
}

export const mShootRoute: Routes = [
  {
    path: '',
    component: MShootComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MShoots'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MShootDetailComponent,
    resolve: {
      mShoot: MShootResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MShoots'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MShootUpdateComponent,
    resolve: {
      mShoot: MShootResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MShoots'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MShootUpdateComponent,
    resolve: {
      mShoot: MShootResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MShoots'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mShootPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MShootDeletePopupComponent,
    resolve: {
      mShoot: MShootResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MShoots'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
