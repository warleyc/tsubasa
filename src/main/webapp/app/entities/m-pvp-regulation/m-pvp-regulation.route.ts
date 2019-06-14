import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';
import { MPvpRegulationService } from './m-pvp-regulation.service';
import { MPvpRegulationComponent } from './m-pvp-regulation.component';
import { MPvpRegulationDetailComponent } from './m-pvp-regulation-detail.component';
import { MPvpRegulationUpdateComponent } from './m-pvp-regulation-update.component';
import { MPvpRegulationDeletePopupComponent } from './m-pvp-regulation-delete-dialog.component';
import { IMPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';

@Injectable({ providedIn: 'root' })
export class MPvpRegulationResolve implements Resolve<IMPvpRegulation> {
  constructor(private service: MPvpRegulationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpRegulation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpRegulation>) => response.ok),
        map((mPvpRegulation: HttpResponse<MPvpRegulation>) => mPvpRegulation.body)
      );
    }
    return of(new MPvpRegulation());
  }
}

export const mPvpRegulationRoute: Routes = [
  {
    path: '',
    component: MPvpRegulationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpRegulationDetailComponent,
    resolve: {
      mPvpRegulation: MPvpRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpRegulationUpdateComponent,
    resolve: {
      mPvpRegulation: MPvpRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpRegulationUpdateComponent,
    resolve: {
      mPvpRegulation: MPvpRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRegulations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpRegulationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpRegulationDeletePopupComponent,
    resolve: {
      mPvpRegulation: MPvpRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpRegulations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
