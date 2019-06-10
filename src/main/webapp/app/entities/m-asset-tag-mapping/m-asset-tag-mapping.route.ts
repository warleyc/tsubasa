import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';
import { MAssetTagMappingService } from './m-asset-tag-mapping.service';
import { MAssetTagMappingComponent } from './m-asset-tag-mapping.component';
import { MAssetTagMappingDetailComponent } from './m-asset-tag-mapping-detail.component';
import { MAssetTagMappingUpdateComponent } from './m-asset-tag-mapping-update.component';
import { MAssetTagMappingDeletePopupComponent } from './m-asset-tag-mapping-delete-dialog.component';
import { IMAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';

@Injectable({ providedIn: 'root' })
export class MAssetTagMappingResolve implements Resolve<IMAssetTagMapping> {
  constructor(private service: MAssetTagMappingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAssetTagMapping> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAssetTagMapping>) => response.ok),
        map((mAssetTagMapping: HttpResponse<MAssetTagMapping>) => mAssetTagMapping.body)
      );
    }
    return of(new MAssetTagMapping());
  }
}

export const mAssetTagMappingRoute: Routes = [
  {
    path: '',
    component: MAssetTagMappingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAssetTagMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAssetTagMappingDetailComponent,
    resolve: {
      mAssetTagMapping: MAssetTagMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssetTagMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAssetTagMappingUpdateComponent,
    resolve: {
      mAssetTagMapping: MAssetTagMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssetTagMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAssetTagMappingUpdateComponent,
    resolve: {
      mAssetTagMapping: MAssetTagMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssetTagMappings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAssetTagMappingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAssetTagMappingDeletePopupComponent,
    resolve: {
      mAssetTagMapping: MAssetTagMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAssetTagMappings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
