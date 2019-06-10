import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCharacter } from 'app/shared/model/m-character.model';

@Component({
  selector: 'jhi-m-character-detail',
  templateUrl: './m-character-detail.component.html'
})
export class MCharacterDetailComponent implements OnInit {
  mCharacter: IMCharacter;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCharacter }) => {
      this.mCharacter = mCharacter;
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
