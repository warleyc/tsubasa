import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';
import { MMarathonQuestTsubasaPointRewardService } from './m-marathon-quest-tsubasa-point-reward.service';
import { MMarathonQuestTsubasaPointRewardComponent } from './m-marathon-quest-tsubasa-point-reward.component';
import { MMarathonQuestTsubasaPointRewardDetailComponent } from './m-marathon-quest-tsubasa-point-reward-detail.component';
import { MMarathonQuestTsubasaPointRewardUpdateComponent } from './m-marathon-quest-tsubasa-point-reward-update.component';
import { MMarathonQuestTsubasaPointRewardDeletePopupComponent } from './m-marathon-quest-tsubasa-point-reward-delete-dialog.component';
import { IMMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';

@Injectable({ providedIn: 'root' })
export class MMarathonQuestTsubasaPointRewardResolve implements Resolve<IMMarathonQuestTsubasaPointReward> {
  constructor(private service: MMarathonQuestTsubasaPointRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonQuestTsubasaPointReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonQuestTsubasaPointReward>) => response.ok),
        map((mMarathonQuestTsubasaPointReward: HttpResponse<MMarathonQuestTsubasaPointReward>) => mMarathonQuestTsubasaPointReward.body)
      );
    }
    return of(new MMarathonQuestTsubasaPointReward());
  }
}

export const mMarathonQuestTsubasaPointRewardRoute: Routes = [
  {
    path: '',
    component: MMarathonQuestTsubasaPointRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonQuestTsubasaPointRewardDetailComponent,
    resolve: {
      mMarathonQuestTsubasaPointReward: MMarathonQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mMarathonQuestTsubasaPointReward: MMarathonQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mMarathonQuestTsubasaPointReward: MMarathonQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonQuestTsubasaPointRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonQuestTsubasaPointRewardDeletePopupComponent,
    resolve: {
      mMarathonQuestTsubasaPointReward: MMarathonQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
