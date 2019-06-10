import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';
import { MAdventQuestStageRewardService } from './m-advent-quest-stage-reward.service';
import { MAdventQuestStageRewardComponent } from './m-advent-quest-stage-reward.component';
import { MAdventQuestStageRewardDetailComponent } from './m-advent-quest-stage-reward-detail.component';
import { MAdventQuestStageRewardUpdateComponent } from './m-advent-quest-stage-reward-update.component';
import { MAdventQuestStageRewardDeletePopupComponent } from './m-advent-quest-stage-reward-delete-dialog.component';
import { IMAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MAdventQuestStageRewardResolve implements Resolve<IMAdventQuestStageReward> {
  constructor(private service: MAdventQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAdventQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAdventQuestStageReward>) => response.ok),
        map((mAdventQuestStageReward: HttpResponse<MAdventQuestStageReward>) => mAdventQuestStageReward.body)
      );
    }
    return of(new MAdventQuestStageReward());
  }
}

export const mAdventQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MAdventQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAdventQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAdventQuestStageRewardDetailComponent,
    resolve: {
      mAdventQuestStageReward: MAdventQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAdventQuestStageRewardUpdateComponent,
    resolve: {
      mAdventQuestStageReward: MAdventQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAdventQuestStageRewardUpdateComponent,
    resolve: {
      mAdventQuestStageReward: MAdventQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAdventQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAdventQuestStageRewardDeletePopupComponent,
    resolve: {
      mAdventQuestStageReward: MAdventQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
