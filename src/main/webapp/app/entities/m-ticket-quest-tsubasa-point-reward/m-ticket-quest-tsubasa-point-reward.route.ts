import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTicketQuestTsubasaPointReward } from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';
import { MTicketQuestTsubasaPointRewardService } from './m-ticket-quest-tsubasa-point-reward.service';
import { MTicketQuestTsubasaPointRewardComponent } from './m-ticket-quest-tsubasa-point-reward.component';
import { MTicketQuestTsubasaPointRewardDetailComponent } from './m-ticket-quest-tsubasa-point-reward-detail.component';
import { MTicketQuestTsubasaPointRewardUpdateComponent } from './m-ticket-quest-tsubasa-point-reward-update.component';
import { MTicketQuestTsubasaPointRewardDeletePopupComponent } from './m-ticket-quest-tsubasa-point-reward-delete-dialog.component';
import { IMTicketQuestTsubasaPointReward } from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';

@Injectable({ providedIn: 'root' })
export class MTicketQuestTsubasaPointRewardResolve implements Resolve<IMTicketQuestTsubasaPointReward> {
  constructor(private service: MTicketQuestTsubasaPointRewardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTicketQuestTsubasaPointReward> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTicketQuestTsubasaPointReward>) => response.ok),
        map((mTicketQuestTsubasaPointReward: HttpResponse<MTicketQuestTsubasaPointReward>) => mTicketQuestTsubasaPointReward.body)
      );
    }
    return of(new MTicketQuestTsubasaPointReward());
  }
}

export const mTicketQuestTsubasaPointRewardRoute: Routes = [
  {
    path: '',
    component: MTicketQuestTsubasaPointRewardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTicketQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTicketQuestTsubasaPointRewardDetailComponent,
    resolve: {
      mTicketQuestTsubasaPointReward: MTicketQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTicketQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mTicketQuestTsubasaPointReward: MTicketQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTicketQuestTsubasaPointRewardUpdateComponent,
    resolve: {
      mTicketQuestTsubasaPointReward: MTicketQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTicketQuestTsubasaPointRewardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTicketQuestTsubasaPointRewardDeletePopupComponent,
    resolve: {
      mTicketQuestTsubasaPointReward: MTicketQuestTsubasaPointRewardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestTsubasaPointRewards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
