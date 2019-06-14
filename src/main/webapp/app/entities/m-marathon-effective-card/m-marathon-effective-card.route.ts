import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';
import { MMarathonEffectiveCardService } from './m-marathon-effective-card.service';
import { MMarathonEffectiveCardComponent } from './m-marathon-effective-card.component';
import { MMarathonEffectiveCardDetailComponent } from './m-marathon-effective-card-detail.component';
import { MMarathonEffectiveCardUpdateComponent } from './m-marathon-effective-card-update.component';
import { MMarathonEffectiveCardDeletePopupComponent } from './m-marathon-effective-card-delete-dialog.component';
import { IMMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';

@Injectable({ providedIn: 'root' })
export class MMarathonEffectiveCardResolve implements Resolve<IMMarathonEffectiveCard> {
  constructor(private service: MMarathonEffectiveCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMarathonEffectiveCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMarathonEffectiveCard>) => response.ok),
        map((mMarathonEffectiveCard: HttpResponse<MMarathonEffectiveCard>) => mMarathonEffectiveCard.body)
      );
    }
    return of(new MMarathonEffectiveCard());
  }
}

export const mMarathonEffectiveCardRoute: Routes = [
  {
    path: '',
    component: MMarathonEffectiveCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMarathonEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMarathonEffectiveCardDetailComponent,
    resolve: {
      mMarathonEffectiveCard: MMarathonEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMarathonEffectiveCardUpdateComponent,
    resolve: {
      mMarathonEffectiveCard: MMarathonEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMarathonEffectiveCardUpdateComponent,
    resolve: {
      mMarathonEffectiveCard: MMarathonEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonEffectiveCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMarathonEffectiveCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMarathonEffectiveCardDeletePopupComponent,
    resolve: {
      mMarathonEffectiveCard: MMarathonEffectiveCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMarathonEffectiveCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
