import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';
import { MGachaRenditionBallService } from './m-gacha-rendition-ball.service';
import { MGachaRenditionBallComponent } from './m-gacha-rendition-ball.component';
import { MGachaRenditionBallDetailComponent } from './m-gacha-rendition-ball-detail.component';
import { MGachaRenditionBallUpdateComponent } from './m-gacha-rendition-ball-update.component';
import { MGachaRenditionBallDeletePopupComponent } from './m-gacha-rendition-ball-delete-dialog.component';
import { IMGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';

@Injectable({ providedIn: 'root' })
export class MGachaRenditionBallResolve implements Resolve<IMGachaRenditionBall> {
  constructor(private service: MGachaRenditionBallService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaRenditionBall> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaRenditionBall>) => response.ok),
        map((mGachaRenditionBall: HttpResponse<MGachaRenditionBall>) => mGachaRenditionBall.body)
      );
    }
    return of(new MGachaRenditionBall());
  }
}

export const mGachaRenditionBallRoute: Routes = [
  {
    path: '',
    component: MGachaRenditionBallComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaRenditionBalls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaRenditionBallDetailComponent,
    resolve: {
      mGachaRenditionBall: MGachaRenditionBallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBalls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaRenditionBallUpdateComponent,
    resolve: {
      mGachaRenditionBall: MGachaRenditionBallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBalls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaRenditionBallUpdateComponent,
    resolve: {
      mGachaRenditionBall: MGachaRenditionBallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBalls'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaRenditionBallPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaRenditionBallDeletePopupComponent,
    resolve: {
      mGachaRenditionBall: MGachaRenditionBallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaRenditionBalls'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
