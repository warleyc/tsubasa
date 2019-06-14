import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';
import { MQuestCoopRewardService } from './m-quest-coop-reward.service';
import { MQuestCoopRewardComponent } from './m-quest-coop-reward.component';
import { MQuestCoopRewardDetailComponent } from './m-quest-coop-reward-detail.component';
import { MQuestCoopRewardUpdateComponent } from './m-quest-coop-reward-update.component';
import { MQuestCoopRewardDeletePopupComponent } from './m-quest-coop-reward-delete-dialog.component';
import { IMQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';

@Injectable({ providedIn: 'root' })
export class MQuestCoopRewardResolve implements Resolve<IMQuestCoopReward> {
  constructor(private service: MQuestCoopRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestCoopReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestCoopReward>) => response.ok),
        map((mQuestCoopReward: HttpResponse<MQuestCoopReward>) => mQuestCoopReward.body)
      );
    }
    return of(new MQuestCoopReward());
  }
}

export const mQuestCoopRewardRoute: Routes = [
  {
    path: '',
    component: MQuestCoopRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestCoopRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestCoopRewardDetailComponent,
    resolve: {
      mQuestCoopReward: MQuestCoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoopRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestCoopRewardUpdateComponent,
    resolve: {
      mQuestCoopReward: MQuestCoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoopRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestCoopRewardUpdateComponent,
    resolve: {
      mQuestCoopReward: MQuestCoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoopRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestCoopRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestCoopRewardDeletePopupComponent,
    resolve: {
      mQuestCoopReward: MQuestCoopRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestCoopRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
