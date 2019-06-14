import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';

@Component({
  selector: 'jhi-m-guild-negative-word-detail',
  templateUrl: './m-guild-negative-word-detail.component.html'
})
export class MGuildNegativeWordDetailComponent implements OnInit {
  mGuildNegativeWord: IMGuildNegativeWord;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildNegativeWord }) => {
      this.mGuildNegativeWord = mGuildNegativeWord;
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
