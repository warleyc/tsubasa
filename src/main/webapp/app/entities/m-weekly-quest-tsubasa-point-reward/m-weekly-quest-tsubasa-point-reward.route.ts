import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';
import { MWeeklyQuestTsubasaPointRewardService } from './m-weekly-quest-tsubasa-point-reward.service';
import { MWeeklyQuestTsubasaPointRewardComponent } from './m-weekly-quest-tsubasa-point-reward.component';
import { MWeeklyQuestTsubasaPointRewardDetailComponent } from './m-weekly-quest-tsubasa-point-reward-detail.component';
import { MWeeklyQuestTsubasaPointRewardUpdateComponent } from './m-weekly-quest-tsubasa-point-reward-update.component';
import { MWeeklyQuestTsubasaPointRewardDeletePopupComponent } from './m-weekly-quest-tsubasa-point-reward-delete-dialog.component';
import { IMWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';

@Injectable({ providedIn: 'root' })
export class MWeeklyQuestTsubasaPointRewardResolve implements Resolve<IMWeeklyQuestTsubasaPointReward> {
  constructor(private service: MWeeklyQuestTsubasaPointRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMWeeklyQuestTsubasaPointReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MWeeklyQuestTsubasaPointReward>) => response.ok),
        map((mWeeklyQuestTsubasaPointReward: HttpResponse<MWeeklyQuestTsubasaPointReward>) => mWeeklyQuestTsubasaPointReward.body)
      );
    }
    return of(new MWeeklyQuestTsubasaPointReward());
  }
}

export const mWeeklyQuestTsubasaPointRewardRoute: Routes = [
  {
    path: '',
    component: MWeeklyQuestTsubasaPointRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MWeeklyQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MWeeklyQuestTsubasaPointRewardDetailComponent,
    resolve: {
      mWeeklyQuestTsubasaPointReward: MWeeklyQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MWeeklyQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mWeeklyQuestTsubasaPointReward: MWeeklyQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MWeeklyQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mWeeklyQuestTsubasaPointReward: MWeeklyQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mWeeklyQuestTsubasaPointRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MWeeklyQuestTsubasaPointRewardDeletePopupComponent,
    resolve: {
      mWeeklyQuestTsubasaPointReward: MWeeklyQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MWeeklyQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
