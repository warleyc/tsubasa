import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEmblemParts } from 'app/shared/model/m-emblem-parts.model';
import { MEmblemPartsService } from './m-emblem-parts.service';
import { MEmblemPartsComponent } from './m-emblem-parts.component';
import { MEmblemPartsDetailComponent } from './m-emblem-parts-detail.component';
import { MEmblemPartsUpdateComponent } from './m-emblem-parts-update.component';
import { MEmblemPartsDeletePopupComponent } from './m-emblem-parts-delete-dialog.component';
import { IMEmblemParts } from 'app/shared/model/m-emblem-parts.model';

@Injectable({ providedIn: 'root' })
export class MEmblemPartsResolve implements Resolve<IMEmblemParts> {
  constructor(private service: MEmblemPartsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEmblemParts> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEmblemParts>) => response.ok),
        map((mEmblemParts: HttpResponse<MEmblemParts>) => mEmblemParts.body)
      );
    }
    return of(new MEmblemParts());
  }
}

export const mEmblemPartsRoute: Routes = [
  {
    path: '',
    component: MEmblemPartsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEmblemParts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEmblemPartsDetailComponent,
    resolve: {
      mEmblemParts: MEmblemPartsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemParts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEmblemPartsUpdateComponent,
    resolve: {
      mEmblemParts: MEmblemPartsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemParts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEmblemPartsUpdateComponent,
    resolve: {
      mEmblemParts: MEmblemPartsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemParts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEmblemPartsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEmblemPartsDeletePopupComponent,
    resolve: {
      mEmblemParts: MEmblemPartsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemParts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
