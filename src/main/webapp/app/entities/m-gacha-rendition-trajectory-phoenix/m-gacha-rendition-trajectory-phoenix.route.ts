import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';
import { MGachaRenditionTrajectoryPhoenixService } from './m-gacha-rendition-trajectory-phoenix.service';
import { MGachaRenditionTrajectoryPhoenixComponent } from './m-gacha-rendition-trajectory-phoenix.component';
import { MGachaRenditionTrajectoryPhoenixDetailComponent } from './m-gacha-rendition-trajectory-phoenix-detail.component';
import { MGachaRenditionTrajectoryPhoenixUpdateComponent } from './m-gacha-rendition-trajectory-phoenix-update.component';
import { MGachaRenditionTrajectoryPhoenixDeletePopupComponent } from './m-gacha-rendition-trajectory-phoenix-delete-dialog.component';
import { IMGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTrajectoryPhoenixResolve implements Resolve<IMGachaRenditionTrajectoryPhoenix> {
  constructor(private service: MGachaRenditionTrajectoryPhoenixService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionTrajectoryPhoenix> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionTrajectoryPhoenix>) => response.ok),
        map((mGachaRenditionTrajectoryPhoenix: HttpResponse<MGachaRenditionTrajectoryPhoenix>) => mGachaRenditionTrajectoryPhoenix.body)
      );
    }
    return of(new MGachaRenditionTrajectoryPhoenix());
  }
}

export const mGachaRenditionTrajectoryPhoenixRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionTrajectoryPhoenixComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionTrajectoryPhoenixes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionTrajectoryPhoenixDetailComponent,
    resolve: {
      mGachaRenditionTrajectoryPhoenix: MGachaRenditionTrajectoryPhoenixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryPhoenixes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionTrajectoryPhoenixUpdateComponent,
    resolve: {
      mGachaRenditionTrajectoryPhoenix: MGachaRenditionTrajectoryPhoenixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryPhoenixes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionTrajectoryPhoenixUpdateComponent,
    resolve: {
      mGachaRenditionTrajectoryPhoenix: MGachaRenditionTrajectoryPhoenixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryPhoenixes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionTrajectoryPhoenixPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionTrajectoryPhoenixDeletePopupComponent,
    resolve: {
      mGachaRenditionTrajectoryPhoenix: MGachaRenditionTrajectoryPhoenixResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryPhoenixes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
