import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMatchOption } from 'app/shared/model/m-match-option.model';

@Component({
  selector: 'jhi-m-match-option-detail',
  templateUrl: './m-match-option-detail.component.html'
})
export class MMatchOptionDetailComponent implements OnInit {
  mMatchOption: IMMatchOption;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchOption }) => {
      this.mMatchOption = mMatchOption;
    });
  }

  previousState() {
    window.history.back();
  }
}
