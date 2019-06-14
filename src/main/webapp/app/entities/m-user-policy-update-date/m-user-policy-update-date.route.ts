import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';
import { MUserPolicyUpdateDateService } from './m-user-policy-update-date.service';
import { MUserPolicyUpdateDateComponent } from './m-user-policy-update-date.component';
import { MUserPolicyUpdateDateDetailComponent } from './m-user-policy-update-date-detail.component';
import { MUserPolicyUpdateDateUpdateComponent } from './m-user-policy-update-date-update.component';
import { MUserPolicyUpdateDateDeletePopupComponent } from './m-user-policy-update-date-delete-dialog.component';
import { IMUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';

@Injectable({ providedIn: 'root' })
export class MUserPolicyUpdateDateResolve implements Resolve<IMUserPolicyUpdateDate> {
  constructor(private service: MUserPolicyUpdateDateService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMUserPolicyUpdateDate> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MUserPolicyUpdateDate>) => response.ok),
        map((mUserPolicyUpdateDate: HttpResponse<MUserPolicyUpdateDate>) => mUserPolicyUpdateDate.body)
      );
    }
    return of(new MUserPolicyUpdateDate());
  }
}

export const mUserPolicyUpdateDateRoute: Routes = [
  {
    path: '',
    component: MUserPolicyUpdateDateComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MUserPolicyUpdateDates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MUserPolicyUpdateDateDetailComponent,
    resolve: {
      mUserPolicyUpdateDate: MUserPolicyUpdateDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserPolicyUpdateDates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MUserPolicyUpdateDateUpdateComponent,
    resolve: {
      mUserPolicyUpdateDate: MUserPolicyUpdateDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserPolicyUpdateDates'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MUserPolicyUpdateDateUpdateComponent,
    resolve: {
      mUserPolicyUpdateDate: MUserPolicyUpdateDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserPolicyUpdateDates'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mUserPolicyUpdateDatePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MUserPolicyUpdateDateDeletePopupComponent,
    resolve: {
      mUserPolicyUpdateDate: MUserPolicyUpdateDateResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUserPolicyUpdateDates'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
