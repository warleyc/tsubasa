import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';
import { MRecommendFormationFilterService } from './m-recommend-formation-filter.service';
import { MRecommendFormationFilterComponent } from './m-recommend-formation-filter.component';
import { MRecommendFormationFilterDetailComponent } from './m-recommend-formation-filter-detail.component';
import { MRecommendFormationFilterUpdateComponent } from './m-recommend-formation-filter-update.component';
import { MRecommendFormationFilterDeletePopupComponent } from './m-recommend-formation-filter-delete-dialog.component';
import { IMRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';

@Injectable({ providedIn: 'root' })
export class MRecommendFormationFilterResolve implements Resolve<IMRecommendFormationFilter> {
  constructor(private service: MRecommendFormationFilterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMRecommendFormationFilter> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MRecommendFormationFilter>) => response.ok),
        map((mRecommendFormationFilter: HttpResponse<MRecommendFormationFilter>) => mRecommendFormationFilter.body)
      );
    }
    return of(new MRecommendFormationFilter());
  }
}

export const mRecommendFormationFilterRoute: Routes = [
  {
    path: '',
    component: MRecommendFormationFilterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MRecommendFormationFilters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MRecommendFormationFilterDetailComponent,
    resolve: {
      mRecommendFormationFilter: MRecommendFormationFilterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MRecommendFormationFilterUpdateComponent,
    resolve: {
      mRecommendFormationFilter: MRecommendFormationFilterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MRecommendFormationFilterUpdateComponent,
    resolve: {
      mRecommendFormationFilter: MRecommendFormationFilterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mRecommendFormationFilterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MRecommendFormationFilterDeletePopupComponent,
    resolve: {
      mRecommendFormationFilter: MRecommendFormationFilterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
