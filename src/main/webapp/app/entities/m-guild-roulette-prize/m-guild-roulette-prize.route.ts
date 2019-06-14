import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';
import { MGuildRoulettePrizeService } from './m-guild-roulette-prize.service';
import { MGuildRoulettePrizeComponent } from './m-guild-roulette-prize.component';
import { MGuildRoulettePrizeDetailComponent } from './m-guild-roulette-prize-detail.component';
import { MGuildRoulettePrizeUpdateComponent } from './m-guild-roulette-prize-update.component';
import { MGuildRoulettePrizeDeletePopupComponent } from './m-guild-roulette-prize-delete-dialog.component';
import { IMGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';

@Injectable({ providedIn: 'root' })
export class MGuildRoulettePrizeResolve implements Resolve<IMGuildRoulettePrize> {
  constructor(private service: MGuildRoulettePrizeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildRoulettePrize> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildRoulettePrize>) => response.ok),
        map((mGuildRoulettePrize: HttpResponse<MGuildRoulettePrize>) => mGuildRoulettePrize.body)
      );
    }
    return of(new MGuildRoulettePrize());
  }
}

export const mGuildRoulettePrizeRoute: Routes = [
  {
    path: '',
    component: MGuildRoulettePrizeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildRoulettePrizes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildRoulettePrizeDetailComponent,
    resolve: {
      mGuildRoulettePrize: MGuildRoulettePrizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildRoulettePrizes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildRoulettePrizeUpdateComponent,
    resolve: {
      mGuildRoulettePrize: MGuildRoulettePrizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildRoulettePrizes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildRoulettePrizeUpdateComponent,
    resolve: {
      mGuildRoulettePrize: MGuildRoulettePrizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildRoulettePrizes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildRoulettePrizePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildRoulettePrizeDeletePopupComponent,
    resolve: {
      mGuildRoulettePrize: MGuildRoulettePrizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildRoulettePrizes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
