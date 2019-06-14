import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';

@Component({
  selector: 'jhi-m-model-uniform-up-resource-detail',
  templateUrl: './m-model-uniform-up-resource-detail.component.html'
})
export class MModelUniformUpResourceDetailComponent implements OnInit {
  mModelUniformUpResource: IMModelUniformUpResource;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformUpResource }) => {
      this.mModelUniformUpResource = mModelUniformUpResource;
    });
  }

  previousState() {
    window.history.back();
  }
}
