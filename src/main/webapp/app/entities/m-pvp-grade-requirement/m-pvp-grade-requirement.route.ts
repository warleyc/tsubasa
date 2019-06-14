import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';
import { MPvpGradeRequirementService } from './m-pvp-grade-requirement.service';
import { MPvpGradeRequirementComponent } from './m-pvp-grade-requirement.component';
import { MPvpGradeRequirementDetailComponent } from './m-pvp-grade-requirement-detail.component';
import { MPvpGradeRequirementUpdateComponent } from './m-pvp-grade-requirement-update.component';
import { MPvpGradeRequirementDeletePopupComponent } from './m-pvp-grade-requirement-delete-dialog.component';
import { IMPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';

@Injectable({ providedIn: 'root' })
export class MPvpGradeRequirementResolve implements Resolve<IMPvpGradeRequirement> {
  constructor(private service: MPvpGradeRequirementService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPvpGradeRequirement> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPvpGradeRequirement>) => response.ok),
        map((mPvpGradeRequirement: HttpResponse<MPvpGradeRequirement>) => mPvpGradeRequirement.body)
      );
    }
    return of(new MPvpGradeRequirement());
  }
}

export const mPvpGradeRequirementRoute: Routes = [
  {
    path: '',
    component: MPvpGradeRequirementComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPvpGradeRequirements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPvpGradeRequirementDetailComponent,
    resolve: {
      mPvpGradeRequirement: MPvpGradeRequirementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGradeRequirements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPvpGradeRequirementUpdateComponent,
    resolve: {
      mPvpGradeRequirement: MPvpGradeRequirementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGradeRequirements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPvpGradeRequirementUpdateComponent,
    resolve: {
      mPvpGradeRequirement: MPvpGradeRequirementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGradeRequirements'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPvpGradeRequirementPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPvpGradeRequirementDeletePopupComponent,
    resolve: {
      mPvpGradeRequirement: MPvpGradeRequirementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPvpGradeRequirements'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
