import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMatchEnvironment } from 'app/shared/model/m-match-environment.model';
import { MMatchEnvironmentService } from './m-match-environment.service';
import { MMatchEnvironmentComponent } from './m-match-environment.component';
import { MMatchEnvironmentDetailComponent } from './m-match-environment-detail.component';
import { MMatchEnvironmentUpdateComponent } from './m-match-environment-update.component';
import { MMatchEnvironmentDeletePopupComponent } from './m-match-environment-delete-dialog.component';
import { IMMatchEnvironment } from 'app/shared/model/m-match-environment.model';

@Injectable({ providedIn: 'root' })
export class MMatchEnvironmentResolve implements Resolve<IMMatchEnvironment> {
  constructor(private service: MMatchEnvironmentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMatchEnvironment> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMatchEnvironment>) => response.ok),
        map((mMatchEnvironment: HttpResponse<MMatchEnvironment>) => mMatchEnvironment.body)
      );
    }
    return of(new MMatchEnvironment());
  }
}

export const mMatchEnvironmentRoute: Routes = [
  {
    path: '',
    component: MMatchEnvironmentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMatchEnvironments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMatchEnvironmentDetailComponent,
    resolve: {
      mMatchEnvironment: MMatchEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchEnvironments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMatchEnvironmentUpdateComponent,
    resolve: {
      mMatchEnvironment: MMatchEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchEnvironments'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMatchEnvironmentUpdateComponent,
    resolve: {
      mMatchEnvironment: MMatchEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchEnvironments'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMatchEnvironmentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMatchEnvironmentDeletePopupComponent,
    resolve: {
      mMatchEnvironment: MMatchEnvironmentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchEnvironments'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
