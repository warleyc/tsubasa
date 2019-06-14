import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';
import { MQuestSpecialRewardService } from './m-quest-special-reward.service';
import { MQuestSpecialRewardComponent } from './m-quest-special-reward.component';
import { MQuestSpecialRewardDetailComponent } from './m-quest-special-reward-detail.component';
import { MQuestSpecialRewardUpdateComponent } from './m-quest-special-reward-update.component';
import { MQuestSpecialRewardDeletePopupComponent } from './m-quest-special-reward-delete-dialog.component';
import { IMQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';

@Injectable({ providedIn: 'root' })
export class MQuestSpecialRewardResolve implements Resolve<IMQuestSpecialReward> {
  constructor(private service: MQuestSpecialRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestSpecialReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestSpecialReward>) => response.ok),
        map((mQuestSpecialReward: HttpResponse<MQuestSpecialReward>) => mQuestSpecialReward.body)
      );
    }
    return of(new MQuestSpecialReward());
  }
}

export const mQuestSpecialRewardRoute: Routes = [
  {
    path: '',
    component: MQuestSpecialRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestSpecialRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestSpecialRewardDetailComponent,
    resolve: {
      mQuestSpecialReward: MQuestSpecialRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestSpecialRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestSpecialRewardUpdateComponent,
    resolve: {
      mQuestSpecialReward: MQuestSpecialRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestSpecialRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestSpecialRewardUpdateComponent,
    resolve: {
      mQuestSpecialReward: MQuestSpecialRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestSpecialRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestSpecialRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestSpecialRewardDeletePopupComponent,
    resolve: {
      mQuestSpecialReward: MQuestSpecialRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestSpecialRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
