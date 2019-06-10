import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMArousalMaterial } from 'app/shared/model/m-arousal-material.model';

@Component({
  selector: 'jhi-m-arousal-material-detail',
  templateUrl: './m-arousal-material-detail.component.html'
})
export class MArousalMaterialDetailComponent implements OnInit {
  mArousalMaterial: IMArousalMaterial;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mArousalMaterial }) => {
      this.mArousalMaterial = mArousalMaterial;
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
