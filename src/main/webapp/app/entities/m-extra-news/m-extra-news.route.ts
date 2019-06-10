import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MExtraNews } from 'app/shared/model/m-extra-news.model';
import { MExtraNewsService } from './m-extra-news.service';
import { MExtraNewsComponent } from './m-extra-news.component';
import { MExtraNewsDetailComponent } from './m-extra-news-detail.component';
import { MExtraNewsUpdateComponent } from './m-extra-news-update.component';
import { MExtraNewsDeletePopupComponent } from './m-extra-news-delete-dialog.component';
import { IMExtraNews } from 'app/shared/model/m-extra-news.model';

@Injectable({ providedIn: 'root' })
export class MExtraNewsResolve implements Resolve<IMExtraNews> {
  constructor(private service: MExtraNewsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMExtraNews> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MExtraNews>) => response.ok),
        map((mExtraNews: HttpResponse<MExtraNews>) => mExtraNews.body)
      );
    }
    return of(new MExtraNews());
  }
}

export const mExtraNewsRoute: Routes = [
  {
    path: '',
    component: MExtraNewsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MExtraNews'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MExtraNewsDetailComponent,
    resolve: {
      mExtraNews: MExtraNewsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtraNews'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MExtraNewsUpdateComponent,
    resolve: {
      mExtraNews: MExtraNewsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtraNews'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MExtraNewsUpdateComponent,
    resolve: {
      mExtraNews: MExtraNewsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtraNews'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mExtraNewsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MExtraNewsDeletePopupComponent,
    resolve: {
      mExtraNews: MExtraNewsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MExtraNews'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
