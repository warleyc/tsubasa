import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDbMapping } from 'app/shared/model/m-db-mapping.model';
import { MDbMappingService } from './m-db-mapping.service';
import { MDbMappingComponent } from './m-db-mapping.component';
import { MDbMappingDetailComponent } from './m-db-mapping-detail.component';
import { MDbMappingUpdateComponent } from './m-db-mapping-update.component';
import { MDbMappingDeletePopupComponent } from './m-db-mapping-delete-dialog.component';
import { IMDbMapping } from 'app/shared/model/m-db-mapping.model';

@Injectable({ providedIn: 'root' })
export class MDbMappingResolve implements Resolve<IMDbMapping> {
  constructor(private service: MDbMappingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDbMapping> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDbMapping>) => response.ok),
        map((mDbMapping: HttpResponse<MDbMapping>) => mDbMapping.body)
      );
    }
    return of(new MDbMapping());
  }
}

export const mDbMappingRoute: Routes = [
  {
    path: '',
    component: MDbMappingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDbMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDbMappingDetailComponent,
    resolve: {
      mDbMapping: MDbMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDbMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDbMappingUpdateComponent,
    resolve: {
      mDbMapping: MDbMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDbMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDbMappingUpdateComponent,
    resolve: {
      mDbMapping: MDbMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDbMappings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDbMappingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDbMappingDeletePopupComponent,
    resolve: {
      mDbMapping: MDbMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDbMappings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
