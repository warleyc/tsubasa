import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';
import { MMarathonBoostScheduleService } from './m-marathon-boost-schedule.service';
import { MMarathonBoostScheduleComponent } from './m-marathon-boost-schedule.component';
import { MMarathonBoostScheduleDetailComponent } from './m-marathon-boost-schedule-detail.component';
import { MMarathonBoostScheduleUpdateComponent } from './m-marathon-boost-schedule-update.component';
import { MMarathonBoostScheduleDeletePopupComponent } from './m-marathon-boost-schedule-delete-dialog.component';
import { IMMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';

@Injectable({ providedIn: 'root' })
export class MMarathonBoostScheduleResolve implements Resolve<IMMarathonBoostSchedule> {
  constructor(private service: MMarathonBoostScheduleService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonBoostSchedule> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonBoostSchedule>) => response.ok),
        map((mMarathonBoostSchedule: HttpResponse<MMarathonBoostSchedule>) => mMarathonBoostSchedule.body)
      );
    }
    return of(new MMarathonBoostSchedule());
  }
}

export const mMarathonBoostScheduleRoute: Routes = [
  {
    path: '',
    component: MMarathonBoostScheduleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonBoostSchedules'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonBoostScheduleDetailComponent,
    resolve: {
      mMarathonBoostSchedule: MMarathonBoostScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostSchedules'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonBoostScheduleUpdateComponent,
    resolve: {
      mMarathonBoostSchedule: MMarathonBoostScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostSchedules'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonBoostScheduleUpdateComponent,
    resolve: {
      mMarathonBoostSchedule: MMarathonBoostScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostSchedules'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonBoostSchedulePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonBoostScheduleDeletePopupComponent,
    resolve: {
      mMarathonBoostSchedule: MMarathonBoostScheduleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonBoostSchedules'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
