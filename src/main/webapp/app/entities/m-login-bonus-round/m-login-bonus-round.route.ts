import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';
import { MLoginBonusRoundService } from './m-login-bonus-round.service';
import { MLoginBonusRoundComponent } from './m-login-bonus-round.component';
import { MLoginBonusRoundDetailComponent } from './m-login-bonus-round-detail.component';
import { MLoginBonusRoundUpdateComponent } from './m-login-bonus-round-update.component';
import { MLoginBonusRoundDeletePopupComponent } from './m-login-bonus-round-delete-dialog.component';
import { IMLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';

@Injectable({ providedIn: 'root' })
export class MLoginBonusRoundResolve implements Resolve<IMLoginBonusRound> {
  constructor(private service: MLoginBonusRoundService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLoginBonusRound> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLoginBonusRound>) => response.ok),
        map((mLoginBonusRound: HttpResponse<MLoginBonusRound>) => mLoginBonusRound.body)
      );
    }
    return of(new MLoginBonusRound());
  }
}

export const mLoginBonusRoundRoute: Routes = [
  {
    path: '',
    component: MLoginBonusRoundComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLoginBonusRounds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLoginBonusRoundDetailComponent,
    resolve: {
      mLoginBonusRound: MLoginBonusRoundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusRounds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLoginBonusRoundUpdateComponent,
    resolve: {
      mLoginBonusRound: MLoginBonusRoundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusRounds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLoginBonusRoundUpdateComponent,
    resolve: {
      mLoginBonusRound: MLoginBonusRoundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusRounds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLoginBonusRoundPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLoginBonusRoundDeletePopupComponent,
    resolve: {
      mLoginBonusRound: MLoginBonusRoundResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLoginBonusRounds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
