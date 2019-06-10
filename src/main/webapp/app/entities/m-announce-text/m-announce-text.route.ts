import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MAnnounceText } from 'app/shared/model/m-announce-text.model';
import { MAnnounceTextService } from './m-announce-text.service';
import { MAnnounceTextComponent } from './m-announce-text.component';
import { MAnnounceTextDetailComponent } from './m-announce-text-detail.component';
import { MAnnounceTextUpdateComponent } from './m-announce-text-update.component';
import { MAnnounceTextDeletePopupComponent } from './m-announce-text-delete-dialog.component';
import { IMAnnounceText } from 'app/shared/model/m-announce-text.model';

@Injectable({ providedIn: 'root' })
export class MAnnounceTextResolve implements Resolve<IMAnnounceText> {
  constructor(private service: MAnnounceTextService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMAnnounceText> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MAnnounceText>) => response.ok),
        map((mAnnounceText: HttpResponse<MAnnounceText>) => mAnnounceText.body)
      );
    }
    return of(new MAnnounceText());
  }
}

export const mAnnounceTextRoute: Routes = [
  {
    path: '',
    component: MAnnounceTextComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MAnnounceTexts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MAnnounceTextDetailComponent,
    resolve: {
      mAnnounceText: MAnnounceTextResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAnnounceTexts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MAnnounceTextUpdateComponent,
    resolve: {
      mAnnounceText: MAnnounceTextResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAnnounceTexts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MAnnounceTextUpdateComponent,
    resolve: {
      mAnnounceText: MAnnounceTextResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAnnounceTexts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mAnnounceTextPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MAnnounceTextDeletePopupComponent,
    resolve: {
      mAnnounceText: MAnnounceTextResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MAnnounceTexts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
