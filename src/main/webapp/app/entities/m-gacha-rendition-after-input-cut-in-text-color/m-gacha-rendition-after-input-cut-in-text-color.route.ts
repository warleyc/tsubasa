import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';
import { MGachaRenditionAfterInputCutInTextColorService } from './m-gacha-rendition-after-input-cut-in-text-color.service';
import { MGachaRenditionAfterInputCutInTextColorComponent } from './m-gacha-rendition-after-input-cut-in-text-color.component';
import { MGachaRenditionAfterInputCutInTextColorDetailComponent } from './m-gacha-rendition-after-input-cut-in-text-color-detail.component';
import { MGachaRenditionAfterInputCutInTextColorUpdateComponent } from './m-gacha-rendition-after-input-cut-in-text-color-update.component';
import { MGachaRenditionAfterInputCutInTextColorDeletePopupComponent } from './m-gacha-rendition-after-input-cut-in-text-color-delete-dialog.component';
import { IMGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionAfterInputCutInTextColorResolve implements Resolve<IMGachaRenditionAfterInputCutInTextColor> {
  constructor(private service: MGachaRenditionAfterInputCutInTextColorService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionAfterInputCutInTextColor> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionAfterInputCutInTextColor>) => response.ok),
        map(
          (mGachaRenditionAfterInputCutInTextColor: HttpResponse<MGachaRenditionAfterInputCutInTextColor>) =>
            mGachaRenditionAfterInputCutInTextColor.body
        )
      );
    }
    return of(new MGachaRenditionAfterInputCutInTextColor());
  }
}

export const mGachaRenditionAfterInputCutInTextColorRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionAfterInputCutInTextColorComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionAfterInputCutInTextColors'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionAfterInputCutInTextColorDetailComponent,
    resolve: {
      mGachaRenditionAfterInputCutInTextColor: MGachaRenditionAfterInputCutInTextColorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutInTextColors'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionAfterInputCutInTextColorUpdateComponent,
    resolve: {
      mGachaRenditionAfterInputCutInTextColor: MGachaRenditionAfterInputCutInTextColorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutInTextColors'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionAfterInputCutInTextColorUpdateComponent,
    resolve: {
      mGachaRenditionAfterInputCutInTextColor: MGachaRenditionAfterInputCutInTextColorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutInTextColors'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionAfterInputCutInTextColorPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionAfterInputCutInTextColorDeletePopupComponent,
    resolve: {
      mGachaRenditionAfterInputCutInTextColor: MGachaRenditionAfterInputCutInTextColorResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionAfterInputCutInTextColors'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
