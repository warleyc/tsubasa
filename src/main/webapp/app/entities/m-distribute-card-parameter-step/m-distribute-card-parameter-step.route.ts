import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';
import { MDistributeCardParameterStepService } from './m-distribute-card-parameter-step.service';
import { MDistributeCardParameterStepComponent } from './m-distribute-card-parameter-step.component';
import { MDistributeCardParameterStepDetailComponent } from './m-distribute-card-parameter-step-detail.component';
import { MDistributeCardParameterStepUpdateComponent } from './m-distribute-card-parameter-step-update.component';
import { MDistributeCardParameterStepDeletePopupComponent } from './m-distribute-card-parameter-step-delete-dialog.component';
import { IMDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';

@Injectable({ providedIn: 'root' })
export class MDistributeCardParameterStepResolve implements Resolve<IMDistributeCardParameterStep> {
  constructor(private service: MDistributeCardParameterStepService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDistributeCardParameterStep> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDistributeCardParameterStep>) => response.ok),
        map((mDistributeCardParameterStep: HttpResponse<MDistributeCardParameterStep>) => mDistributeCardParameterStep.body)
      );
    }
    return of(new MDistributeCardParameterStep());
  }
}

export const mDistributeCardParameterStepRoute: Routes = [
  {
    path: '',
    component: MDistributeCardParameterStepComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDistributeCardParameterSteps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDistributeCardParameterStepDetailComponent,
    resolve: {
      mDistributeCardParameterStep: MDistributeCardParameterStepResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameterSteps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDistributeCardParameterStepUpdateComponent,
    resolve: {
      mDistributeCardParameterStep: MDistributeCardParameterStepResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameterSteps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDistributeCardParameterStepUpdateComponent,
    resolve: {
      mDistributeCardParameterStep: MDistributeCardParameterStepResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameterSteps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDistributeCardParameterStepPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDistributeCardParameterStepDeletePopupComponent,
    resolve: {
      mDistributeCardParameterStep: MDistributeCardParameterStepResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDistributeCardParameterSteps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
