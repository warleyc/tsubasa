import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTrainingCost } from 'app/shared/model/m-training-cost.model';
import { MTrainingCostService } from './m-training-cost.service';
import { MTrainingCostComponent } from './m-training-cost.component';
import { MTrainingCostDetailComponent } from './m-training-cost-detail.component';
import { MTrainingCostUpdateComponent } from './m-training-cost-update.component';
import { MTrainingCostDeletePopupComponent } from './m-training-cost-delete-dialog.component';
import { IMTrainingCost } from 'app/shared/model/m-training-cost.model';

@Injectable({ providedIn: 'root' })
export class MTrainingCostResolve implements Resolve<IMTrainingCost> {
  constructor(private service: MTrainingCostService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTrainingCost> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTrainingCost>) => response.ok),
        map((mTrainingCost: HttpResponse<MTrainingCost>) => mTrainingCost.body)
      );
    }
    return of(new MTrainingCost());
  }
}

export const mTrainingCostRoute: Routes = [
  {
    path: '',
    component: MTrainingCostComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTrainingCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTrainingCostDetailComponent,
    resolve: {
      mTrainingCost: MTrainingCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTrainingCostUpdateComponent,
    resolve: {
      mTrainingCost: MTrainingCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTrainingCostUpdateComponent,
    resolve: {
      mTrainingCost: MTrainingCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCosts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTrainingCostPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTrainingCostDeletePopupComponent,
    resolve: {
      mTrainingCost: MTrainingCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCosts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
