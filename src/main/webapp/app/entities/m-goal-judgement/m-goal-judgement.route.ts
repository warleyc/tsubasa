import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGoalJudgement } from 'app/shared/model/m-goal-judgement.model';
import { MGoalJudgementService } from './m-goal-judgement.service';
import { MGoalJudgementComponent } from './m-goal-judgement.component';
import { MGoalJudgementDetailComponent } from './m-goal-judgement-detail.component';
import { MGoalJudgementUpdateComponent } from './m-goal-judgement-update.component';
import { MGoalJudgementDeletePopupComponent } from './m-goal-judgement-delete-dialog.component';
import { IMGoalJudgement } from 'app/shared/model/m-goal-judgement.model';

@Injectable({ providedIn: 'root' })
export class MGoalJudgementResolve implements Resolve<IMGoalJudgement> {
  constructor(private service: MGoalJudgementService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGoalJudgement> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGoalJudgement>) => response.ok),
        map((mGoalJudgement: HttpResponse<MGoalJudgement>) => mGoalJudgement.body)
      );
    }
    return of(new MGoalJudgement());
  }
}

export const mGoalJudgementRoute: Routes = [
  {
    path: '',
    component: MGoalJudgementComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGoalJudgements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGoalJudgementDetailComponent,
    resolve: {
      mGoalJudgement: MGoalJudgementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalJudgements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGoalJudgementUpdateComponent,
    resolve: {
      mGoalJudgement: MGoalJudgementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalJudgements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGoalJudgementUpdateComponent,
    resolve: {
      mGoalJudgement: MGoalJudgementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalJudgements'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGoalJudgementPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGoalJudgementDeletePopupComponent,
    resolve: {
      mGoalJudgement: MGoalJudgementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalJudgements'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
