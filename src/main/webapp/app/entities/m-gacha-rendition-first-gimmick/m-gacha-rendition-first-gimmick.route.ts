import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';
import { MGachaRenditionFirstGimmickService } from './m-gacha-rendition-first-gimmick.service';
import { MGachaRenditionFirstGimmickComponent } from './m-gacha-rendition-first-gimmick.component';
import { MGachaRenditionFirstGimmickDetailComponent } from './m-gacha-rendition-first-gimmick-detail.component';
import { MGachaRenditionFirstGimmickUpdateComponent } from './m-gacha-rendition-first-gimmick-update.component';
import { MGachaRenditionFirstGimmickDeletePopupComponent } from './m-gacha-rendition-first-gimmick-delete-dialog.component';
import { IMGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionFirstGimmickResolve implements Resolve<IMGachaRenditionFirstGimmick> {
  constructor(private service: MGachaRenditionFirstGimmickService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionFirstGimmick> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionFirstGimmick>) => response.ok),
        map((mGachaRenditionFirstGimmick: HttpResponse<MGachaRenditionFirstGimmick>) => mGachaRenditionFirstGimmick.body)
      );
    }
    return of(new MGachaRenditionFirstGimmick());
  }
}

export const mGachaRenditionFirstGimmickRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionFirstGimmickComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionFirstGimmicks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionFirstGimmickDetailComponent,
    resolve: {
      mGachaRenditionFirstGimmick: MGachaRenditionFirstGimmickResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionFirstGimmicks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionFirstGimmickUpdateComponent,
    resolve: {
      mGachaRenditionFirstGimmick: MGachaRenditionFirstGimmickResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionFirstGimmicks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionFirstGimmickUpdateComponent,
    resolve: {
      mGachaRenditionFirstGimmick: MGachaRenditionFirstGimmickResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionFirstGimmicks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionFirstGimmickPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionFirstGimmickDeletePopupComponent,
    resolve: {
      mGachaRenditionFirstGimmick: MGachaRenditionFirstGimmickResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionFirstGimmicks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
