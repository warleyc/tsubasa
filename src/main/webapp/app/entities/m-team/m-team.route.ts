import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTeam } from 'app/shared/model/m-team.model';
import { MTeamService } from './m-team.service';
import { MTeamComponent } from './m-team.component';
import { MTeamDetailComponent } from './m-team-detail.component';
import { MTeamUpdateComponent } from './m-team-update.component';
import { MTeamDeletePopupComponent } from './m-team-delete-dialog.component';
import { IMTeam } from 'app/shared/model/m-team.model';

@Injectable({ providedIn: 'root' })
export class MTeamResolve implements Resolve<IMTeam> {
  constructor(private service: MTeamService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTeam> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTeam>) => response.ok),
        map((mTeam: HttpResponse<MTeam>) => mTeam.body)
      );
    }
    return of(new MTeam());
  }
}

export const mTeamRoute: Routes = [
  {
    path: '',
    component: MTeamComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTeams'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTeamDetailComponent,
    resolve: {
      mTeam: MTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeams'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTeamUpdateComponent,
    resolve: {
      mTeam: MTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeams'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTeamUpdateComponent,
    resolve: {
      mTeam: MTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeams'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTeamPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTeamDeletePopupComponent,
    resolve: {
      mTeam: MTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeams'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
