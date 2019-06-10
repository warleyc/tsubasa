import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MArousal } from 'app/shared/model/m-arousal.model';
import { MArousalService } from './m-arousal.service';
import { MArousalComponent } from './m-arousal.component';
import { MArousalDetailComponent } from './m-arousal-detail.component';
import { MArousalUpdateComponent } from './m-arousal-update.component';
import { MArousalDeletePopupComponent } from './m-arousal-delete-dialog.component';
import { IMArousal } from 'app/shared/model/m-arousal.model';

@Injectable({ providedIn: 'root' })
export class MArousalResolve implements Resolve<IMArousal> {
  constructor(private service: MArousalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMArousal> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MArousal>) => response.ok),
        map((mArousal: HttpResponse<MArousal>) => mArousal.body)
      );
    }
    return of(new MArousal());
  }
}

export const mArousalRoute: Routes = [
  {
    path: '',
    component: MArousalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MArousals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MArousalDetailComponent,
    resolve: {
      mArousal: MArousalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MArousalUpdateComponent,
    resolve: {
      mArousal: MArousalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousals'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MArousalUpdateComponent,
    resolve: {
      mArousal: MArousalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousals'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mArousalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MArousalDeletePopupComponent,
    resolve: {
      mArousal: MArousalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MArousals'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
