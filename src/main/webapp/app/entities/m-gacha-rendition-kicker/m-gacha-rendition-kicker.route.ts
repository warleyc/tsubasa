import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';
import { MGachaRenditionKickerService } from './m-gacha-rendition-kicker.service';
import { MGachaRenditionKickerComponent } from './m-gacha-rendition-kicker.component';
import { MGachaRenditionKickerDetailComponent } from './m-gacha-rendition-kicker-detail.component';
import { MGachaRenditionKickerUpdateComponent } from './m-gacha-rendition-kicker-update.component';
import { MGachaRenditionKickerDeletePopupComponent } from './m-gacha-rendition-kicker-delete-dialog.component';
import { IMGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionKickerResolve implements Resolve<IMGachaRenditionKicker> {
  constructor(private service: MGachaRenditionKickerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionKicker> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionKicker>) => response.ok),
        map((mGachaRenditionKicker: HttpResponse<MGachaRenditionKicker>) => mGachaRenditionKicker.body)
      );
    }
    return of(new MGachaRenditionKicker());
  }
}

export const mGachaRenditionKickerRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionKickerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionKickers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionKickerDetailComponent,
    resolve: {
      mGachaRenditionKicker: MGachaRenditionKickerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionKickers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionKickerUpdateComponent,
    resolve: {
      mGachaRenditionKicker: MGachaRenditionKickerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionKickers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionKickerUpdateComponent,
    resolve: {
      mGachaRenditionKicker: MGachaRenditionKickerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionKickers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionKickerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionKickerDeletePopupComponent,
    resolve: {
      mGachaRenditionKicker: MGachaRenditionKickerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionKickers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
