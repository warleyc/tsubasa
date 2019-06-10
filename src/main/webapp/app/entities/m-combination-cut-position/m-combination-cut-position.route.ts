import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';
import { MCombinationCutPositionService } from './m-combination-cut-position.service';
import { MCombinationCutPositionComponent } from './m-combination-cut-position.component';
import { MCombinationCutPositionDetailComponent } from './m-combination-cut-position-detail.component';
import { MCombinationCutPositionUpdateComponent } from './m-combination-cut-position-update.component';
import { MCombinationCutPositionDeletePopupComponent } from './m-combination-cut-position-delete-dialog.component';
import { IMCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';

@Injectable({ providedIn: 'root' })
export class MCombinationCutPositionResolve implements Resolve<IMCombinationCutPosition> {
  constructor(private service: MCombinationCutPositionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCombinationCutPosition> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCombinationCutPosition>) => response.ok),
        map((mCombinationCutPosition: HttpResponse<MCombinationCutPosition>) => mCombinationCutPosition.body)
      );
    }
    return of(new MCombinationCutPosition());
  }
}

export const mCombinationCutPositionRoute: Routes = [
  {
    path: '',
    component: MCombinationCutPositionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCombinationCutPositions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCombinationCutPositionDetailComponent,
    resolve: {
      mCombinationCutPosition: MCombinationCutPositionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCombinationCutPositions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCombinationCutPositionUpdateComponent,
    resolve: {
      mCombinationCutPosition: MCombinationCutPositionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCombinationCutPositions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCombinationCutPositionUpdateComponent,
    resolve: {
      mCombinationCutPosition: MCombinationCutPositionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCombinationCutPositions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCombinationCutPositionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCombinationCutPositionDeletePopupComponent,
    resolve: {
      mCombinationCutPosition: MCombinationCutPositionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCombinationCutPositions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
