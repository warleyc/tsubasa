import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';
import { MMarathonQuestStageRewardService } from './m-marathon-quest-stage-reward.service';
import { MMarathonQuestStageRewardComponent } from './m-marathon-quest-stage-reward.component';
import { MMarathonQuestStageRewardDetailComponent } from './m-marathon-quest-stage-reward-detail.component';
import { MMarathonQuestStageRewardUpdateComponent } from './m-marathon-quest-stage-reward-update.component';
import { MMarathonQuestStageRewardDeletePopupComponent } from './m-marathon-quest-stage-reward-delete-dialog.component';
import { IMMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';

@Injectable({ providedIn: 'root' })
export class MMarathonQuestStageRewardResolve implements Resolve<IMMarathonQuestStageReward> {
  constructor(private service: MMarathonQuestStageRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonQuestStageReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonQuestStageReward>) => response.ok),
        map((mMarathonQuestStageReward: HttpResponse<MMarathonQuestStageReward>) => mMarathonQuestStageReward.body)
      );
    }
    return of(new MMarathonQuestStageReward());
  }
}

export const mMarathonQuestStageRewardRoute: Routes = [
  {
    path: '',
    component: MMarathonQuestStageRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonQuestStageRewardDetailComponent,
    resolve: {
      mMarathonQuestStageReward: MMarathonQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonQuestStageRewardUpdateComponent,
    resolve: {
      mMarathonQuestStageReward: MMarathonQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonQuestStageRewardUpdateComponent,
    resolve: {
      mMarathonQuestStageReward: MMarathonQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStageRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonQuestStageRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonQuestStageRewardDeletePopupComponent,
    resolve: {
      mMarathonQuestStageReward: MMarathonQuestStageRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestStageRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
