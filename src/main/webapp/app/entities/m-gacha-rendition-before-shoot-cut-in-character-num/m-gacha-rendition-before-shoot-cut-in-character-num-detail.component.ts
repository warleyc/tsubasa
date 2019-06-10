import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGachaRenditionBeforeShootCutInCharacterNum } from 'app/shared/model/m-gacha-rendition-before-shoot-cut-in-character-num.model';

@Component({
  selector: 'jhi-m-gacha-rendition-before-shoot-cut-in-character-num-detail',
  templateUrl: './m-gacha-rendition-before-shoot-cut-in-character-num-detail.component.html'
})
export class MGachaRenditionBeforeShootCutInCharacterNumDetailComponent implements OnInit {
  mGachaRenditionBeforeShootCutInCharacterNum: IMGachaRenditionBeforeShootCutInCharacterNum;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionBeforeShootCutInCharacterNum }) => {
      this.mGachaRenditionBeforeShootCutInCharacterNum = mGachaRenditionBeforeShootCutInCharacterNum;
    });
  }

  previousState() {
    window.history.back();
  }
}
