import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';
import { MGoalEffectiveCardService } from './m-goal-effective-card.service';
import { MGoalEffectiveCardComponent } from './m-goal-effective-card.component';
import { MGoalEffectiveCardDetailComponent } from './m-goal-effective-card-detail.component';
import { MGoalEffectiveCardUpdateComponent } from './m-goal-effective-card-update.component';
import { MGoalEffectiveCardDeletePopupComponent } from './m-goal-effective-card-delete-dialog.component';
import { IMGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';

@Injectable({ providedIn: 'root' })
export class MGoalEffectiveCardResolve implements Resolve<IMGoalEffectiveCard> {
  constructor(private service: MGoalEffectiveCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGoalEffectiveCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGoalEffectiveCard>) => response.ok),
        map((mGoalEffectiveCard: HttpResponse<MGoalEffectiveCard>) => mGoalEffectiveCard.body)
      );
    }
    return of(new MGoalEffectiveCard());
  }
}

export const mGoalEffectiveCardRoute: Routes = [
  {
    path: '',
    component: MGoalEffectiveCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGoalEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGoalEffectiveCardDetailComponent,
    resolve: {
      mGoalEffectiveCard: MGoalEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGoalEffectiveCardUpdateComponent,
    resolve: {
      mGoalEffectiveCard: MGoalEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGoalEffectiveCardUpdateComponent,
    resolve: {
      mGoalEffectiveCard: MGoalEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGoalEffectiveCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGoalEffectiveCardDeletePopupComponent,
    resolve: {
      mGoalEffectiveCard: MGoalEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGoalEffectiveCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
