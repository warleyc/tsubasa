import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';
import { MGachaRenditionExtraCutinService } from './m-gacha-rendition-extra-cutin.service';
import { MGachaRenditionExtraCutinComponent } from './m-gacha-rendition-extra-cutin.component';
import { MGachaRenditionExtraCutinDetailComponent } from './m-gacha-rendition-extra-cutin-detail.component';
import { MGachaRenditionExtraCutinUpdateComponent } from './m-gacha-rendition-extra-cutin-update.component';
import { MGachaRenditionExtraCutinDeletePopupComponent } from './m-gacha-rendition-extra-cutin-delete-dialog.component';
import { IMGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionExtraCutinResolve implements Resolve<IMGachaRenditionExtraCutin> {
  constructor(private service: MGachaRenditionExtraCutinService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionExtraCutin> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionExtraCutin>) => response.ok),
        map((mGachaRenditionExtraCutin: HttpResponse<MGachaRenditionExtraCutin>) => mGachaRenditionExtraCutin.body)
      );
    }
    return of(new MGachaRenditionExtraCutin());
  }
}

export const mGachaRenditionExtraCutinRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionExtraCutinComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionExtraCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionExtraCutinDetailComponent,
    resolve: {
      mGachaRenditionExtraCutin: MGachaRenditionExtraCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionExtraCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionExtraCutinUpdateComponent,
    resolve: {
      mGachaRenditionExtraCutin: MGachaRenditionExtraCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionExtraCutins'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionExtraCutinUpdateComponent,
    resolve: {
      mGachaRenditionExtraCutin: MGachaRenditionExtraCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionExtraCutins'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionExtraCutinPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionExtraCutinDeletePopupComponent,
    resolve: {
      mGachaRenditionExtraCutin: MGachaRenditionExtraCutinResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionExtraCutins'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
