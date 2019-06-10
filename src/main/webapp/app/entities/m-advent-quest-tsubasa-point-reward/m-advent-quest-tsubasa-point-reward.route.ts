import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';
import { MAdventQuestTsubasaPointRewardService } from './m-advent-quest-tsubasa-point-reward.service';
import { MAdventQuestTsubasaPointRewardComponent } from './m-advent-quest-tsubasa-point-reward.component';
import { MAdventQuestTsubasaPointRewardDetailComponent } from './m-advent-quest-tsubasa-point-reward-detail.component';
import { MAdventQuestTsubasaPointRewardUpdateComponent } from './m-advent-quest-tsubasa-point-reward-update.component';
import { MAdventQuestTsubasaPointRewardDeletePopupComponent } from './m-advent-quest-tsubasa-point-reward-delete-dialog.component';
import { IMAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';

@Injectable({ providedIn: 'root' })
export class MAdventQuestTsubasaPointRewardResolve implements Resolve<IMAdventQuestTsubasaPointReward> {
  constructor(private service: MAdventQuestTsubasaPointRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAdventQuestTsubasaPointReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAdventQuestTsubasaPointReward>) => response.ok),
        map((mAdventQuestTsubasaPointReward: HttpResponse<MAdventQuestTsubasaPointReward>) => mAdventQuestTsubasaPointReward.body)
      );
    }
    return of(new MAdventQuestTsubasaPointReward());
  }
}

export const mAdventQuestTsubasaPointRewardRoute: Routes = [
  {
    path: '',
    component: MAdventQuestTsubasaPointRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAdventQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAdventQuestTsubasaPointRewardDetailComponent,
    resolve: {
      mAdventQuestTsubasaPointReward: MAdventQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAdventQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mAdventQuestTsubasaPointReward: MAdventQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAdventQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mAdventQuestTsubasaPointReward: MAdventQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAdventQuestTsubasaPointRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAdventQuestTsubasaPointRewardDeletePopupComponent,
    resolve: {
      mAdventQuestTsubasaPointReward: MAdventQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAdventQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
