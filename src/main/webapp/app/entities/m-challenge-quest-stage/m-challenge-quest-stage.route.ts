import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';
import { MChallengeQuestStageService } from './m-challenge-quest-stage.service';
import { MChallengeQuestStageComponent } from './m-challenge-quest-stage.component';
import { MChallengeQuestStageDetailComponent } from './m-challenge-quest-stage-detail.component';
import { MChallengeQuestStageUpdateComponent } from './m-challenge-quest-stage-update.component';
import { MChallengeQuestStageDeletePopupComponent } from './m-challenge-quest-stage-delete-dialog.component';
import { IMChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MChallengeQuestStageResolve implements Resolve<IMChallengeQuestStage> {
  constructor(private service: MChallengeQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMChallengeQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MChallengeQuestStage>) => response.ok),
        map((mChallengeQuestStage: HttpResponse<MChallengeQuestStage>) => mChallengeQuestStage.body)
      );
    }
    return of(new MChallengeQuestStage());
  }
}

export const mChallengeQuestStageRoute: Routes = [
  {
    path: '',
    component: MChallengeQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MChallengeQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MChallengeQuestStageDetailComponent,
    resolve: {
      mChallengeQuestStage: MChallengeQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MChallengeQuestStageUpdateComponent,
    resolve: {
      mChallengeQuestStage: MChallengeQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MChallengeQuestStageUpdateComponent,
    resolve: {
      mChallengeQuestStage: MChallengeQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mChallengeQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MChallengeQuestStageDeletePopupComponent,
    resolve: {
      mChallengeQuestStage: MChallengeQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
