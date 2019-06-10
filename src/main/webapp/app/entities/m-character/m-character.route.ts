import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCharacter } from 'app/shared/model/m-character.model';
import { MCharacterService } from './m-character.service';
import { MCharacterComponent } from './m-character.component';
import { MCharacterDetailComponent } from './m-character-detail.component';
import { MCharacterUpdateComponent } from './m-character-update.component';
import { MCharacterDeletePopupComponent } from './m-character-delete-dialog.component';
import { IMCharacter } from 'app/shared/model/m-character.model';

@Injectable({ providedIn: 'root' })
export class MCharacterResolve implements Resolve<IMCharacter> {
  constructor(private service: MCharacterService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCharacter> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCharacter>) => response.ok),
        map((mCharacter: HttpResponse<MCharacter>) => mCharacter.body)
      );
    }
    return of(new MCharacter());
  }
}

export const mCharacterRoute: Routes = [
  {
    path: '',
    component: MCharacterComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCharacters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCharacterDetailComponent,
    resolve: {
      mCharacter: MCharacterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCharacterUpdateComponent,
    resolve: {
      mCharacter: MCharacterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacters'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCharacterUpdateComponent,
    resolve: {
      mCharacter: MCharacterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacters'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCharacterPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCharacterDeletePopupComponent,
    resolve: {
      mCharacter: MCharacterResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCharacters'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
