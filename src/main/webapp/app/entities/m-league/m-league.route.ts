import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLeague } from 'app/shared/model/m-league.model';
import { MLeagueService } from './m-league.service';
import { MLeagueComponent } from './m-league.component';
import { MLeagueDetailComponent } from './m-league-detail.component';
import { MLeagueUpdateComponent } from './m-league-update.component';
import { MLeagueDeletePopupComponent } from './m-league-delete-dialog.component';
import { IMLeague } from 'app/shared/model/m-league.model';

@Injectable({ providedIn: 'root' })
export class MLeagueResolve implements Resolve<IMLeague> {
  constructor(private service: MLeagueService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLeague> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLeague>) => response.ok),
        map((mLeague: HttpResponse<MLeague>) => mLeague.body)
      );
    }
    return of(new MLeague());
  }
}

export const mLeagueRoute: Routes = [
  {
    path: '',
    component: MLeagueComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLeagues'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLeagueDetailComponent,
    resolve: {
      mLeague: MLeagueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagues'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLeagueUpdateComponent,
    resolve: {
      mLeague: MLeagueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagues'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLeagueUpdateComponent,
    resolve: {
      mLeague: MLeagueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagues'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLeaguePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLeagueDeletePopupComponent,
    resolve: {
      mLeague: MLeagueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagues'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
