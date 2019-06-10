import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MContentableCard } from 'app/shared/model/m-contentable-card.model';
import { MContentableCardService } from './m-contentable-card.service';
import { MContentableCardComponent } from './m-contentable-card.component';
import { MContentableCardDetailComponent } from './m-contentable-card-detail.component';
import { MContentableCardUpdateComponent } from './m-contentable-card-update.component';
import { MContentableCardDeletePopupComponent } from './m-contentable-card-delete-dialog.component';
import { IMContentableCard } from 'app/shared/model/m-contentable-card.model';

@Injectable({ providedIn: 'root' })
export class MContentableCardResolve implements Resolve<IMContentableCard> {
  constructor(private service: MContentableCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMContentableCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MContentableCard>) => response.ok),
        map((mContentableCard: HttpResponse<MContentableCard>) => mContentableCard.body)
      );
    }
    return of(new MContentableCard());
  }
}

export const mContentableCardRoute: Routes = [
  {
    path: '',
    component: MContentableCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MContentableCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MContentableCardDetailComponent,
    resolve: {
      mContentableCard: MContentableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MContentableCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MContentableCardUpdateComponent,
    resolve: {
      mContentableCard: MContentableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MContentableCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MContentableCardUpdateComponent,
    resolve: {
      mContentableCard: MContentableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MContentableCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mContentableCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MContentableCardDeletePopupComponent,
    resolve: {
      mContentableCard: MContentableCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MContentableCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
