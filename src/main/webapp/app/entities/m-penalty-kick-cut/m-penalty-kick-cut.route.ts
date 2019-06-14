import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';
import { MPenaltyKickCutService } from './m-penalty-kick-cut.service';
import { MPenaltyKickCutComponent } from './m-penalty-kick-cut.component';
import { MPenaltyKickCutDetailComponent } from './m-penalty-kick-cut-detail.component';
import { MPenaltyKickCutUpdateComponent } from './m-penalty-kick-cut-update.component';
import { MPenaltyKickCutDeletePopupComponent } from './m-penalty-kick-cut-delete-dialog.component';
import { IMPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';

@Injectable({ providedIn: 'root' })
export class MPenaltyKickCutResolve implements Resolve<IMPenaltyKickCut> {
  constructor(private service: MPenaltyKickCutService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPenaltyKickCut> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPenaltyKickCut>) => response.ok),
        map((mPenaltyKickCut: HttpResponse<MPenaltyKickCut>) => mPenaltyKickCut.body)
      );
    }
    return of(new MPenaltyKickCut());
  }
}

export const mPenaltyKickCutRoute: Routes = [
  {
    path: '',
    component: MPenaltyKickCutComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPenaltyKickCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPenaltyKickCutDetailComponent,
    resolve: {
      mPenaltyKickCut: MPenaltyKickCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPenaltyKickCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPenaltyKickCutUpdateComponent,
    resolve: {
      mPenaltyKickCut: MPenaltyKickCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPenaltyKickCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPenaltyKickCutUpdateComponent,
    resolve: {
      mPenaltyKickCut: MPenaltyKickCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPenaltyKickCuts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPenaltyKickCutPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPenaltyKickCutDeletePopupComponent,
    resolve: {
      mPenaltyKickCut: MPenaltyKickCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPenaltyKickCuts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
