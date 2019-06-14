import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';
import { MInheritActionSkillCostService } from './m-inherit-action-skill-cost.service';
import { MInheritActionSkillCostComponent } from './m-inherit-action-skill-cost.component';
import { MInheritActionSkillCostDetailComponent } from './m-inherit-action-skill-cost-detail.component';
import { MInheritActionSkillCostUpdateComponent } from './m-inherit-action-skill-cost-update.component';
import { MInheritActionSkillCostDeletePopupComponent } from './m-inherit-action-skill-cost-delete-dialog.component';
import { IMInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';

@Injectable({ providedIn: 'root' })
export class MInheritActionSkillCostResolve implements Resolve<IMInheritActionSkillCost> {
  constructor(private service: MInheritActionSkillCostService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMInheritActionSkillCost> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MInheritActionSkillCost>) => response.ok),
        map((mInheritActionSkillCost: HttpResponse<MInheritActionSkillCost>) => mInheritActionSkillCost.body)
      );
    }
    return of(new MInheritActionSkillCost());
  }
}

export const mInheritActionSkillCostRoute: Routes = [
  {
    path: '',
    component: MInheritActionSkillCostComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MInheritActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MInheritActionSkillCostDetailComponent,
    resolve: {
      mInheritActionSkillCost: MInheritActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInheritActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MInheritActionSkillCostUpdateComponent,
    resolve: {
      mInheritActionSkillCost: MInheritActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInheritActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MInheritActionSkillCostUpdateComponent,
    resolve: {
      mInheritActionSkillCost: MInheritActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInheritActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mInheritActionSkillCostPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MInheritActionSkillCostDeletePopupComponent,
    resolve: {
      mInheritActionSkillCost: MInheritActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInheritActionSkillCosts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
