import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MColorPalette } from 'app/shared/model/m-color-palette.model';
import { MColorPaletteService } from './m-color-palette.service';
import { MColorPaletteComponent } from './m-color-palette.component';
import { MColorPaletteDetailComponent } from './m-color-palette-detail.component';
import { MColorPaletteUpdateComponent } from './m-color-palette-update.component';
import { MColorPaletteDeletePopupComponent } from './m-color-palette-delete-dialog.component';
import { IMColorPalette } from 'app/shared/model/m-color-palette.model';

@Injectable({ providedIn: 'root' })
export class MColorPaletteResolve implements Resolve<IMColorPalette> {
  constructor(private service: MColorPaletteService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMColorPalette> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MColorPalette>) => response.ok),
        map((mColorPalette: HttpResponse<MColorPalette>) => mColorPalette.body)
      );
    }
    return of(new MColorPalette());
  }
}

export const mColorPaletteRoute: Routes = [
  {
    path: '',
    component: MColorPaletteComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MColorPalettes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MColorPaletteDetailComponent,
    resolve: {
      mColorPalette: MColorPaletteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MColorPalettes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MColorPaletteUpdateComponent,
    resolve: {
      mColorPalette: MColorPaletteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MColorPalettes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MColorPaletteUpdateComponent,
    resolve: {
      mColorPalette: MColorPaletteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MColorPalettes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mColorPalettePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MColorPaletteDeletePopupComponent,
    resolve: {
      mColorPalette: MColorPaletteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MColorPalettes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
