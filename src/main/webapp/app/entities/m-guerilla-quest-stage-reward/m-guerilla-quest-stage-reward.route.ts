import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';
import { MGuerillaQuestStageRewardService } from './m-guerilla-quest-stage-reward.service';
import { MGuerillaQuestStageRewardComponent } from './m-guerilla-quest-stage-reward.component';
import { MGuerillaQuestStageRewardDetailComponent } from './m-guerilla-quest-stage-reward-detail.component';
import { MGuerillaQuestStageRewardUpdateComponent } from './m-guerilla-quest-stage-reward-update.component';
import { MGuerillaQuestStageRewardDeletePopupComponent } from './m-guerilla-quest-stage-reward-delete-dialog.component';
import { IMGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestStageRewardResolve implements Resolve<IMGuerillaQuestStageReward> {
  constructor(private service: MGuerillaQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuerillaQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuerillaQuestStageReward>) => response.ok),
        map((mGuerillaQuestStageReward: HttpResponse<MGuerillaQuestStageReward>) => mGuerillaQuestStageReward.body)
      );
    }
    return of(new MGuerillaQuestStageReward());
  }
}

export const mGuerillaQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MGuerillaQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuerillaQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuerillaQuestStageRewardDetailComponent,
    resolve: {
      mGuerillaQuestStageReward: MGuerillaQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuerillaQuestStageRewardUpdateComponent,
    resolve: {
      mGuerillaQuestStageReward: MGuerillaQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuerillaQuestStageRewardUpdateComponent,
    resolve: {
      mGuerillaQuestStageReward: MGuerillaQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuerillaQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuerillaQuestStageRewardDeletePopupComponent,
    resolve: {
      mGuerillaQuestStageReward: MGuerillaQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
