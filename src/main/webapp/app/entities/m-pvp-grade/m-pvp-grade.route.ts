import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpGrade } from 'app/shared/model/m-pvp-grade.model';
import { MPvpGradeService } from './m-pvp-grade.service';
import { MPvpGradeComponent } from './m-pvp-grade.component';
import { MPvpGradeDetailComponent } from './m-pvp-grade-detail.component';
import { MPvpGradeUpdateComponent } from './m-pvp-grade-update.component';
import { MPvpGradeDeletePopupComponent } from './m-pvp-grade-delete-dialog.component';
import { IMPvpGrade } from 'app/shared/model/m-pvp-grade.model';

@Injectable({ providedIn: 'root' })
export class MPvpGradeResolve implements Resolve<IMPvpGrade> {
  constructor(private service: MPvpGradeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpGrade> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpGrade>) => response.ok),
        map((mPvpGrade: HttpResponse<MPvpGrade>) => mPvpGrade.body)
      );
    }
    return of(new MPvpGrade());
  }
}

export const mPvpGradeRoute: Routes = [
  {
    path: '',
    component: MPvpGradeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpGrades'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpGradeDetailComponent,
    resolve: {
      mPvpGrade: MPvpGradeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGrades'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpGradeUpdateComponent,
    resolve: {
      mPvpGrade: MPvpGradeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGrades'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpGradeUpdateComponent,
    resolve: {
      mPvpGrade: MPvpGradeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGrades'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpGradePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpGradeDeletePopupComponent,
    resolve: {
      mPvpGrade: MPvpGradeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGrades'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
