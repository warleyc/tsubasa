import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';
import { MCutShootOrbitService } from './m-cut-shoot-orbit.service';
import { MCutShootOrbitComponent } from './m-cut-shoot-orbit.component';
import { MCutShootOrbitDetailComponent } from './m-cut-shoot-orbit-detail.component';
import { MCutShootOrbitUpdateComponent } from './m-cut-shoot-orbit-update.component';
import { MCutShootOrbitDeletePopupComponent } from './m-cut-shoot-orbit-delete-dialog.component';
import { IMCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';

@Injectable({ providedIn: 'root' })
export class MCutShootOrbitResolve implements Resolve<IMCutShootOrbit> {
  constructor(private service: MCutShootOrbitService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCutShootOrbit> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCutShootOrbit>) => response.ok),
        map((mCutShootOrbit: HttpResponse<MCutShootOrbit>) => mCutShootOrbit.body)
      );
    }
    return of(new MCutShootOrbit());
  }
}

export const mCutShootOrbitRoute: Routes = [
  {
    path: '',
    component: MCutShootOrbitComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCutShootOrbits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCutShootOrbitDetailComponent,
    resolve: {
      mCutShootOrbit: MCutShootOrbitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutShootOrbits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCutShootOrbitUpdateComponent,
    resolve: {
      mCutShootOrbit: MCutShootOrbitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutShootOrbits'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCutShootOrbitUpdateComponent,
    resolve: {
      mCutShootOrbit: MCutShootOrbitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutShootOrbits'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCutShootOrbitPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCutShootOrbitDeletePopupComponent,
    resolve: {
      mCutShootOrbit: MCutShootOrbitResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCutShootOrbits'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
