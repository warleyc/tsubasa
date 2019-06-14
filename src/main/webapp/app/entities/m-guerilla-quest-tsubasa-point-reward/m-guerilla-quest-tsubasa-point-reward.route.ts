import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';
import { MGuerillaQuestTsubasaPointRewardService } from './m-guerilla-quest-tsubasa-point-reward.service';
import { MGuerillaQuestTsubasaPointRewardComponent } from './m-guerilla-quest-tsubasa-point-reward.component';
import { MGuerillaQuestTsubasaPointRewardDetailComponent } from './m-guerilla-quest-tsubasa-point-reward-detail.component';
import { MGuerillaQuestTsubasaPointRewardUpdateComponent } from './m-guerilla-quest-tsubasa-point-reward-update.component';
import { MGuerillaQuestTsubasaPointRewardDeletePopupComponent } from './m-guerilla-quest-tsubasa-point-reward-delete-dialog.component';
import { IMGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';

@Injectable({ providedIn: 'root' })
export class MGuerillaQuestTsubasaPointRewardResolve implements Resolve<IMGuerillaQuestTsubasaPointReward> {
  constructor(private service: MGuerillaQuestTsubasaPointRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuerillaQuestTsubasaPointReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuerillaQuestTsubasaPointReward>) => response.ok),
        map((mGuerillaQuestTsubasaPointReward: HttpResponse<MGuerillaQuestTsubasaPointReward>) => mGuerillaQuestTsubasaPointReward.body)
      );
    }
    return of(new MGuerillaQuestTsubasaPointReward());
  }
}

export const mGuerillaQuestTsubasaPointRewardRoute: Routes = [
  {
    path: '',
    component: MGuerillaQuestTsubasaPointRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuerillaQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuerillaQuestTsubasaPointRewardDetailComponent,
    resolve: {
      mGuerillaQuestTsubasaPointReward: MGuerillaQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuerillaQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mGuerillaQuestTsubasaPointReward: MGuerillaQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuerillaQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mGuerillaQuestTsubasaPointReward: MGuerillaQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuerillaQuestTsubasaPointRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuerillaQuestTsubasaPointRewardDeletePopupComponent,
    resolve: {
      mGuerillaQuestTsubasaPointReward: MGuerillaQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuerillaQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
