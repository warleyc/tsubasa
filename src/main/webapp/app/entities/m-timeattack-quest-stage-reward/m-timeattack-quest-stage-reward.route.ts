import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';
import { MTimeattackQuestStageRewardService } from './m-timeattack-quest-stage-reward.service';
import { MTimeattackQuestStageRewardComponent } from './m-timeattack-quest-stage-reward.component';
import { MTimeattackQuestStageRewardDetailComponent } from './m-timeattack-quest-stage-reward-detail.component';
import { MTimeattackQuestStageRewardUpdateComponent } from './m-timeattack-quest-stage-reward-update.component';
import { MTimeattackQuestStageRewardDeletePopupComponent } from './m-timeattack-quest-stage-reward-delete-dialog.component';
import { IMTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MTimeattackQuestStageRewardResolve implements Resolve<IMTimeattackQuestStageReward> {
  constructor(private service: MTimeattackQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTimeattackQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTimeattackQuestStageReward>) => response.ok),
        map((mTimeattackQuestStageReward: HttpResponse<MTimeattackQuestStageReward>) => mTimeattackQuestStageReward.body)
      );
    }
    return of(new MTimeattackQuestStageReward());
  }
}

export const mTimeattackQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MTimeattackQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTimeattackQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTimeattackQuestStageRewardDetailComponent,
    resolve: {
      mTimeattackQuestStageReward: MTimeattackQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTimeattackQuestStageRewardUpdateComponent,
    resolve: {
      mTimeattackQuestStageReward: MTimeattackQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTimeattackQuestStageRewardUpdateComponent,
    resolve: {
      mTimeattackQuestStageReward: MTimeattackQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTimeattackQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTimeattackQuestStageRewardDeletePopupComponent,
    resolve: {
      mTimeattackQuestStageReward: MTimeattackQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTimeattackQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
