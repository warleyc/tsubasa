import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';
import { MGachaRenditionBeforeShootCutInPatternService } from './m-gacha-rendition-before-shoot-cut-in-pattern.service';
import { MGachaRenditionBeforeShootCutInPatternComponent } from './m-gacha-rendition-before-shoot-cut-in-pattern.component';
import { MGachaRenditionBeforeShootCutInPatternDetailComponent } from './m-gacha-rendition-before-shoot-cut-in-pattern-detail.component';
import { MGachaRenditionBeforeShootCutInPatternUpdateComponent } from './m-gacha-rendition-before-shoot-cut-in-pattern-update.component';
import { MGachaRenditionBeforeShootCutInPatternDeletePopupComponent } from './m-gacha-rendition-before-shoot-cut-in-pattern-delete-dialog.component';
import { IMGachaRenditionBeforeShootCutInPattern } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-pattern.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionBeforeShootCutInPatternResolve implements Resolve<IMGachaRenditionBeforeShootCutInPattern> {
  constructor(private service: MGachaRenditionBeforeShootCutInPatternService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionBeforeShootCutInPattern> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionBeforeShootCutInPattern>) => response.ok),
        map(
          (mGachaRenditionBeforeShootCutInPattern: HttpResponse<MGachaRenditionBeforeShootCutInPattern>) =>
            mGachaRenditionBeforeShootCutInPattern.body
        )
      );
    }
    return of(new MGachaRenditionBeforeShootCutInPattern());
  }
}

export const mGachaRenditionBeforeShootCutInPatternRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionBeforeShootCutInPatternComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionBeforeShootCutInPatterns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionBeforeShootCutInPatternDetailComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInPattern: MGachaRenditionBeforeShootCutInPatternResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInPatterns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionBeforeShootCutInPatternUpdateComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInPattern: MGachaRenditionBeforeShootCutInPatternResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInPatterns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionBeforeShootCutInPatternUpdateComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInPattern: MGachaRenditionBeforeShootCutInPatternResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInPatterns'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionBeforeShootCutInPatternPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionBeforeShootCutInPatternDeletePopupComponent,
    resolve: {
      mGachaRenditionBeforeShootCutInPattern: MGachaRenditionBeforeShootCutInPatternResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBeforeShootCutInPatterns'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
