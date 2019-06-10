import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDeckCondition } from 'app/shared/model/m-deck-condition.model';
import { MDeckConditionService } from './m-deck-condition.service';
import { MDeckConditionComponent } from './m-deck-condition.component';
import { MDeckConditionDetailComponent } from './m-deck-condition-detail.component';
import { MDeckConditionUpdateComponent } from './m-deck-condition-update.component';
import { MDeckConditionDeletePopupComponent } from './m-deck-condition-delete-dialog.component';
import { IMDeckCondition } from 'app/shared/model/m-deck-condition.model';

@Injectable({ providedIn: 'root' })
export class MDeckConditionResolve implements Resolve<IMDeckCondition> {
  constructor(private service: MDeckConditionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDeckCondition> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDeckCondition>) => response.ok),
        map((mDeckCondition: HttpResponse<MDeckCondition>) => mDeckCondition.body)
      );
    }
    return of(new MDeckCondition());
  }
}

export const mDeckConditionRoute: Routes = [
  {
    path: '',
    component: MDeckConditionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDeckConditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDeckConditionDetailComponent,
    resolve: {
      mDeckCondition: MDeckConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckConditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDeckConditionUpdateComponent,
    resolve: {
      mDeckCondition: MDeckConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckConditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDeckConditionUpdateComponent,
    resolve: {
      mDeckCondition: MDeckConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckConditions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDeckConditionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDeckConditionDeletePopupComponent,
    resolve: {
      mDeckCondition: MDeckConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckConditions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
