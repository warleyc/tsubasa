import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTicketQuestTsubasaPointReward } from 'app/shared/model/m-ticket-quest-tsubasa-point-reward.model';

@Component({
  selector: 'jhi-m-ticket-quest-tsubasa-point-reward-detail',
  templateUrl: './m-ticket-quest-tsubasa-point-reward-detail.component.html'
})
export class MTicketQuestTsubasaPointRewardDetailComponent implements OnInit {
  mTicketQuestTsubasaPointReward: IMTicketQuestTsubasaPointReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestTsubasaPointReward }) => {
      this.mTicketQuestTsubasaPointReward = mTicketQuestTsubasaPointReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
