import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRendition } from 'app/shared/model/m-gacha-rendition.model';
import { MGachaRenditionService } from './m-gacha-rendition.service';
import { MGachaRenditionComponent } from './m-gacha-rendition.component';
import { MGachaRenditionDetailComponent } from './m-gacha-rendition-detail.component';
import { MGachaRenditionUpdateComponent } from './m-gacha-rendition-update.component';
import { MGachaRenditionDeletePopupComponent } from './m-gacha-rendition-delete-dialog.component';
import { IMGachaRendition } from 'app/shared/model/m-gacha-rendition.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionResolve implements Resolve<IMGachaRendition> {
  constructor(private service: MGachaRenditionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRendition> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRendition>) => response.ok),
        map((mGachaRendition: HttpResponse<MGachaRendition>) => mGachaRendition.body)
      );
    }
    return of(new MGachaRendition());
  }
}

export const mGachaRenditionRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionDetailComponent,
    resolve: {
      mGachaRendition: MGachaRenditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionUpdateComponent,
    resolve: {
      mGachaRendition: MGachaRenditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionUpdateComponent,
    resolve: {
      mGachaRendition: MGachaRenditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionDeletePopupComponent,
    resolve: {
      mGachaRendition: MGachaRenditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
