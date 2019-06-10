import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';
import { MDetachActionSkillCostService } from './m-detach-action-skill-cost.service';
import { MDetachActionSkillCostComponent } from './m-detach-action-skill-cost.component';
import { MDetachActionSkillCostDetailComponent } from './m-detach-action-skill-cost-detail.component';
import { MDetachActionSkillCostUpdateComponent } from './m-detach-action-skill-cost-update.component';
import { MDetachActionSkillCostDeletePopupComponent } from './m-detach-action-skill-cost-delete-dialog.component';
import { IMDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';

@Injectable({ providedIn: 'root' })
export class MDetachActionSkillCostResolve implements Resolve<IMDetachActionSkillCost> {
  constructor(private service: MDetachActionSkillCostService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDetachActionSkillCost> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDetachActionSkillCost>) => response.ok),
        map((mDetachActionSkillCost: HttpResponse<MDetachActionSkillCost>) => mDetachActionSkillCost.body)
      );
    }
    return of(new MDetachActionSkillCost());
  }
}

export const mDetachActionSkillCostRoute: Routes = [
  {
    path: '',
    component: MDetachActionSkillCostComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDetachActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDetachActionSkillCostDetailComponent,
    resolve: {
      mDetachActionSkillCost: MDetachActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDetachActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDetachActionSkillCostUpdateComponent,
    resolve: {
      mDetachActionSkillCost: MDetachActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDetachActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDetachActionSkillCostUpdateComponent,
    resolve: {
      mDetachActionSkillCost: MDetachActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDetachActionSkillCosts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDetachActionSkillCostPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDetachActionSkillCostDeletePopupComponent,
    resolve: {
      mDetachActionSkillCost: MDetachActionSkillCostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDetachActionSkillCosts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
