import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';
import { MAreaActionWeightService } from './m-area-action-weight.service';
import { MAreaActionWeightComponent } from './m-area-action-weight.component';
import { MAreaActionWeightDetailComponent } from './m-area-action-weight-detail.component';
import { MAreaActionWeightUpdateComponent } from './m-area-action-weight-update.component';
import { MAreaActionWeightDeletePopupComponent } from './m-area-action-weight-delete-dialog.component';
import { IMAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';

@Injectable({ providedIn: 'root' })
export class MAreaActionWeightResolve implements Resolve<IMAreaActionWeight> {
  constructor(private service: MAreaActionWeightService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAreaActionWeight> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAreaActionWeight>) => response.ok),
        map((mAreaActionWeight: HttpResponse<MAreaActionWeight>) => mAreaActionWeight.body)
      );
    }
    return of(new MAreaActionWeight());
  }
}

export const mAreaActionWeightRoute: Routes = [
  {
    path: '',
    component: MAreaActionWeightComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAreaActionWeights'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAreaActionWeightDetailComponent,
    resolve: {
      mAreaActionWeight: MAreaActionWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAreaActionWeights'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAreaActionWeightUpdateComponent,
    resolve: {
      mAreaActionWeight: MAreaActionWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAreaActionWeights'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAreaActionWeightUpdateComponent,
    resolve: {
      mAreaActionWeight: MAreaActionWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAreaActionWeights'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAreaActionWeightPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAreaActionWeightDeletePopupComponent,
    resolve: {
      mAreaActionWeight: MAreaActionWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAreaActionWeights'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
