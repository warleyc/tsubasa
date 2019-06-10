import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';
import { MGachaRenditionAfterInputCutInService } from './m-gacha-rendition-after-input-cut-in.service';
import { MGachaRenditionAfterInputCutInComponent } from './m-gacha-rendition-after-input-cut-in.component';
import { MGachaRenditionAfterInputCutInDetailComponent } from './m-gacha-rendition-after-input-cut-in-detail.component';
import { MGachaRenditionAfterInputCutInUpdateComponent } from './m-gacha-rendition-after-input-cut-in-update.component';
import { MGachaRenditionAfterInputCutInDeletePopupComponent } from './m-gacha-rendition-after-input-cut-in-delete-dialog.component';
import { IMGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionAfterInputCutInResolve implements Resolve<IMGachaRenditionAfterInputCutIn> {
  constructor(private service: MGachaRenditionAfterInputCutInService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionAfterInputCutIn> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionAfterInputCutIn>) => response.ok),
        map((mGachaRenditionAfterInputCutIn: HttpResponse<MGachaRenditionAfterInputCutIn>) => mGachaRenditionAfterInputCutIn.body)
      );
    }
    return of(new MGachaRenditionAfterInputCutIn());
  }
}

export const mGachaRenditionAfterInputCutInRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionAfterInputCutInComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionAfterInputCutIns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionAfterInputCutInDetailComponent,
    resolve: {
      mGachaRenditionAfterInputCutIn: MGachaRenditionAfterInputCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutIns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionAfterInputCutInUpdateComponent,
    resolve: {
      mGachaRenditionAfterInputCutIn: MGachaRenditionAfterInputCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutIns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionAfterInputCutInUpdateComponent,
    resolve: {
      mGachaRenditionAfterInputCutIn: MGachaRenditionAfterInputCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutIns'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionAfterInputCutInPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionAfterInputCutInDeletePopupComponent,
    resolve: {
      mGachaRenditionAfterInputCutIn: MGachaRenditionAfterInputCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutIns'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
