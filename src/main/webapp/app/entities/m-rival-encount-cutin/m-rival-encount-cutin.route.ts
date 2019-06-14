import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';
import { MRivalEncountCutinService } from './m-rival-encount-cutin.service';
import { MRivalEncountCutinComponent } from './m-rival-encount-cutin.component';
import { MRivalEncountCutinDetailComponent } from './m-rival-encount-cutin-detail.component';
import { MRivalEncountCutinUpdateComponent } from './m-rival-encount-cutin-update.component';
import { MRivalEncountCutinDeletePopupComponent } from './m-rival-encount-cutin-delete-dialog.component';
import { IMRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';

@Injectable({ providedIn: 'root' })
export class MRivalEncountCutinResolve implements Resolve<IMRivalEncountCutin> {
  constructor(private service: MRivalEncountCutinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMRivalEncountCutin> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MRivalEncountCutin>) => response.ok),
        map((mRivalEncountCutin: HttpResponse<MRivalEncountCutin>) => mRivalEncountCutin.body)
      );
    }
    return of(new MRivalEncountCutin());
  }
}

export const mRivalEncountCutinRoute: Routes = [
  {
    path: '',
    component: MRivalEncountCutinComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MRivalEncountCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MRivalEncountCutinDetailComponent,
    resolve: {
      mRivalEncountCutin: MRivalEncountCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRivalEncountCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MRivalEncountCutinUpdateComponent,
    resolve: {
      mRivalEncountCutin: MRivalEncountCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRivalEncountCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MRivalEncountCutinUpdateComponent,
    resolve: {
      mRivalEncountCutin: MRivalEncountCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRivalEncountCutins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mRivalEncountCutinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MRivalEncountCutinDeletePopupComponent,
    resolve: {
      mRivalEncountCutin: MRivalEncountCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRivalEncountCutins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
