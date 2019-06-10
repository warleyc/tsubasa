import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCheatCaution } from 'app/shared/model/m-cheat-caution.model';
import { MCheatCautionService } from './m-cheat-caution.service';

@Component({
  selector: 'jhi-m-cheat-caution-delete-dialog',
  templateUrl: './m-cheat-caution-delete-dialog.component.html'
})
export class MCheatCautionDeleteDialogComponent {
  mCheatCaution: IMCheatCaution;

  constructor(
    protected mCheatCautionService: MCheatCautionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCheatCautionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCheatCautionListModification',
        content: 'Deleted an mCheatCaution'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-cheat-caution-delete-popup',
  template: ''
})
export class MCheatCautionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCheatCaution }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCheatCautionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCheatCaution = mCheatCaution;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-cheat-caution', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-cheat-caution', { outlets: { popup: null } }]);
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
