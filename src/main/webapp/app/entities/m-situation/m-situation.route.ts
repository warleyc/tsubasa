import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSituation } from 'app/shared/model/m-situation.model';
import { MSituationService } from './m-situation.service';
import { MSituationComponent } from './m-situation.component';
import { MSituationDetailComponent } from './m-situation-detail.component';
import { MSituationUpdateComponent } from './m-situation-update.component';
import { MSituationDeletePopupComponent } from './m-situation-delete-dialog.component';
import { IMSituation } from 'app/shared/model/m-situation.model';

@Injectable({ providedIn: 'root' })
export class MSituationResolve implements Resolve<IMSituation> {
  constructor(private service: MSituationService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSituation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSituation>) => response.ok),
        map((mSituation: HttpResponse<MSituation>) => mSituation.body)
      );
    }
    return of(new MSituation());
  }
}

export const mSituationRoute: Routes = [
  {
    path: '',
    component: MSituationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSituations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSituationDetailComponent,
    resolve: {
      mSituation: MSituationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSituationUpdateComponent,
    resolve: {
      mSituation: MSituationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituations'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSituationUpdateComponent,
    resolve: {
      mSituation: MSituationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituations'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSituationPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSituationDeletePopupComponent,
    resolve: {
      mSituation: MSituationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSituations'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
