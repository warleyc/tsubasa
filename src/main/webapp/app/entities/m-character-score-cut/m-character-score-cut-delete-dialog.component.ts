import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCharacterScoreCut } from 'app/shared/model/m-character-score-cut.model';
import { MCharacterScoreCutService } from './m-character-score-cut.service';

@Component({
  selector: 'jhi-m-character-score-cut-delete-dialog',
  templateUrl: './m-character-score-cut-delete-dialog.component.html'
})
export class MCharacterScoreCutDeleteDialogComponent {
  mCharacterScoreCut: IMCharacterScoreCut;

  constructor(
    protected mCharacterScoreCutService: MCharacterScoreCutService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCharacterScoreCutService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCharacterScoreCutListModification',
        content: 'Deleted an mCharacterScoreCut'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-character-score-cut-delete-popup',
  template: ''
})
export class MCharacterScoreCutDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCharacterScoreCut }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCharacterScoreCutDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCharacterScoreCut = mCharacterScoreCut;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-character-score-cut', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-character-score-cut', { outlets: { popup: null } }]);
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
