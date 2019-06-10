import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';
import { MGachaRenditionBeforeShootCutInCharacterNumService } from './m-gacha-rendition-before-shoot-cut-in-character-num.service';
import { MGachaRenditionBeforeShootCutInCharacterNumComponent } from './m-gacha-rendition-before-shoot-cut-in-character-num.component';
import { MGachaRenditionBeforeShootCutInCharacterNumDetailComponent } from './m-gacha-rendition-before-shoot-cut-in-character-num-detail.component';
import { MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent } from './m-gacha-rendition-before-shoot-cut-in-character-num-update.component';
import { MGachaRenditionBeforeShootCutInCharacterNumDeletePopupComponent } from './m-gacha-rendition-before-shoot-cut-in-character-num-delete-dialog.component';
import { IMGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionBeforeShootCutInCharacterNumResolve implements Resolve<IMGachaRenditionBeforeShootCutInCharacterNum> {
  constructor(private service: MGachaRenditionBeforeShootCutInCharacterNumService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionBeforeShootCutInCharacterNum> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionBeforeShootCutInCharacterNum>) => response.ok),
        map(
          (mGachaRenditionBeforeShootCutInCharacterNum: HttpResponse<MGachaRenditionBeforeShootCutInCharacterNum>) =>
            mGachaRenditionBeforeShootCutInCharacterNum.body
        )
      );
    }
    return of(new MGachaRenditionBeforeShootCutInCharacterNum());
  }
}

export const mGachaRenditionBeforeShootCutInCharacterNumRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionBeforeShootCutInCharacterNumComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionBeforeShootCutInCharacterNums'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionBeforeShootCutInCharacterNumDetailComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInCharacterNum: MGachaRenditionBeforeShootCutInCharacterNumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInCharacterNums'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInCharacterNum: MGachaRenditionBeforeShootCutInCharacterNumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInCharacterNums'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionBeforeShootCutInCharacterNumUpdateComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInCharacterNum: MGachaRenditionBeforeShootCutInCharacterNumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInCharacterNums'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionBeforeShootCutInCharacterNumPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionBeforeShootCutInCharacterNumDeletePopupComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInCharacterNum: MGachaRenditionBeforeShootCutInCharacterNumResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInCharacterNums'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
