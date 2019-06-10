import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCharacterBook } from 'app/shared/model/m-character-book.model';
import { MCharacterBookService } from './m-character-book.service';
import { MCharacterBookComponent } from './m-character-book.component';
import { MCharacterBookDetailComponent } from './m-character-book-detail.component';
import { MCharacterBookUpdateComponent } from './m-character-book-update.component';
import { MCharacterBookDeletePopupComponent } from './m-character-book-delete-dialog.component';
import { IMCharacterBook } from 'app/shared/model/m-character-book.model';

@Injectable({ providedIn: 'root' })
export class MCharacterBookResolve implements Resolve<IMCharacterBook> {
  constructor(private service: MCharacterBookService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCharacterBook> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCharacterBook>) => response.ok),
        map((mCharacterBook: HttpResponse<MCharacterBook>) => mCharacterBook.body)
      );
    }
    return of(new MCharacterBook());
  }
}

export const mCharacterBookRoute: Routes = [
  {
    path: '',
    component: MCharacterBookComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCharacterBooks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCharacterBookDetailComponent,
    resolve: {
      mCharacterBook: MCharacterBookResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterBooks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCharacterBookUpdateComponent,
    resolve: {
      mCharacterBook: MCharacterBookResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterBooks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCharacterBookUpdateComponent,
    resolve: {
      mCharacterBook: MCharacterBookResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterBooks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCharacterBookPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCharacterBookDeletePopupComponent,
    resolve: {
      mCharacterBook: MCharacterBookResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacterBooks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
