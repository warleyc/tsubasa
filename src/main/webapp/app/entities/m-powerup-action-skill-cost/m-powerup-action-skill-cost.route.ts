import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';
import { MPowerupActionSkillCostService } from './m-powerup-action-skill-cost.service';
import { MPowerupActionSkillCostComponent } from './m-powerup-action-skill-cost.component';
import { MPowerupActionSkillCostDetailComponent } from './m-powerup-action-skill-cost-detail.component';
import { MPowerupActionSkillCostUpdateComponent } from './m-powerup-action-skill-cost-update.component';
import { MPowerupActionSkillCostDeletePopupComponent } from './m-powerup-action-skill-cost-delete-dialog.component';
import { IMPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';

@Injectable({ providedIn: 'root' })
export class MPowerupActionSkillCostResolve implements Resolve<IMPowerupActionSkillCost> {
  constructor(private service: MPowerupActionSkillCostService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPowerupActionSkillCost> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPowerupActionSkillCost>) => response.ok),
        map((mPowerupActionSkillCost: HttpResponse<MPowerupActionSkillCost>) => mPowerupActionSkillCost.body)
      );
    }
    return of(new MPowerupActionSkillCost());
  }
}

export const mPowerupActionSkillCostRoute: Routes = [
  {
    path: '',
    component: MPowerupActionSkillCostComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPowerupActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPowerupActionSkillCostDetailComponent,
    resolve: {
      mPowerupActionSkillCost: MPowerupActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPowerupActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPowerupActionSkillCostUpdateComponent,
    resolve: {
      mPowerupActionSkillCost: MPowerupActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPowerupActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPowerupActionSkillCostUpdateComponent,
    resolve: {
      mPowerupActionSkillCost: MPowerupActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPowerupActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPowerupActionSkillCostPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPowerupActionSkillCostDeletePopupComponent,
    resolve: {
      mPowerupActionSkillCost: MPowerupActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPowerupActionSkillCosts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
