import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMNpcPersonality } from 'app/shared/model/m-npc-personality.model';

@Component({
  selector: 'jhi-m-npc-personality-detail',
  templateUrl: './m-npc-personality-detail.component.html'
})
export class MNpcPersonalityDetailComponent implements OnInit {
  mNpcPersonality: IMNpcPersonality;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNpcPersonality }) => {
      this.mNpcPersonality = mNpcPersonality;
    });
  }

  previousState() {
    window.history.back();
  }
}
