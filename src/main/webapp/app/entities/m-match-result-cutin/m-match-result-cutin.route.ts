import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';
import { MMatchResultCutinService } from './m-match-result-cutin.service';
import { MMatchResultCutinComponent } from './m-match-result-cutin.component';
import { MMatchResultCutinDetailComponent } from './m-match-result-cutin-detail.component';
import { MMatchResultCutinUpdateComponent } from './m-match-result-cutin-update.component';
import { MMatchResultCutinDeletePopupComponent } from './m-match-result-cutin-delete-dialog.component';
import { IMMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';

@Injectable({ providedIn: 'root' })
export class MMatchResultCutinResolve implements Resolve<IMMatchResultCutin> {
  constructor(private service: MMatchResultCutinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMMatchResultCutin> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MMatchResultCutin>) => response.ok),
        map((mMatchResultCutin: HttpResponse<MMatchResultCutin>) => mMatchResultCutin.body)
      );
    }
    return of(new MMatchResultCutin());
  }
}

export const mMatchResultCutinRoute: Routes = [
  {
    path: '',
    component: MMatchResultCutinComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MMatchResultCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MMatchResultCutinDetailComponent,
    resolve: {
      mMatchResultCutin: MMatchResultCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchResultCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MMatchResultCutinUpdateComponent,
    resolve: {
      mMatchResultCutin: MMatchResultCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchResultCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MMatchResultCutinUpdateComponent,
    resolve: {
      mMatchResultCutin: MMatchResultCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchResultCutins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mMatchResultCutinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MMatchResultCutinDeletePopupComponent,
    resolve: {
      mMatchResultCutin: MMatchResultCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MMatchResultCutins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
