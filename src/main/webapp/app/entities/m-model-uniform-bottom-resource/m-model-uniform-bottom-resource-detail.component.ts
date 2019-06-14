import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';

@Component({
  selector: 'jhi-m-model-uniform-bottom-resource-detail',
  templateUrl: './m-model-uniform-bottom-resource-detail.component.html'
})
export class MModelUniformBottomResourceDetailComponent implements OnInit {
  mModelUniformBottomResource: IMModelUniformBottomResource;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformBottomResource }) => {
      this.mModelUniformBottomResource = mModelUniformBottomResource;
    });
  }

  previousState() {
    window.history.back();
  }
}
