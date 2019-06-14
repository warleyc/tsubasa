import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';
import { MQuestTsubasaPointRewardService } from './m-quest-tsubasa-point-reward.service';
import { MQuestTsubasaPointRewardComponent } from './m-quest-tsubasa-point-reward.component';
import { MQuestTsubasaPointRewardDetailComponent } from './m-quest-tsubasa-point-reward-detail.component';
import { MQuestTsubasaPointRewardUpdateComponent } from './m-quest-tsubasa-point-reward-update.component';
import { MQuestTsubasaPointRewardDeletePopupComponent } from './m-quest-tsubasa-point-reward-delete-dialog.component';
import { IMQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';

@Injectable({ providedIn: 'root' })
export class MQuestTsubasaPointRewardResolve implements Resolve<IMQuestTsubasaPointReward> {
  constructor(private service: MQuestTsubasaPointRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestTsubasaPointReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestTsubasaPointReward>) => response.ok),
        map((mQuestTsubasaPointReward: HttpResponse<MQuestTsubasaPointReward>) => mQuestTsubasaPointReward.body)
      );
    }
    return of(new MQuestTsubasaPointReward());
  }
}

export const mQuestTsubasaPointRewardRoute: Routes = [
  {
    path: '',
    component: MQuestTsubasaPointRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestTsubasaPointRewardDetailComponent,
    resolve: {
      mQuestTsubasaPointReward: MQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mQuestTsubasaPointReward: MQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mQuestTsubasaPointReward: MQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestTsubasaPointRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestTsubasaPointRewardDeletePopupComponent,
    resolve: {
      mQuestTsubasaPointReward: MQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
