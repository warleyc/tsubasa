import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';
import { MLeagueAffiliateRewardService } from './m-league-affiliate-reward.service';
import { MLeagueAffiliateRewardComponent } from './m-league-affiliate-reward.component';
import { MLeagueAffiliateRewardDetailComponent } from './m-league-affiliate-reward-detail.component';
import { MLeagueAffiliateRewardUpdateComponent } from './m-league-affiliate-reward-update.component';
import { MLeagueAffiliateRewardDeletePopupComponent } from './m-league-affiliate-reward-delete-dialog.component';
import { IMLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';

@Injectable({ providedIn: 'root' })
export class MLeagueAffiliateRewardResolve implements Resolve<IMLeagueAffiliateReward> {
  constructor(private service: MLeagueAffiliateRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLeagueAffiliateReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLeagueAffiliateReward>) => response.ok),
        map((mLeagueAffiliateReward: HttpResponse<MLeagueAffiliateReward>) => mLeagueAffiliateReward.body)
      );
    }
    return of(new MLeagueAffiliateReward());
  }
}

export const mLeagueAffiliateRewardRoute: Routes = [
  {
    path: '',
    component: MLeagueAffiliateRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLeagueAffiliateRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLeagueAffiliateRewardDetailComponent,
    resolve: {
      mLeagueAffiliateReward: MLeagueAffiliateRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueAffiliateRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLeagueAffiliateRewardUpdateComponent,
    resolve: {
      mLeagueAffiliateReward: MLeagueAffiliateRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueAffiliateRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLeagueAffiliateRewardUpdateComponent,
    resolve: {
      mLeagueAffiliateReward: MLeagueAffiliateRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueAffiliateRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLeagueAffiliateRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLeagueAffiliateRewardDeletePopupComponent,
    resolve: {
      mLeagueAffiliateReward: MLeagueAffiliateRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueAffiliateRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
