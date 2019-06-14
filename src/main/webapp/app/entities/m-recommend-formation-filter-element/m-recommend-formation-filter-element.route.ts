import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';
import { MRecommendFormationFilterElementService } from './m-recommend-formation-filter-element.service';
import { MRecommendFormationFilterElementComponent } from './m-recommend-formation-filter-element.component';
import { MRecommendFormationFilterElementDetailComponent } from './m-recommend-formation-filter-element-detail.component';
import { MRecommendFormationFilterElementUpdateComponent } from './m-recommend-formation-filter-element-update.component';
import { MRecommendFormationFilterElementDeletePopupComponent } from './m-recommend-formation-filter-element-delete-dialog.component';
import { IMRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';

@Injectable({ providedIn: 'root' })
export class MRecommendFormationFilterElementResolve implements Resolve<IMRecommendFormationFilterElement> {
  constructor(private service: MRecommendFormationFilterElementService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMRecommendFormationFilterElement> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MRecommendFormationFilterElement>) => response.ok),
        map((mRecommendFormationFilterElement: HttpResponse<MRecommendFormationFilterElement>) => mRecommendFormationFilterElement.body)
      );
    }
    return of(new MRecommendFormationFilterElement());
  }
}

export const mRecommendFormationFilterElementRoute: Routes = [
  {
    path: '',
    component: MRecommendFormationFilterElementComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MRecommendFormationFilterElements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MRecommendFormationFilterElementDetailComponent,
    resolve: {
      mRecommendFormationFilterElement: MRecommendFormationFilterElementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilterElements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MRecommendFormationFilterElementUpdateComponent,
    resolve: {
      mRecommendFormationFilterElement: MRecommendFormationFilterElementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilterElements'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MRecommendFormationFilterElementUpdateComponent,
    resolve: {
      mRecommendFormationFilterElement: MRecommendFormationFilterElementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilterElements'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mRecommendFormationFilterElementPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MRecommendFormationFilterElementDeletePopupComponent,
    resolve: {
      mRecommendFormationFilterElement: MRecommendFormationFilterElementResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRecommendFormationFilterElements'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
