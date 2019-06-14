import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLeagueRegulation } from 'app/shared/model/m-league-regulation.model';
import { MLeagueRegulationService } from './m-league-regulation.service';
import { MLeagueRegulationComponent } from './m-league-regulation.component';
import { MLeagueRegulationDetailComponent } from './m-league-regulation-detail.component';
import { MLeagueRegulationUpdateComponent } from './m-league-regulation-update.component';
import { MLeagueRegulationDeletePopupComponent } from './m-league-regulation-delete-dialog.component';
import { IMLeagueRegulation } from 'app/shared/model/m-league-regulation.model';

@Injectable({ providedIn: 'root' })
export class MLeagueRegulationResolve implements Resolve<IMLeagueRegulation> {
  constructor(private service: MLeagueRegulationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLeagueRegulation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLeagueRegulation>) => response.ok),
        map((mLeagueRegulation: HttpResponse<MLeagueRegulation>) => mLeagueRegulation.body)
      );
    }
    return of(new MLeagueRegulation());
  }
}

export const mLeagueRegulationRoute: Routes = [
  {
    path: '',
    component: MLeagueRegulationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLeagueRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLeagueRegulationDetailComponent,
    resolve: {
      mLeagueRegulation: MLeagueRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLeagueRegulationUpdateComponent,
    resolve: {
      mLeagueRegulation: MLeagueRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRegulations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLeagueRegulationUpdateComponent,
    resolve: {
      mLeagueRegulation: MLeagueRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRegulations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLeagueRegulationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLeagueRegulationDeletePopupComponent,
    resolve: {
      mLeagueRegulation: MLeagueRegulationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueRegulations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
