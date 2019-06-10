import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';
import { MDummyOpponentService } from './m-dummy-opponent.service';
import { MDummyOpponentComponent } from './m-dummy-opponent.component';
import { MDummyOpponentDetailComponent } from './m-dummy-opponent-detail.component';
import { MDummyOpponentUpdateComponent } from './m-dummy-opponent-update.component';
import { MDummyOpponentDeletePopupComponent } from './m-dummy-opponent-delete-dialog.component';
import { IMDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

@Injectable({ providedIn: 'root' })
export class MDummyOpponentResolve implements Resolve<IMDummyOpponent> {
  constructor(private service: MDummyOpponentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDummyOpponent> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDummyOpponent>) => response.ok),
        map((mDummyOpponent: HttpResponse<MDummyOpponent>) => mDummyOpponent.body)
      );
    }
    return of(new MDummyOpponent());
  }
}

export const mDummyOpponentRoute: Routes = [
  {
    path: '',
    component: MDummyOpponentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDummyOpponents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDummyOpponentDetailComponent,
    resolve: {
      mDummyOpponent: MDummyOpponentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyOpponents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDummyOpponentUpdateComponent,
    resolve: {
      mDummyOpponent: MDummyOpponentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyOpponents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDummyOpponentUpdateComponent,
    resolve: {
      mDummyOpponent: MDummyOpponentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyOpponents'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDummyOpponentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDummyOpponentDeletePopupComponent,
    resolve: {
      mDummyOpponent: MDummyOpponentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyOpponents'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
