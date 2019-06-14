import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMission } from 'app/shared/model/m-mission.model';
import { MMissionService } from './m-mission.service';
import { MMissionComponent } from './m-mission.component';
import { MMissionDetailComponent } from './m-mission-detail.component';
import { MMissionUpdateComponent } from './m-mission-update.component';
import { MMissionDeletePopupComponent } from './m-mission-delete-dialog.component';
import { IMMission } from 'app/shared/model/m-mission.model';

@Injectable({ providedIn: 'root' })
export class MMissionResolve implements Resolve<IMMission> {
  constructor(private service: MMissionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMission> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMission>) => response.ok),
        map((mMission: HttpResponse<MMission>) => mMission.body)
      );
    }
    return of(new MMission());
  }
}

export const mMissionRoute: Routes = [
  {
    path: '',
    component: MMissionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMissions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMissionDetailComponent,
    resolve: {
      mMission: MMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMissionUpdateComponent,
    resolve: {
      mMission: MMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMissionUpdateComponent,
    resolve: {
      mMission: MMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMissionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMissionDeletePopupComponent,
    resolve: {
      mMission: MMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMissions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
