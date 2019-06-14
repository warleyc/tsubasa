import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';
import { MModelUniformUpService } from './m-model-uniform-up.service';
import { MModelUniformUpComponent } from './m-model-uniform-up.component';
import { MModelUniformUpDetailComponent } from './m-model-uniform-up-detail.component';
import { MModelUniformUpUpdateComponent } from './m-model-uniform-up-update.component';
import { MModelUniformUpDeletePopupComponent } from './m-model-uniform-up-delete-dialog.component';
import { IMModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';

@Injectable({ providedIn: 'root' })
export class MModelUniformUpResolve implements Resolve<IMModelUniformUp> {
  constructor(private service: MModelUniformUpService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMModelUniformUp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MModelUniformUp>) => response.ok),
        map((mModelUniformUp: HttpResponse<MModelUniformUp>) => mModelUniformUp.body)
      );
    }
    return of(new MModelUniformUp());
  }
}

export const mModelUniformUpRoute: Routes = [
  {
    path: '',
    component: MModelUniformUpComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MModelUniformUps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MModelUniformUpDetailComponent,
    resolve: {
      mModelUniformUp: MModelUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MModelUniformUpUpdateComponent,
    resolve: {
      mModelUniformUp: MModelUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MModelUniformUpUpdateComponent,
    resolve: {
      mModelUniformUp: MModelUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mModelUniformUpPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MModelUniformUpDeletePopupComponent,
    resolve: {
      mModelUniformUp: MModelUniformUpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelUniformUps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
