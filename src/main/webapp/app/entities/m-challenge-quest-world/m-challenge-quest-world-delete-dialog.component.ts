import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';
import { MChallengeQuestWorldService } from './m-challenge-quest-world.service';

@Component({
  selector: 'jhi-m-challenge-quest-world-delete-dialog',
  templateUrl: './m-challenge-quest-world-delete-dialog.component.html'
})
export class MChallengeQuestWorldDeleteDialogComponent {
  mChallengeQuestWorld: IMChallengeQuestWorld;

  constructor(
    protected mChallengeQuestWorldService: MChallengeQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mChallengeQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mChallengeQuestWorldListModification',
        content: 'Deleted an mChallengeQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-challenge-quest-world-delete-popup',
  template: ''
})
export class MChallengeQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MChallengeQuestWorldDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mChallengeQuestWorld = mChallengeQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-challenge-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-challenge-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
