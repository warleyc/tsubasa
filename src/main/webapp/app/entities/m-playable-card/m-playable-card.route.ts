import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPlayableCard } from 'app/shared/model/m-playable-card.model';
import { MPlayableCardService } from './m-playable-card.service';
import { MPlayableCardComponent } from './m-playable-card.component';
import { MPlayableCardDetailComponent } from './m-playable-card-detail.component';
import { MPlayableCardUpdateComponent } from './m-playable-card-update.component';
import { MPlayableCardDeletePopupComponent } from './m-playable-card-delete-dialog.component';
import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';

@Injectable({ providedIn: 'root' })
export class MPlayableCardResolve implements Resolve<IMPlayableCard> {
  constructor(private service: MPlayableCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPlayableCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPlayableCard>) => response.ok),
        map((mPlayableCard: HttpResponse<MPlayableCard>) => mPlayableCard.body)
      );
    }
    return of(new MPlayableCard());
  }
}

export const mPlayableCardRoute: Routes = [
  {
    path: '',
    component: MPlayableCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPlayableCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPlayableCardDetailComponent,
    resolve: {
      mPlayableCard: MPlayableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPlayableCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPlayableCardUpdateComponent,
    resolve: {
      mPlayableCard: MPlayableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPlayableCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPlayableCardUpdateComponent,
    resolve: {
      mPlayableCard: MPlayableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPlayableCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPlayableCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPlayableCardDeletePopupComponent,
    resolve: {
      mPlayableCard: MPlayableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPlayableCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
