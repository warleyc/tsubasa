import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';
import { MStoryResourceMappingService } from './m-story-resource-mapping.service';
import { MStoryResourceMappingComponent } from './m-story-resource-mapping.component';
import { MStoryResourceMappingDetailComponent } from './m-story-resource-mapping-detail.component';
import { MStoryResourceMappingUpdateComponent } from './m-story-resource-mapping-update.component';
import { MStoryResourceMappingDeletePopupComponent } from './m-story-resource-mapping-delete-dialog.component';
import { IMStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';

@Injectable({ providedIn: 'root' })
export class MStoryResourceMappingResolve implements Resolve<IMStoryResourceMapping> {
  constructor(private service: MStoryResourceMappingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMStoryResourceMapping> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MStoryResourceMapping>) => response.ok),
        map((mStoryResourceMapping: HttpResponse<MStoryResourceMapping>) => mStoryResourceMapping.body)
      );
    }
    return of(new MStoryResourceMapping());
  }
}

export const mStoryResourceMappingRoute: Routes = [
  {
    path: '',
    component: MStoryResourceMappingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MStoryResourceMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MStoryResourceMappingDetailComponent,
    resolve: {
      mStoryResourceMapping: MStoryResourceMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoryResourceMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MStoryResourceMappingUpdateComponent,
    resolve: {
      mStoryResourceMapping: MStoryResourceMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoryResourceMappings'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MStoryResourceMappingUpdateComponent,
    resolve: {
      mStoryResourceMapping: MStoryResourceMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoryResourceMappings'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mStoryResourceMappingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MStoryResourceMappingDeletePopupComponent,
    resolve: {
      mStoryResourceMapping: MStoryResourceMappingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStoryResourceMappings'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
