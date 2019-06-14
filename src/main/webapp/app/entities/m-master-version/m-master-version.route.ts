import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMasterVersion } from 'app/shared/model/m-master-version.model';
import { MMasterVersionService } from './m-master-version.service';
import { MMasterVersionComponent } from './m-master-version.component';
import { MMasterVersionDetailComponent } from './m-master-version-detail.component';
import { MMasterVersionUpdateComponent } from './m-master-version-update.component';
import { MMasterVersionDeletePopupComponent } from './m-master-version-delete-dialog.component';
import { IMMasterVersion } from 'app/shared/model/m-master-version.model';

@Injectable({ providedIn: 'root' })
export class MMasterVersionResolve implements Resolve<IMMasterVersion> {
  constructor(private service: MMasterVersionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMasterVersion> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMasterVersion>) => response.ok),
        map((mMasterVersion: HttpResponse<MMasterVersion>) => mMasterVersion.body)
      );
    }
    return of(new MMasterVersion());
  }
}

export const mMasterVersionRoute: Routes = [
  {
    path: '',
    component: MMasterVersionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMasterVersions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMasterVersionDetailComponent,
    resolve: {
      mMasterVersion: MMasterVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMasterVersions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMasterVersionUpdateComponent,
    resolve: {
      mMasterVersion: MMasterVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMasterVersions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMasterVersionUpdateComponent,
    resolve: {
      mMasterVersion: MMasterVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMasterVersions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMasterVersionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMasterVersionDeletePopupComponent,
    resolve: {
      mMasterVersion: MMasterVersionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMasterVersions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
