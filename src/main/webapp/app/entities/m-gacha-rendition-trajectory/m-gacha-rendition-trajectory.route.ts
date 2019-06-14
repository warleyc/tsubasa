import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';
import { MGachaRenditionTrajectoryService } from './m-gacha-rendition-trajectory.service';
import { MGachaRenditionTrajectoryComponent } from './m-gacha-rendition-trajectory.component';
import { MGachaRenditionTrajectoryDetailComponent } from './m-gacha-rendition-trajectory-detail.component';
import { MGachaRenditionTrajectoryUpdateComponent } from './m-gacha-rendition-trajectory-update.component';
import { MGachaRenditionTrajectoryDeletePopupComponent } from './m-gacha-rendition-trajectory-delete-dialog.component';
import { IMGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTrajectoryResolve implements Resolve<IMGachaRenditionTrajectory> {
  constructor(private service: MGachaRenditionTrajectoryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionTrajectory> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionTrajectory>) => response.ok),
        map((mGachaRenditionTrajectory: HttpResponse<MGachaRenditionTrajectory>) => mGachaRenditionTrajectory.body)
      );
    }
    return of(new MGachaRenditionTrajectory());
  }
}

export const mGachaRenditionTrajectoryRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionTrajectoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionTrajectories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionTrajectoryDetailComponent,
    resolve: {
      mGachaRenditionTrajectory: MGachaRenditionTrajectoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionTrajectoryUpdateComponent,
    resolve: {
      mGachaRenditionTrajectory: MGachaRenditionTrajectoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionTrajectoryUpdateComponent,
    resolve: {
      mGachaRenditionTrajectory: MGachaRenditionTrajectoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectories'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionTrajectoryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionTrajectoryDeletePopupComponent,
    resolve: {
      mGachaRenditionTrajectory: MGachaRenditionTrajectoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectories'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
