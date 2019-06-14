import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';
import { MGachaRenditionTrajectoryCutInService } from './m-gacha-rendition-trajectory-cut-in.service';
import { MGachaRenditionTrajectoryCutInComponent } from './m-gacha-rendition-trajectory-cut-in.component';
import { MGachaRenditionTrajectoryCutInDetailComponent } from './m-gacha-rendition-trajectory-cut-in-detail.component';
import { MGachaRenditionTrajectoryCutInUpdateComponent } from './m-gacha-rendition-trajectory-cut-in-update.component';
import { MGachaRenditionTrajectoryCutInDeletePopupComponent } from './m-gacha-rendition-trajectory-cut-in-delete-dialog.component';
import { IMGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionTrajectoryCutInResolve implements Resolve<IMGachaRenditionTrajectoryCutIn> {
  constructor(private service: MGachaRenditionTrajectoryCutInService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionTrajectoryCutIn> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionTrajectoryCutIn>) => response.ok),
        map((mGachaRenditionTrajectoryCutIn: HttpResponse<MGachaRenditionTrajectoryCutIn>) => mGachaRenditionTrajectoryCutIn.body)
      );
    }
    return of(new MGachaRenditionTrajectoryCutIn());
  }
}

export const mGachaRenditionTrajectoryCutInRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionTrajectoryCutInComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionTrajectoryCutIns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionTrajectoryCutInDetailComponent,
    resolve: {
      mGachaRenditionTrajectoryCutIn: MGachaRenditionTrajectoryCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryCutIns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionTrajectoryCutInUpdateComponent,
    resolve: {
      mGachaRenditionTrajectoryCutIn: MGachaRenditionTrajectoryCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryCutIns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionTrajectoryCutInUpdateComponent,
    resolve: {
      mGachaRenditionTrajectoryCutIn: MGachaRenditionTrajectoryCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryCutIns'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionTrajectoryCutInPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionTrajectoryCutInDeletePopupComponent,
    resolve: {
      mGachaRenditionTrajectoryCutIn: MGachaRenditionTrajectoryCutInResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionTrajectoryCutIns'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
