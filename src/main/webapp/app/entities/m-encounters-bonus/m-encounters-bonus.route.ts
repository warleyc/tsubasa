import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';
import { MEncountersBonusService } from './m-encounters-bonus.service';
import { MEncountersBonusComponent } from './m-encounters-bonus.component';
import { MEncountersBonusDetailComponent } from './m-encounters-bonus-detail.component';
import { MEncountersBonusUpdateComponent } from './m-encounters-bonus-update.component';
import { MEncountersBonusDeletePopupComponent } from './m-encounters-bonus-delete-dialog.component';
import { IMEncountersBonus } from 'app/shared/model/m-encounters-bonus.model';

@Injectable({ providedIn: 'root' })
export class MEncountersBonusResolve implements Resolve<IMEncountersBonus> {
  constructor(private service: MEncountersBonusService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEncountersBonus> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEncountersBonus>) => response.ok),
        map((mEncountersBonus: HttpResponse<MEncountersBonus>) => mEncountersBonus.body)
      );
    }
    return of(new MEncountersBonus());
  }
}

export const mEncountersBonusRoute: Routes = [
  {
    path: '',
    component: MEncountersBonusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEncountersBonuses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEncountersBonusDetailComponent,
    resolve: {
      mEncountersBonus: MEncountersBonusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersBonuses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEncountersBonusUpdateComponent,
    resolve: {
      mEncountersBonus: MEncountersBonusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersBonuses'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEncountersBonusUpdateComponent,
    resolve: {
      mEncountersBonus: MEncountersBonusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersBonuses'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEncountersBonusPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEncountersBonusDeletePopupComponent,
    resolve: {
      mEncountersBonus: MEncountersBonusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersBonuses'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
