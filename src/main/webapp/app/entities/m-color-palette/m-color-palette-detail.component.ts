import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMColorPalette } from 'app/shared/model/m-color-palette.model';

@Component({
  selector: 'jhi-m-color-palette-detail',
  templateUrl: './m-color-palette-detail.component.html'
})
export class MColorPaletteDetailComponent implements OnInit {
  mColorPalette: IMColorPalette;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mColorPalette }) => {
      this.mColorPalette = mColorPalette;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
