import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEncountersCut } from 'app/shared/model/m-encounters-cut.model';
import { MEncountersCutService } from './m-encounters-cut.service';
import { MEncountersCutComponent } from './m-encounters-cut.component';
import { MEncountersCutDetailComponent } from './m-encounters-cut-detail.component';
import { MEncountersCutUpdateComponent } from './m-encounters-cut-update.component';
import { MEncountersCutDeletePopupComponent } from './m-encounters-cut-delete-dialog.component';
import { IMEncountersCut } from 'app/shared/model/m-encounters-cut.model';

@Injectable({ providedIn: 'root' })
export class MEncountersCutResolve implements Resolve<IMEncountersCut> {
  constructor(private service: MEncountersCutService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEncountersCut> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEncountersCut>) => response.ok),
        map((mEncountersCut: HttpResponse<MEncountersCut>) => mEncountersCut.body)
      );
    }
    return of(new MEncountersCut());
  }
}

export const mEncountersCutRoute: Routes = [
  {
    path: '',
    component: MEncountersCutComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEncountersCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEncountersCutDetailComponent,
    resolve: {
      mEncountersCut: MEncountersCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEncountersCutUpdateComponent,
    resolve: {
      mEncountersCut: MEncountersCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCuts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEncountersCutUpdateComponent,
    resolve: {
      mEncountersCut: MEncountersCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCuts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEncountersCutPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEncountersCutDeletePopupComponent,
    resolve: {
      mEncountersCut: MEncountersCutResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCuts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
