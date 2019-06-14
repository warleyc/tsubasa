import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildMission } from 'app/shared/model/m-guild-mission.model';
import { MGuildMissionService } from './m-guild-mission.service';
import { MGuildMissionComponent } from './m-guild-mission.component';
import { MGuildMissionDetailComponent } from './m-guild-mission-detail.component';
import { MGuildMissionUpdateComponent } from './m-guild-mission-update.component';
import { MGuildMissionDeletePopupComponent } from './m-guild-mission-delete-dialog.component';
import { IMGuildMission } from 'app/shared/model/m-guild-mission.model';

@Injectable({ providedIn: 'root' })
export class MGuildMissionResolve implements Resolve<IMGuildMission> {
  constructor(private service: MGuildMissionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildMission> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildMission>) => response.ok),
        map((mGuildMission: HttpResponse<MGuildMission>) => mGuildMission.body)
      );
    }
    return of(new MGuildMission());
  }
}

export const mGuildMissionRoute: Routes = [
  {
    path: '',
    component: MGuildMissionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildMissions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildMissionDetailComponent,
    resolve: {
      mGuildMission: MGuildMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildMissions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildMissionUpdateComponent,
    resolve: {
      mGuildMission: MGuildMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildMissions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildMissionUpdateComponent,
    resolve: {
      mGuildMission: MGuildMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildMissions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildMissionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildMissionDeletePopupComponent,
    resolve: {
      mGuildMission: MGuildMissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildMissions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
